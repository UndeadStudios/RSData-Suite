package com.suite.loaders;

import java.math.BigInteger;

public final class Stream {
	final byte readShort() {// readShort
		return (byte) (-buffer[pos++] + 128);
	}

	final byte readByte(int i) {
		return buffer[pos++];
	}

	public int readUnsignedByte() {
		return buffer[pos++] & 0xff;
	}
	public final int g2b() {
		this.pos += 2;
		int value = ((this.buffer[this.pos - 2] & 0xFF) << 8) + (this.buffer[this.pos - 1] & 0xFF);
		if (value > 32767) {
			value -= 0x10000;
		}
		return value;
	}
	public int readUnsignedByte(int shit) {
		return buffer[pos++] & 0xff;
	}

	public byte readSignedByte() {
		return buffer[pos++];
	}
	
	public byte readSignedByte(int shit) {
		return buffer[pos++];
	}


	public int readUnsignedWord() {
		pos += 2;
		return ((buffer[pos - 2] & 0xff) << 8) + (buffer[pos - 1] & 0xff);
	}
	
	public int readUnsignedShort(int arg0) {
		//	anInt4330++;
			if (arg0 != 8104)
				writeShort(60, (byte) -68);
			pos += 2;
			return ((buffer[-2 + pos] << 21257576 & 0xff00) - -(buffer[-1+ pos] & 0xff));
	}
		
		public void writeShort(int arg0, byte arg1) {
			//	anInt4314++;
				if (arg1 <= 24)
					buffer = null;
				buffer[pos++] = (byte) (arg0 >> -1846218616);
				buffer[pos++] = (byte) arg0;
		}


	public int readSignedWord() {
		pos += 2;
		int i = ((buffer[pos - 2] & 0xff) << 8) + (buffer[pos - 1] & 0xff);
		if (i > 32767)
			i -= 0x10000;
		return i;
	}
	
	public int readSignedShort(int arg0) {
			pos += 2;
			//anInt4297++;
			if (arg0 != 1625554944)
				return -65;
			int i = (((0xff & buffer[-2 + pos]) << -2024060696) - -(buffer[-1
					+ pos] & 0xff));
			if (i > 32767)
				i -= 65536;
			return i;
	}
	
	public int readUnsignedWord(int shit) {
		pos += 2;
		return ((buffer[pos - 2] & 0xff) << 8) + (buffer[pos - 1] & 0xff);
	}

	public int readSignedWord(int shit) {
		pos += 2;
		int i = ((buffer[pos - 2] & 0xff) << 8) + (buffer[pos - 1] & 0xff);
		if (i > 32767)
			i -= 0x10000;
		return i;
	}

	final int readUSmart() {// method421 c(byte
		int i = 0xff & buffer[pos];
		if (i < 128)
			return readUnsignedByte() - 64;
		return readUnsignedWord() - 49152;
	}

	final int read24BitInt(byte i) {// read24bitint
		pos += 3;
		if (i > -21)
			return 29;
		return ((0xff & buffer[-1 + pos]) + ((buffer[-2 + pos] << 8 & 0xff00) + ((buffer[-3
				+ pos] & 0xff) << 16)));
	}
	public final int g2badd() {
		this.pos += 2;
		int value = ((this.buffer[this.pos - 2] & 0xFF) << 8) + (this.buffer[this.pos - 1] - 128 & 0xFF);
		if (value > 32767) {
			value -= 0x10000;
		}
		return value;
	}
	final int h() {// readInt?
		pos += 4;
		return ((0xff & buffer[-1 + pos]) + (buffer[pos - 2] << 8 & 0xff00)
				+ (buffer[-4 + pos] << 24 & ~0xffffff) - -(0xff0000 & buffer[-3
				+ pos] << 16));
	}
	
	public int readUnsignedInt() {
			pos += 4;
			return ((buffer[-1 + pos] & 0xff)
					+ ((buffer[-4 + pos] & 0xff) << -333510440) + ((0xff0000 & buffer[pos - 3] << -1647926640) - -((0xff & buffer[-2
					+ pos]) << 20426280)));
	}
	
