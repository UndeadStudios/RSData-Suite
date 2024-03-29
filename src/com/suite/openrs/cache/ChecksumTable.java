package com.suite.openrs.cache;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;

import com.suite.openrs.util.crypto.Rsa;
import com.suite.openrs.util.crypto.Whirlpool;

/**
 * A {@link ChecksumTable} stores checksums and versions of
 * {@link ReferenceTable}s. When encoded in a {@link Container} and prepended
 * with the file type and id it is more commonly known as the client's
 * "update keys".
 * @author Graham
 * @author `Discardedx2
 */
public class ChecksumTable {

	/**
	 * Decodes the {@link ChecksumTable} in the specified
	 * {@link ByteBuffer}. Whirlpool digests are not read.
	 * @param buffer The {@link ByteBuffer} containing the table.
	 * @return The decoded {@link ChecksumTable}.
	 * @throws IOException if an I/O error occurs.
	 */
	public static ChecksumTable decode(ByteBuffer buffer) throws IOException {
		return decode(buffer, false);
	}

	/**
	 * Decodes the {@link ChecksumTable} in the specified
	 * {@link ByteBuffer}.
	 * @param buffer The {@link ByteBuffer} containing the table.
	 * @param whirlpool If whirlpool digests should be read.
	 * @return The decoded {@link ChecksumTable}.
	 * @throws IOException if an I/O error occurs.
	 */
	public static ChecksumTable decode(ByteBuffer buffer, boolean whirlpool) throws IOException {
		return decode(buffer, whirlpool, null, null);
	}

	/**
	 * Decodes the {@link ChecksumTable} in the specified
	 * {@link ByteBuffer} and decrypts the final whirlpool hash.
	 * @param buffer The {@link ByteBuffer} containing the table.
	 * @param whirlpool If whirlpool digests should be read.
	 * @param modulus The modulus.
	 * @param publicKey The public key.
	 * @return The decoded {@link ChecksumTable}.
	 * @throws IOException if an I/O error occurs.
	 */
	public static ChecksumTable decode(ByteBuffer buffer, boolean whirlpool, BigInteger modulus, BigInteger publicKey) throws IOException {
		/* find out how many entries there are and allocate a new table */
		int size = whirlpool ? (buffer.limit() / 8) : (buffer.get() & 0xFF);
		ChecksumTable table = new ChecksumTable(size);

		/* calculate the whirlpool digest we expect to have at the end */
		byte[] masterDigest = null;
		if (whirlpool) {
			byte[] temp = new byte[size * 72 + 1];
			buffer.position(0);
			buffer.get(temp);
			masterDigest = Whirlpool.whirlpool(temp, 0, temp.length);
		}

		/* read the entries */
		buffer.position(1);
		for (int i = 0; i < size; i++) {
			int crc = buffer.getInt();
			int version = buffer.getInt();
			byte[] digest = new byte[64];
			if (whirlpool) {
				buffer.get(digest);
			}
			table.entries[i] = new Entry(crc, version, digest);
		}

		/* read the trailing digest and check if it matches up */
		if (whirlpool) {
			byte[] bytes = new byte[buffer.remaining()];
			buffer.get(bytes);
			ByteBuffer temp = ByteBuffer.wrap(bytes);

			if (modulus != null && publicKey != null) {
				temp = Rsa.crypt(buffer, modulus, publicKey);
			}

			if (temp.limit() != 66)
				throw new IOException("Decrypted data is not 66 bytes long");

			for (int i = 0; i < 64; i++) {
				if (temp.get(i + 1) != masterDigest[i])
					throw new IOException("Whirlpool digest mismatch");
			}
		}

		/* if it looks good return the table */
		return table;
	}

	/**
	 * Represents a single entry in a {@link ChecksumTable}. Each entry
	 * contains a CRC32 checksum and version of the corresponding
	 * {@link ReferenceTable}.
	 * @author Graham Edgecombe
	 */
	public static class Entry {

		/**
		 * The CRC32 checksum of the reference table.
		 */
		private final int crc;

		/**
		 * The version of the reference table.
		 */
		private final int version;

		/**
		 * The whirlpool digest of the reference table.
		 */
		private final byte[] whirlpool;

