package com.suite.openrs.cache;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.suite.openrs.cache.util.CompressionUtils;
import com.suite.openrs.util.crypto.Xtea;

/**
 * A {@link Container} holds an optionally compressed file. This class can be
 * used to decompress and compress containers. A container can also have a two
 * byte trailer which specifies the version of the file within it.
 * @author Graham
 * @author `Discardedx2
 */
public final class Container {

	/**
	 * This type indicates that no compression is used.
	 */
	public static final int COMPRESSION_NONE = 0;

	/**
	 * This type indicates that BZIP2 compression is used.
	 */
	public static final int COMPRESSION_BZIP2 = 1;

	/**
	 * This type indicates that GZIP compression is used.
	 */
	public static final int COMPRESSION_GZIP = 2;

	/**
	 * Decodes and decompresses the container.
	 * @param buffer The buffer.
	 * @return The decompressed container.
	 * @throws IOException if an I/O error occurs.
	 */

	/**
	 * The type of compression this container uses.
	 */
	private int type;

	/**
	 * The decompressed data.
	 */
	private ByteBuffer data;

	/**
	 * The version of the file within this container.
	 */
	private int version;

	/**
	 * Creates a new unversioned container.
	 * @param type The type of compression.
	 * @param data The decompressed data.
	 */
	public Container(int type, ByteBuffer data) {
		this(type, data, -1);
	}

	/**
	 * Creates a new versioned container.
	 * @param type The type of compression.
	 * @param data The decompressed data.
	 * @param version The version of the file within this container.
	 */
	public Container(int type, ByteBuffer data, int version) {
		this.type = type;
		this.data = data;
		this.version = version;
	}

	/**
	 * Checks if this container is versioned.
	 * @return {@code true} if so, {@code false} if not.
	 */
	public boolean isVersioned() {
		return version != -1;
	}

	/**
	 * Gets the version of the file in this container.
	 * @return The version of the file.
	 * @throws IllegalArgumentException if this container is not versioned.
	 */
	public int getVersion() {
		if (!isVersioned())
			throw new IllegalStateException();

		return version;
	}

	/**
	 * Sets the version of this container.
	 * @param version The version.
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Removes the version on this container so it becomes unversioned.
	 */
	public void removeVersion() {
		this.version = -1;
	}

	/**
	 * Sets the type of this container.
	 * @param type The compression type.
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Gets the type of this container.
	 * @return The compression type.
	 */
	public int getType() {
		return type;
	}

	/**
	 * Gets the decompressed data.
	 * @return The decompressed data.
	 */
	public ByteBuffer getData() {
		return data;//.asReadOnlyBuffer();
	}

	/**
	 * Encodes and compresses this container.
	 * @return The buffer.
	 * @throws IOException if an I/O error occurs.
	 */
	public ByteBuffer encode() throws IOException {
		ByteBuffer data = getData(); // so we have a read only view, making this method thread safe

		/* grab the data as a byte array for compression */
		byte[] bytes = new byte[data.limit()];
		data.mark();
		data.get(bytes);
		data.reset();

		/* compress the data */
		byte[] compressed;
		if (type == COMPRESSION_NONE) {
			compressed = bytes;
		} else if (type == COMPRESSION_GZIP) {
			compressed = CompressionUtils.gzip(bytes);
		} else if (type == COMPRESSION_BZIP2) {
			compressed = CompressionUtils.bzip2(bytes);
		} else {
			throw new IOException("Invalid compression type");
		}

		/* calculate the size of the header and trailer and allocate a buffer */
		int header = 5 + (type == COMPRESSION_NONE ? 0 : 4) + (isVersioned() ? 2 : 0);
		ByteBuffer buf = ByteBuffer.allocate(header + compressed.length);

		/* write the header, with the optional uncompressed length */
		buf.put((byte) type);
		buf.putInt(compressed.length);
		if (type != COMPRESSION_NONE) {
			buf.putInt(data.limit());
		}

		/* write the compressed length */
		buf.put(compressed);

		/* write the trailer with the optional version */
		if (isVersioned()) {
			buf.putShort((short) version);
		}

		/* flip the buffer and return it */
		return (ByteBuffer) buf.flip();
	}
	
	private static final int[] NULL_KEY = new int[4];

	public static Container decode(ByteBuffer buffer) throws IOException {
		return Container.decode(buffer, NULL_KEY);
	}

	public static Container decode(ByteBuffer buffer, int[] key) throws IOException {
		/* decode the type and length */
		int type = buffer.get() & 0xFF;
		int length = buffer.getInt();

		/* decrypt */
		if (key[0] != 0 || key[1] != 0 || key[2] != 0 || key[3] != 0) {
			Xtea.decipher(buffer, 5, length + (type == COMPRESSION_NONE ? 5 : 9), key);
		}

		/* check if we should decompress the data or not */
		if (type == COMPRESSION_NONE) {
			/* simply grab the data and wrap it in a buffer */
			byte[] temp = new byte[length];
			buffer.get(temp);
			ByteBuffer data = ByteBuffer.wrap(temp);

			/* decode the version if present */
			int version = -1;
			if (buffer.remaining() >= 2) {
				version = buffer.getShort();
			}

			/* and return the decoded container */
			return new Container(type, data, version);
		} else {
			/* grab the length of the uncompressed data */
			int uncompressedLength = buffer.getInt();

			/* grab the data */
			byte[] compressed = new byte[length];
			buffer.get(compressed);

			/* uncompress it */
			byte[] uncompressed;
			if (type == COMPRESSION_BZIP2) {
				uncompressed = CompressionUtils.bunzip2(compressed);
			} else if (type == COMPRESSION_GZIP) {
				uncompressed = CompressionUtils.gunzip(compressed);
			} else {
				throw new IOException("Invalid compression type");
			}

			/* check if the lengths are equal */
			if (uncompressed.length != uncompressedLength) {
				throw new IOException("Length mismatch");
			}

			/* decode the version if present */
			int version = -1;
			if (buffer.remaining() >= 2) {
				version = buffer.getShort();
			}

			/* and return the decoded container */
			return new Container(type, ByteBuffer.wrap(uncompressed), version);
		}
	}

}