	public int readUnsignedInt(int shet) {
		pos += 4;
		return ((buffer[-1 + pos] & 0xff)
				+ ((buffer[-4 + pos] & 0xff) << -333510440) + ((0xff0000 & buffer[pos - 3] << -1647926640) - -((0xff & buffer[-2
				+ pos]) << 20426280)));
}

	final int v(int i) {
		pos += 3;
		return (0xff & buffer[pos - 3] << 16) + (0xff & buffer[pos - 2] << 8)
				+ (0xff & buffer[pos - 1]);
	}

	public Stream(byte abyte0[]) {
		buffer = abyte0;
		pos = 0;
	}

	public void writeByte(int i) {
		buffer[pos++] = (byte) i;
	}

	public void writeWord(int i) {
		buffer[pos++] = (byte) (i >> 8);
		buffer[pos++] = (byte) i;
	}

	public void method400(int i) {
		buffer[pos++] = (byte) i;
		buffer[pos++] = (byte) (i >> 8);
	}

	public void writeDWordBigEndian(int i) {
		buffer[pos++] = (byte) (i >> 16);
		buffer[pos++] = (byte) (i >> 8);
		buffer[pos++] = (byte) i;
	}

	public void writeDWord(int i) {
		buffer[pos++] = (byte) (i >> 24);
		buffer[pos++] = (byte) (i >> 16);
		buffer[pos++] = (byte) (i >> 8);
		buffer[pos++] = (byte) i;
	}

	public void method403(int j) {
		buffer[pos++] = (byte) j;
		buffer[pos++] = (byte) (j >> 8);
		buffer[pos++] = (byte) (j >> 16);
		buffer[pos++] = (byte) (j >> 24);
	}

	public void writeQWord(long l) {
		try {
			buffer[pos++] = (byte) (int) (l >> 56);
			buffer[pos++] = (byte) (int) (l >> 48);
			buffer[pos++] = (byte) (int) (l >> 40);
			buffer[pos++] = (byte) (int) (l >> 32);
			buffer[pos++] = (byte) (int) (l >> 24);
			buffer[pos++] = (byte) (int) (l >> 16);
			buffer[pos++] = (byte) (int) (l >> 8);
			buffer[pos++] = (byte) (int) l;
		} catch (RuntimeException runtimeexception) {
			System.out.println("14395, " + 5 + ", " + l + ", "
					+ runtimeexception.toString());
			throw new RuntimeException();
		}
	}

	public void writeString(String s) {
		// s.getBytes(0, s.length(), buffer, pos); //deprecated
		System.arraycopy(s.getBytes(), 0, buffer, pos, s.length());
		pos += s.length();
		buffer[pos++] = 10;
	}

	public void writeBytes(byte abyte0[], int i, int j) {
		for (int k = j; k < j + i; k++)
			buffer[pos++] = abyte0[k];

	}

	public int readUShort() {
		pos += 2;
		return ((buffer[pos - 2] & 0xff) << 8) + (buffer[pos - 1] & 0xff);
	}

	public int read3Bytes() {
		pos += 3;
		return ((buffer[pos - 3] & 0xff) << 16)
				+ ((buffer[pos - 2] & 0xff) << 8) + (buffer[pos - 1] & 0xff);
	}

	public int readDWord() {
		pos += 4;
		return ((buffer[pos - 4] & 0xff) << 24)
				+ ((buffer[pos - 3] & 0xff) << 16)
				+ ((buffer[pos - 2] & 0xff) << 8) + (buffer[pos - 1] & 0xff);
	}

	public long readQWord() {
		long l = readDWord() & 0xffffffffL;
		long l1 = readDWord() & 0xffffffffL;
		return (l << 32) + l1;
	}

	public String readString() {
		int i = pos;
		while (buffer[pos++] != 10)
			;
		return new String(buffer, i, pos - i - 1);
	}

	public byte[] readBytes() {
		int i = pos;
		while (buffer[pos++] != 10)
			;
		byte abyte0[] = new byte[pos - i - 1];
		System.arraycopy(buffer, i, abyte0, i - i, pos - 1 - i);
		return abyte0;
	}