		/**
		 * Creates a new entry.
		 * @param crc The CRC32 checksum of the slave table.
		 * @param version The version of the slave table.
		 * @param whirlpool The whirlpool digest of the reference table.
		 */
		public Entry(int crc, int version, byte[] whirlpool) {
			if (whirlpool.length != 64)
				throw new IllegalArgumentException();

			this.crc = crc;
			this.version = version;
			this.whirlpool = whirlpool;
		}

		/**
		 * Gets the CRC32 checksum of the reference table.
		 * @return The CRC32 checksum.
		 */
		public int getCrc() {
			return crc;
		}

		/**
		 * Gets the version of the reference table.
		 * @return The version.
		 */
		public int getVersion() {
			return version;
		}

		/**
		 * Gets the whirlpool digest of the reference table.
		 * @return The whirlpool digest.
		 */
		public byte[] getWhirlpool() {
			return whirlpool;
		}

	}

	/**
	 * The entries in this table.
	 */
	private Entry[] entries;

	/**
	 * Creates a new {@link ChecksumTable} with the specified size.
	 * @param size The number of entries in this table.
	 */
	public ChecksumTable(int size) {
		entries = new Entry[size];
	}

	/**
	 * Encodes this {@link ChecksumTable}. Whirlpool digests are not encoded.
	 * @return The encoded {@link ByteBuffer}.
	 * @throws IOException if an I/O error occurs.
	 */
	public ByteBuffer encode() throws IOException {
		return encode(false);
	}

	/**
	 * Encodes this {@link ChecksumTable}.
	 * @param whirlpool If whirlpool digests should be encoded.
	 * @return The encoded {@link ByteBuffer}.
	 * @throws IOException if an I/O error occurs.
	 */
	public ByteBuffer encode(boolean whirlpool) throws IOException {
		return encode(whirlpool, null, null);
	}

	/**
	 * Encodes this {@link ChecksumTable} and encrypts the final whirlpool hash.
	 * @param whirlpool If whirlpool digests should be encoded.
	 * @param modulus The modulus.
	 * @param privateKey The private key.
	 * @return The encoded {@link ByteBuffer}.
	 * @throws IOException if an I/O error occurs.
	 */
	public ByteBuffer encode(boolean whirlpool, BigInteger modulus, BigInteger privateKey) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		DataOutputStream os = new DataOutputStream(bout);
		try {
			/* as the new whirlpool format is more complicated we must write the number of entries */
			if (whirlpool)
				os.write(entries.length);

			/* encode the individual entries */
			for (int i = 0; i < entries.length; i++) {
				Entry entry = entries[i];
				os.writeInt(entry.getCrc());
				os.writeInt(entry.getVersion());
				if (whirlpool)
					os.write(entry.getWhirlpool());
			}

			/* compute (and encrypt) the digest of the whole table */
			if (whirlpool) {
				byte[] bytes = bout.toByteArray();
				ByteBuffer temp = ByteBuffer.allocate(66);
				temp.put((byte) 0);
				temp.put(Whirlpool.whirlpool(bytes, 5, bytes.length - 5));
				temp.put((byte) 0);
				temp.flip();

				if (modulus != null && privateKey != null) {
					temp = Rsa.crypt(temp, modulus, privateKey);
				}

				bytes = new byte[temp.limit()];
				temp.get(bytes);
				os.write(bytes);
			}

			byte[] bytes = bout.toByteArray();
			return ByteBuffer.wrap(bytes);
		} finally {
			os.close();
		}
	}

	/**
	 * Gets the size of this table.
	 * @return The size of this table.
	 */
	public int getSize() {
		return entries.length;
	}

	/**
	 * Sets an entry in this table.
	 * @param id The id.
	 * @param entry The entry.
	 * @throws IndexOutOfBoundsException if the id is less than zero or greater
	 * than or equal to the size of the table.
	 */
	public void setEntry(int id, Entry entry) {
		if (id < 0 || id >= entries.length)
			throw new IndexOutOfBoundsException();
		entries[id] = entry;
	}

	/**
	 * Gets an entry from this table.
	 * @param id The id.
	 * @return The entry.
	 * @throws IndexOutOfBoundsException if the id is less than zero or greater
	 * than or equal to the size of the table.
	 */
	public Entry getEntry(int id) {
		if (id < 0 || id >= entries.length)
			throw new IndexOutOfBoundsException();
		return entries[id];
	}

}