	public void readBytes(int i, int j, byte abyte0[]) {
		for (int l = j; l < j + i; l++)
			abyte0[l] = buffer[pos++];
	}

	public void initBitAccess() {
		bitPosition = pos * 8;
	}

	public int readBits(int i) {
		int k = bitPosition >> 3;
		int l = 8 - (bitPosition & 7);
		int i1 = 0;
		bitPosition += i;
		for (; i > l; l = 8) {
			i1 += (buffer[k++] & anIntArray1409[l]) << i - l;
			i -= l;
		}
		if (i == l)
			i1 += buffer[k] & anIntArray1409[l];
		else
			i1 += buffer[k] >> l - i & anIntArray1409[i];
		return i1;
	}

	public void finishBitAccess() {
		pos = (bitPosition + 7) / 8;
	}

	public int method421() {
		try {
			int i = buffer[pos] & 0xff;
			if (i < 128)
				return readUnsignedByte() - 64;
			else
				return readUShort() - 49152;
		} catch (Exception e) {
			return -1;
		}
	}

	public int readSpaceSaver() {
		int i = buffer[pos] & 0xff;
		if (i < 128)
			return readUnsignedByte();
		else
			return readUShort() - 32768;
	}

	public void doKeys() {
		int i = pos;
		pos = 0;
		byte abyte0[] = new byte[i];
		readBytes(i, 0, abyte0);
		BigInteger biginteger2 = new BigInteger(abyte0);
		BigInteger biginteger3 = biginteger2/* .modPow(biginteger, biginteger1) */;
		byte abyte1[] = biginteger3.toByteArray();
		pos = 0;
		writeByte(abyte1.length);
		writeBytes(abyte1, abyte1.length, 0);
	}

	public void method424(int i) {
		buffer[pos++] = (byte) (-i);
	}

	public void method425(int j) {
		buffer[pos++] = (byte) (128 - j);
	}

	public int method426() {
		return buffer[pos++] - 128 & 0xff;
	}

	public int method427() {
		return -buffer[pos++] & 0xff;
	}

	public int readUByte128() {
		return 128 - buffer[pos++] & 0xff;
	}

	public byte readNegativeByte() {
		return (byte) (-buffer[pos++]);
	}

	public byte readByte128() {
		return (byte) (128 - buffer[pos++]);
	}

	public void writeLEShort(int i) {
		buffer[pos++] = (byte) i;
		buffer[pos++] = (byte) (i >> 8);
	}

	public void writeShort128(int j) {
		buffer[pos++] = (byte) (j >> 8);
		buffer[pos++] = (byte) (j + 128);
	}

	public void writeLEShort128(int j) {
		buffer[pos++] = (byte) (j + 128);
		buffer[pos++] = (byte) (j >> 8);
	}

	public int method434() {
		pos += 2;
		return ((buffer[pos - 1] & 0xff) << 8) + (buffer[pos - 2] & 0xff);
	}

	public int readUShort128() {
		pos += 2;
		return ((buffer[pos - 2] & 0xff) << 8) + (buffer[pos - 1] - 128 & 0xff);
	}

	public int method436() {
		pos += 2;
		return ((buffer[pos - 1] & 0xff) << 8) + (buffer[pos - 2] - 128 & 0xff);
	}

	public int method437() {
		pos += 2;
		int j = ((buffer[pos - 1] & 0xff) << 8) + (buffer[pos - 2] & 0xff);
		if (j > 32767)
			j -= 0x10000;
		return j;
	}

	public int method438() {
		pos += 2;
		int j = ((buffer[pos - 1] & 0xff) << 8)
				+ (buffer[pos - 2] - 128 & 0xff);
		if (j > 32767)
			j -= 0x10000;
		return j;
	}

	public int method439() {
		pos += 4;
		return ((buffer[pos - 2] & 0xff) << 24)
				+ ((buffer[pos - 1] & 0xff) << 16)
				+ ((buffer[pos - 4] & 0xff) << 8) + (buffer[pos - 3] & 0xff);
	}

	public int method440() {
		pos += 4;
		return ((buffer[pos - 3] & 0xff) << 24)
				+ ((buffer[pos - 4] & 0xff) << 16)
				+ ((buffer[pos - 1] & 0xff) << 8) + (buffer[pos - 2] & 0xff);
	}

	public void method441(int i, byte abyte0[], int j) {
		for (int k = (i + j) - 1; k >= i; k--)
			buffer[pos++] = (byte) (abyte0[k] + 128);

	}

	public void method442(int i, int j, byte abyte0[]) {
		for (int k = (j + i) - 1; k >= j; k--)
			abyte0[k] = buffer[pos++];

	}

	public byte buffer[];
	public int pos;
	public int bitPosition;
	private static final int[] anIntArray1409 = { 0, 1, 3, 7, 15, 31, 63, 127,
			255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 0x1ffff,
			0x3ffff, 0x7ffff, 0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff,
			0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff, 0x3fffffff,
			0x7fffffff, -1 };
	private static int anInt1412;
	
	public void method1744(int arg0, int arg1) {
	//	try {
		//	anInt4343++;
			if (arg1 != 65280)
				buffer = null;
			buffer[-4 + pos + -arg0] = (byte) (arg0 >> 550543064);
			buffer[pos + (-arg0 + -3)] = (byte) (arg0 >> 2049230864);
			buffer[-2 + (-arg0 + pos)] = (byte) (arg0 >> -192297144);
			buffer[-1 + pos - arg0] = (byte) arg0;
	}
	
	public String readString(boolean arg0) {
		//	anInt4356++;
			int i = pos;
			while (buffer[pos++] != 0) {
				/* empty */
			}
			int i_36_ = -1 + (pos + -i);
			if (i_36_ == 0)
				return "";
			if (arg0 != false)
				method1744(-45, -112);
			/*
			 * try { if (Class131_Sub8_Sub1.method1682(i_36_, i, 4238,
			 * buffer).equalsIgnoreCase("Bank Booth")) { throw new
			 * Exception("its here"); } } catch (Exception e) {
			 * e.printStackTrace(); }
			 */

			return method1682(i_36_, i, 4238, buffer);
	}
	
	public static char[] aCharArray6385 = { '\u20ac', '\0', '\u201a', '\u0192',
		'\u201e', '\u2026', '\u2020', '\u2021', '\u02c6', '\u2030',
		'\u0160', '\u2039', '\u0152', '\0', '\u017d', '\0', '\0', '\u2018',
		'\u2019', '\u201c', '\u201d', '\u2022', '\u2013', '\u2014',
		'\u02dc', '\u2122', '\u0161', '\u203a', '\u0153', '\0', '\u017e',
		'\u0178' };
	
	public static String method1682(int arg0, int arg1, int arg2, byte[] arg3) {
			//anInt6090++;
			char[] cs = new char[arg0];
			int i = 0;
			for (int i_6_ = 0; i_6_ < arg0; i_6_++) {
				int i_7_ = 0xff & arg3[i_6_ + arg1];
				if ((i_7_ ^ 0xffffffff) != -1) {
					if ((i_7_ ^ 0xffffffff) <= -129
							&& (i_7_ ^ 0xffffffff) > -161) {
						int i_8_ = aCharArray6385[i_7_ - 128];
						if (i_8_ == 0)
							i_8_ = 63;
						i_7_ = i_8_;
					}
					cs[i++] = (char) i_7_;
				}
			}
		//	if (arg2 != 4238)
		//		aClass131_Sub1_Sub1_6106 = null;
			return new String(cs, 0, i);
	}

	final int readShort(byte i) {
		/*	if (i != 127)
				aClass58_3993 = null;*/
			pos += 2;
			int value = (((buffer[pos - 1]) & 0xff) + (((buffer[-2+ pos]) & 0xff) << -1546530360));
			/*if (value == 8040) {
				try {
					throw new Exception("reached");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}*/
			return value;
	}
	
    static char[] aCharArray497
    = {'\u20ac', '\0', '\u201a', '\u0192', '\u201e', '\u2026', '\u2020',
    '\u2021', '\u02c6', '\u2030', '\u0160', '\u2039', '\u0152', '\0',
    '\u017d', '\0', '\0', '\u2018', '\u2019', '\u201c', '\u201d',
    '\u2022', '\u2013', '\u2014', '\u02dc', '\u2122', '\u0161',
    '\u203a', '\u0153', '\0', '\u017e', '\u0178'};
	
    static final String method1546(int i, int i_0_, byte i_1_, byte[] is) {
	    char[] cs = new char[i];
	    int i_2_ = 0;
	    //if (i_1_ >= -49)
		//method1548(-78, 47, 80, 103, (byte) -48, -72, 123);
	    for (int i_3_ = 0; (i_3_ ^ 0xffffffff) > (i ^ 0xffffffff);
		 i_3_++) {
		int i_4_ = is[i_0_ + i_3_] & 0xff;
		if ((i_4_ ^ 0xffffffff) != -1) {
		    if ((i_4_ ^ 0xffffffff) <= -129 && i_4_ < 160) {
			int i_5_ = aCharArray497[-128 + i_4_];
			if (i_5_ == 0)
			    i_5_ = 63;
			i_4_ = i_5_;
		    }
		    cs[i_2_++] = (char) i_4_;
		}
	    }
	    return new String(cs, 0, i_2_);
    }
    
	final int readInt1(boolean bool) {
			pos += 4;
			return (((buffer[-4
					+ pos]) << -1188328504 & 0xff00)
					+ ((0xff0000 & ((buffer[pos - 1]) << 340284016)) + (((buffer[pos
							+ -2]) & 0xff) << -2077360904)) - -((buffer[-3
					+ pos]) & 0xff));
	}
	
	final String readString(byte i) {
			int i_18_ = pos;
			if (i != 84)
				readInt1(true);
			while (((buffer[pos++]) ^ 0xffffffff) != -1) {
				/* empty */
			}
			int i_19_ = -i_18_ + pos - 1;
			if (i_19_ == 0)
				return "";
			String output = method1546(i_19_, i_18_, (byte) -64, buffer);
			/*if (output.equalsIgnoreCase("Bank booth")) {
				try {
					throw new Exception("Here");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}*/
			return output;
	}

	final int readUShort(boolean bool) { //h
			pos += 2;
			/*if (bool != false)
				method1244(45, (byte) 31);*/
			int i = ((0xff00 & ((buffer[pos
					+ -2]) << 1357336488)) - -((buffer[pos
					+ -1]) & 0xff));
			if (i > 32767)
				i -= 65536;
			return i;
	}
	
	final int readShort1(byte i) {
			pos += 2;
			return ((0xff & (buffer[-2
					+ pos])) + (((buffer[-1
					+ pos]) & 0xff) << 850116168));
	}

	final int readInt(int i) {
			pos += 4;
			if (i != -2)
				readShort1((byte) -76);
			return ((0xff & (buffer[-1
					+ pos]))
					+ ((0xff0000 & ((buffer[-3
							+ pos]) << 326096944)) + ((0xff & (buffer[-4
							+ pos])) << 1206151768)) + ((buffer[-2
					+ pos]) << -1753478520 & 0xff00));
	}

	final int method1186(int i) {
			pos += 3;
			return (((buffer[-1
					+ pos]) & 0xff)
					+ ((0xff & (buffer[-2
							+ pos])) << 1155233704) + (((buffer[pos
					+ -3]) & 0xff) << 251467472));
	}

	final int readSmart(int i) {
			int i_13_ = 0xff & buffer[pos];
			if (i_13_ < 128)
				return readUnsignedByte((byte) -115);
			return -32768 + readShort((byte) 127);
	}
	
	final int readShorter(byte i) {
			pos += 2;
			int value = (((buffer[pos - 1]) & 0xff) + (((buffer[-2+ pos]) & 0xff) << -1546530360));
			/*if (value == 8040) {
				try {
					throw new Exception("reached");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}*/
			return value;
	}
	
	// removed useless static initializer
}
