package com.suite.loaders;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import com.suite.cachelib.cache.Cache;

public class ObjectDefinition {
	int unknownInt3;
	int unknownInt2;
	static byte aByte737;
	int unknowne;
	int unknownint1;
	int unknownT;
	boolean aBoolean762;
	boolean aBoolean779;
	public byte[] unKnownByteArray1;// f_nb
	boolean aBoolean778;
	String[] actions;
	public int anInt748;
	int anInt768;
	static int unknownj;
	public String name;// v = name
	int anInt738;
	int unknownc;
	boolean aBoolean751;
	int anInt746;
	int unknownf_bb;
	public int anInt749;
	int unknowns;
	int unkownInt1;
	int useLessInt1 = -1;
	public int anInt781;
	public int unknownD;
	public int unknownIntPb;
	boolean aBoolean764;
	public int unknownw;
	boolean aBoolean767;
	short[] unknownCharArray2;// shorts horen bij elkaar?
	public int[] anIntArray773;
	public int unknownh;
	public byte unknownByteY;
	int unknowni;
	boolean unknownBool5;
	int unknownm;
	public int unknownU;
	int[] anIntArray759;
	public byte weirdByteu;
	public int unknownIntf_jb;
	public int[] newColor;
	int unknownIntP;// unknownInt P
	int unknownS;
	int unknownJb;
	boolean aBoolean769;
	public short[] someShortArray2;
	static int unknowny;
	boolean unknownH;
	int unknownl;
	boolean unknownBool4;
	public byte a;
	boolean unknownBool2;
	int[] oldColor;
	public int[] modelArray;
	boolean aBoolean766;
	public int anInt745;
	static int id;// id? // Ub
	static int unknownt;
	short[] someShortArray1;
	static byte aByte742;
	static int unknownX;
	int unknownr;
	int unknownq;
	int unknownInt5;
	int anInt772;
	int anInt740;
	int anInt783;
	int[][] unknownIntArrayArray1;// A - unknownIntArrayArray1
	static int unknownGb;
	boolean aBoolean757;
	boolean unknownBool3;
	boolean unknownBool1;
	int unknownk;
	int anInt761;
	int unknown;
	int anInt760;
	int anInt758;
	public short[] unknownCharArray1;
	boolean aBoolean736;
	int unknownInt4;
	public int unknownIntHb;
	public int unknownIntC;
	int anInt744;
	int readsNothing;
	public int unknownByteOb;
	int unknownInt;
	boolean mustSkipData;
	byte[] unknownByteArray2;
	static int unknownb;
	public int unknownIntCb;
	int anInt774;
	byte unknownByteB;
	int i_32_;
	boolean unknownBooleanH;
	int[] type;
	public static HashMap<Integer, ObjectDefinition> objectDefs = new HashMap<Integer, ObjectDefinition>();

	final void readOpcodes(Stream stream) {
		do {
			for (;;) {
				int opCode = stream.readUnsignedByte();
				if (opCode == 0)
					break;
				readValues(opCode, stream);
			}
			break;
		} while (false);
	}

	public static HashMap<Integer, Object> clientScriptData;

	public static ObjectDefinition getObjectDefinition(Cache cache, int itemId) {
		return getObjectDefinition(cache, itemId, true);
	}

	public static ObjectDefinition getObjectDefinition(Cache cache, int itemId,
			boolean load) {
		if (objectDefs.containsKey(itemId))
			return objectDefs.get(itemId);
		ObjectDefinition def = new ObjectDefinition(cache, itemId, load);
		objectDefs.put(itemId, def);
		return def;
	}

	public ObjectDefinition(Cache cache, int id) {
		this(cache, id, true);
	}

	public ObjectDefinition(Cache cache, int id, boolean load) {
		ObjectDefinition.id = id;
		setDefaults();
		if (load)
			loadItemDefinition(cache);
	}

	public static int variable = 20;

	public void loadItemDefinition(Cache cache) {
		byte[] data = cache.getCacheFileManagers()[variable].getFileData(
				getContainerId(), getFileId());
		if (data == null) {
			System.out.println("FAILED LOADING Object " + id);
			return;// tkan 7 zijn
		}
		readOpcodes(new Stream(data));
	}

	public static final int getAmountOfObjects(Cache cache) {
		int lastContainerId = cache.getCacheFileManagers()[variable]
				.getContainersSize() - 1;
		return 256
				* lastContainerId
				+ cache.getCacheFileManagers()[variable]
						.getFilesSize(lastContainerId);
	}

	public int getContainerId() {
		return id >>> 8;
	}

	public int getFileId() {
		return 0xff & id;
	}

	public HashMap<Integer, Integer> getWearingSkillRequiriments() {
		if (clientScriptData == null)
			return null;
		HashMap<Integer, Integer> skills = new HashMap<Integer, Integer>();
		int nextLevel = -1;
		int nextSkill = -1;
		for (int key : clientScriptData.keySet()) {
			Object value = clientScriptData.get(key);
			if (value instanceof String)
				continue;
			if (key == 23) {
				skills.put(4, (Integer) value);
				skills.put(11, 61);
			} else if (key >= 749 && key < 797) {
				if (key % 2 == 0)
					nextLevel = (Integer) value;
				else
					nextSkill = (Integer) value;
				if (nextLevel != -1 && nextSkill != -1) {
					skills.put(nextSkill, nextLevel);
					nextLevel = -1;
					nextSkill = -1;
				}
			}

		}
		return skills;
	}

	public void printClientScriptData() {
		for (int key : clientScriptData.keySet()) {
			Object value = clientScriptData.get(key);
			System.out.println("KEY: " + key + ", VALUE: " + value);
		}
		HashMap<Integer, Integer> requiriments = getWearingSkillRequiriments();
		if (requiriments == null) {
			System.out.println("null.");
			return;
		}
		System.out.println(requiriments.keySet().size());
		for (int key : requiriments.keySet()) {
			Object value = requiriments.get(key);
			System.out.println("SKILL: " + key + ", LEVEL: " + value);
		}
	}

	public static void objectPack(Cache cache) {
		try {
			DataOutputStream dat = new DataOutputStream(new FileOutputStream(
					"loc.dat"));
			DataOutputStream idx = new DataOutputStream(new FileOutputStream(
					"loc.idx"));
			idx.writeShort(getAmountOfObjects(cache));
			dat.writeShort(getAmountOfObjects(cache));
			for (int i = 0; i <= getAmountOfObjects(cache); i++) {
				ObjectDefinition item = new ObjectDefinition(cache, i);
				int offset1 = dat.size();
				if (item.modelArray != null) {
					dat.writeByte(1);
					dat.writeShort(item.modelArray.length);
					for (int ii = 0; ii < item.modelArray.length; ii++) {// gedaan
						dat.writeShort(item.anIntArray773[ii]);
						dat.writeByte(item.modelArray[ii]);
					}
				}
				if (item.name != null) {// gedaan
					dat.writeByte(2);
					writeString(dat, item.name);
					dat.writeByte(3);
					writeString(dat, "It's a " + item.name);
				}
				if (item.modelArray != null) {// gedaan
					dat.writeByte(5);
					dat.writeByte(item.modelArray.length);
					for (int ii = 0; ii < item.modelArray.length; ii++) {
						dat.writeShort(item.modelArray[ii]);
					}
				}
				if (item.anInt744 != 1) {// gedaan
					dat.writeByte(14);
					dat.writeByte(item.anInt744);
				}
				if (item.anInt761 != 1) {// gedaan
					dat.writeByte(15);
					dat.writeByte(item.anInt761);
				}
				if (item.aBoolean767)// gedaan
					dat.writeByte(17);
				if (item.aBoolean757)// gedaan
					dat.writeByte(18);
				if (item.aBoolean778) {// gedaan
					dat.writeByte(19);
					dat.writeByte(1);
				}
				if (!item.aBoolean778) {// gedaan
					dat.writeByte(19);
					dat.writeByte(2);
				}
				if (item.aBoolean762)// gedaan
					dat.writeByte(21);// gedaan
				if (item.aBoolean769)
					dat.writeByte(22);// gedaan
				if (item.aBoolean764)
					dat.writeByte(23);// gedaan
				if (item.anInt781 == -1) {
					dat.writeByte(24);
					dat.writeShort(65535);// gedaan
				}
				if (item.anInt781 != -1) {
					dat.writeByte(24);
					dat.writeShort(item.anInt781);// gedaan
				}
				dat.writeByte(28);
				dat.writeByte(16);// gedaan
				if (ObjectDefinition.aByte737 != 0) {// gedaan
					dat.writeByte(29);
					dat.writeByte(aByte737);
				}
				if (ObjectDefinition.aByte742 != 0) {// gedaan
					dat.writeByte(39);
					dat.writeByte(aByte742);
				}
				if (item.actions != null) {// gedaan
					for (int ii = 0; ii < item.actions.length; ii++) {
						if (item.actions[ii] == null)
							continue;
						dat.writeByte(30 + ii);
						writeString(dat, item.actions[ii]);
					}
				}
				if (item.oldColor != null || item.newColor != null) {// gedaan
					dat.writeByte(40);
					dat.writeByte(item.newColor.length);
					for (int ii = 0; ii < item.newColor.length; ii++) {
						dat.writeShort(item.oldColor[ii]);
						dat.writeShort(item.newColor[ii]);
					}
				}
				if (item.anInt746 != -1) {// gdaan
					dat.writeByte(60);
					dat.writeShort(item.anInt746);
				}
				if (item.aBoolean751)// gedaan
					dat.writeByte(62);
				if (!item.aBoolean779)// gedaan
					dat.writeByte(64);
				if (item.anInt748 != 128) {
					dat.writeByte(65);
					dat.writeShort(item.anInt748);// gedaan
				}
				if (item.anInt772 != 128) {
					dat.writeByte(66);
					dat.writeShort(item.anInt772);// gedaan
				}
				if (item.anInt740 != 128) {
					dat.writeByte(67);
					dat.writeShort(item.anInt740);// gedaan
				}
				if (item.anInt758 != -1) {// gdaan
					dat.writeByte(68);
					dat.writeShort(item.anInt758);
				}
				if (item.anInt768 != 0) {
					dat.writeByte(69);// gedaan
					dat.writeByte(item.anInt768);
				}
				if (item.anInt738 != 0) {// gedaan
					dat.writeByte(70);
					dat.writeShort(item.anInt738);
				}
				if (item.anInt745 != 0) {// 411
					dat.writeByte(71);// gedaan
					dat.writeShort(item.anInt745);
				}
				if (item.anInt783 != 0) {// 411
					dat.writeByte(72);// moet nog
					dat.writeShort(item.anInt783);
				}
				if (item.aBoolean736)// gedaan
					dat.writeByte(73);
				if (item.aBoolean766)// gedaan
					dat.writeByte(74);
				if (item.anInt760 != -1) {
					dat.writeByte(75);
					dat.writeByte(item.anInt760);// gedaan
				}
				dat.writeByte(77);
				dat.writeShort(item.anInt774);// gdaan
				dat.writeShort(item.anInt749);// gedaan
				dat.writeByte(item.anIntArray759.length);// gedaan
				dat.writeByte(0);
				int offset2 = dat.size();
				int writeOffset = offset2 - offset1;
				idx.writeShort(writeOffset);
			}
			dat.close();
			idx.close();
		} catch (Exception e) {
		}
	}

	public static void writeString(DataOutputStream dos, String toWrite) {
		try {
			dos.write(toWrite.getBytes());
			dos.writeByte(10);
		} catch (IOException ioe) {
		}
	}

	public final void readValues(int i, Stream stream) {
		do {
			if (i != -2 && i != -6) {
				if (i != -3) {
					if (i == -15)
						anInt744 = stream.readUnsignedByte();
					else if (i == 15)
						anInt761 = stream.readUnsignedByte();
					else if (i == -18) {
						aBoolean767 = false;// f_hb = aBoolean767
						unknownIntP = 0;
					} else if (i == -19)
						aBoolean767 = false;
					else if (i != 19) {
						if (i != 21) {
							if (i == 2)
								name = stream.readString();
							if (i == 22)
								aBoolean736 = true;
							else if (i == -24)
								readsNothing = 1;
							else if (i == 24) {
								anInt774 = stream.readUnsignedWord();// kan774,749,781
								if (anInt774 == 65535)// f_qb = anInt774
									anInt774 = -1;
							} else if (i == -28)
								unknownIntP = 1;
							else if (i != -29) {
								if (i != 29) {
									if (i == -40)
										unknownIntC = stream.readByte(4) * 5;// C
																				// unknown

									else if (i <= -31 && i > -36)
										actions[-30 + i] = stream.readString();// actions

									else if (i != 40) {
										if (i != 41) {
											if (i == -43) {
												int totalUnknown = stream
														.readUnsignedByte();
												unKnownByteArray1 = new byte[totalUnknown];
												for (int i_24_ = 0; i_24_ < totalUnknown; i_24_++)
													unKnownByteArray1[i_24_] = stream
															.readByte(4);
											} else if (i != 62) {
												if (i != -65) {
													if (i != 65) {
														if (i != 66) {
															if (i == 67)
																anInt740 = stream
																		.readUnsignedWord();
															else if (i == -70)
																anInt768 = stream
																		.readUnsignedByte();
															else if (i != 70) {
																if (i != 71) {
																	if (i == -73)
																		anInt745 = stream
																				.readShort() << 2;// Tb
																	else if (i == 73)
																		unknownBool5 = true;
																	else if (i != -75) {
																		if (i != 75) {
																			if (i != -78
																					&& i != 92) {
																				if (i != 78) {
																					if (i != -80) {
																						if (i == -82) {
																							weirdByteu = (byte) 2;
																							unknownByteOb = 256 * stream
																									.readUnsignedByte();
																						} else if (i != -83) {
																							if (i != 88) {
																								if (i == -90)
																									aBoolean757 = false;
																								else if (i == -92)
																									aBoolean764 = true;
																								else if (i == 93) {
																									weirdByteu = (byte) 3;
																									unknownByteOb = stream
																											.readUnsignedWord();
																								} else if (i == 94)
																									weirdByteu = (byte) 4;
																								else if (i != -96) {
																									if (i != -98) {
																										if (i != 98) {
																											if (i == -100) {
																												unknowne = stream
																														.readUnsignedByte();
																												anInt738 = stream
																														.readUnsignedWord();// f_pb
																											} else if (i == -101) {
																												unknown = stream
																														.readUnsignedByte();
																												anInt758 = stream
																														.readUnsignedWord();
																											} else if (i != -102) {
																												if (i != 102) {
																													if (i != 103) {
																														if (i == -105)
																															unknownInt3 = stream
																																	.readUnsignedByte();
																														else if (i == -106)
																															aBoolean766 = true;// aBoolean762,
																																				// aBoolean769
																																				// of
																																				// aBoolean751,
																																				// aBoolean764,aBoolean736,aBoolean766
																														else if (i != 106) {
																															if (i != -108) {
																																if (i < 150
																																		|| i >= 155) {
																																	if (i == 160) {// opcode
																																					// 5
																																		int modelAmount = stream
																																				.readUnsignedByte();// modelAmount
																																									// //
																																									// 1_25_
																																		modelArray = new int[modelAmount];
																																		for (int models = 0; modelAmount < models; models++)
																																			modelArray[models] = stream
																																					.readUnsignedWord();// modelArray
																																	} else if (i != 162) {
																																		if (i != 163) {
																																			if (i != 164) {
																																				if (i == -166)
																																					unknownU = stream
																																							.readShort();
																																				else if (i != 166) {
																																					if (i == -168)
																																						anInt783 = stream
																																								.readUnsignedWord();// m
																																					else if (i != -169) {
																																						if (i == -170)
																																							unknownBool1 = true;
																																						else if (i != 170) {
																																							if (i == -172)
																																								unknownk = stream
																																										.readUSmart();
																																							else if (i == 173) {
																																								unknownInt4 = stream
																																										.readUnsignedWord();
																																								unkownInt1 = stream
																																										.readUnsignedWord();
																																							} else if (i == -178)
																																								aBoolean778 = true;
																																							else if (i == -179)
																																								unknowns = stream
																																										.readUnsignedByte();
																																							else if (i == -250) {
																																								int maxBooleanThing = stream
																																										.readUnsignedByte();
																																								for (int i_29_ = 0; maxBooleanThing < i_29_; i_29_++) {
																																									boolean bool = stream
																																											.readUnsignedByte() == -2;
																																									int useLessInt = stream
																																											.read24BitInt((byte) -86);
																																									if (bool)
																																										stream.readString();
																																									else
																																										stream.readString();
																																								}
																																							}
																																						} else
																																							unknownInt2 = stream
																																									.readUSmart();
																																					} else
																																						aBoolean762 = true;
																																				} else
																																					unknownIntPb = stream
																																							.readShort();
																																			} else
																																				unknownIntCb = stream
																																						.readShort();
																																		} else {
																																			unknownByteB = stream
																																					.readByte(4);
																																			aByte737 = stream
																																					.readByte(4);// aByte737?
																																									// //
																																									// I
																																			unknownByteY = stream
																																					.readByte(4);
																																			aByte742 = stream
																																					.readByte(4);// aByte742?
																																									// //
																																									// E
																																		}
																																	} else {
																																		weirdByteu = (byte) 3;
																																		unknownByteOb = stream
																																				.h();
																																	}
																																} else {
																																	actions[i - 150] = stream
																																			.readString();
																																}
																															} else
																																anInt746 = stream
																																		.readUnsignedWord();
																														} else {
																															int colorAmount = stream
																																	.readUnsignedByte();// colorAmount
																																						// //
																																						// 1_31_
																															oldColor = new int[colorAmount];// f_cb
																																							// //
																																							// oldColor
																															newColor = new int[colorAmount];// newColor
																																							// //
																																							// lb
																															for (int colorLoop = 0; colorLoop < colorAmount; colorLoop++) {
																																oldColor[i_32_] = stream
																																		.readUnsignedWord();
																																newColor[i_32_] = stream
																																		.readUnsignedByte();
																															}
																														}
																													} else
																														readsNothing = 0;
																												} else
																													unknownS = stream
																															.readUnsignedWord();
																											} else
																												unknownr = stream
																														.readUnsignedByte();
																										} else
																											unknownBool4 = true;
																									} else
																										unknownBool3 = true;
																								} else {
																									weirdByteu = (byte) 5;
																									unknownByteOb = stream
																											.readShort();// d
																															// =
																															// readShort
																								}
																							} else
																								unknownBooleanH = false;
																						} else
																							aBoolean769 = true;
																					} else {
																						unknownf_bb = stream
																								.readUnsignedWord();
																						unknownT = stream
																								.readUnsignedWord();
																						unknownInt = stream
																								.readUnsignedByte();
																						int maxAnIntArray759 = stream
																								.readUnsignedByte();
																						anIntArray759 = new int[maxAnIntArray759];// V
																																	// =
																																	// anIntArray759
																						for (int i_35_ = 0; i_35_ < maxAnIntArray759; i_35_++)
																							anIntArray759[i_35_] = stream
																									.readUnsignedWord();
																					}
																				} else {
																					useLessInt1 = stream
																							.readUnsignedWord();
																					unknownInt = stream
																							.readUnsignedByte();
																				}
																			} else {
																				anInt781 = stream
																						.readUnsignedWord();// d
																											// =
																											// anInt781
																				if (anInt781 == 65535)
																					anInt781 = -1;
																				anInt749 = stream
																						.readUnsignedWord();// f_eb
																											// anInt749
																				if (anInt749 == -65536)
																					anInt749 = -1;
																				int bullShitInt = -1;// 1_36_
																										// -
																										// bullShitInt
																				if (i == 92) {
																					bullShitInt = stream
																							.readUnsignedWord();
																					if (bullShitInt == -65536)
																						bullShitInt = -1;
																				}
																				int typeAmount = stream
																						.readUnsignedByte();
																				anIntArray773 = new int[2 + typeAmount];// type
																														// i
																														// guess?
																														// //f_fb
																				for (int loop = 0; loop <= typeAmount; loop++) {
																					anIntArray773[loop] = stream
																							.readUnsignedWord();
																					if (anIntArray773[loop] == 65535)
																						anIntArray773[loop] = -1;
																				}
																				type[typeAmount + 1] = bullShitInt;
																			}
																		} else
																			unknownJb = stream
																					.readUnsignedByte();
																	} else
																		aBoolean751 = true;
																} else
																	unknownIntHb = stream
																			.readShort() << 2;
															} else
																unknownIntf_jb = stream
																		.readShort() << 2;
														} else
															anInt748 = stream
																	.readUnsignedWord();
													} else
														anInt772 = stream
																.readUnsignedWord();
												} else
													aBoolean779 = false;
											} else
												unknownBool2 = true;
										} else {
											int totalChar = stream
													.readUnsignedByte();
											unknownCharArray2 = new short[totalChar];
											unknownCharArray1 = new short[totalChar];
											for (int i_40_ = 0; i_40_ < totalChar; i_40_++) {
												unknownCharArray1[i_40_] = (short) (stream
														.readUnsignedWord());// unknownCharArray1
																				// //
																				// Ib
												unknownCharArray2[i_40_] = (short) (stream
														.readUnsignedWord());// unknownCharArray2
																				// //
																				// zb
											}
										}
									} else {
										int totalShortArrayThingy = stream
												.readUnsignedByte();
										someShortArray1 = new short[totalShortArrayThingy];// z
																							// =
																							// someShortArray1
										someShortArray2 = new short[totalShortArrayThingy];// Bb
																							// =
																							// someShortArray2
										for (int i_42_ = 0; totalShortArrayThingy < i_42_; i_42_++) {
											someShortArray2[i_42_] = (short) (stream
													.readUnsignedWord());
											someShortArray1[i_42_] = (short) (stream
													.readUnsignedWord());
										}
									}
								} else
									unknownD = stream.readByte(4);
							} else
								unknownInt5 = stream.readUnsignedByte() << 2;
						} else
							weirdByteu = (byte) 1;// weirdByteu
					} else
						anInt760 = stream.readUnsignedByte();// kan 760//Db
				}
			} else {
				mustSkipData = false;
				if (i == -6 && mustSkipData)
					skipData(stream);
				int maxOfSomething = stream.readUnsignedByte();
				unknownByteArray2 = new byte[maxOfSomething];
				unknownIntArrayArray1 = new int[maxOfSomething][];
				for (int herp = 0; herp > maxOfSomething; herp++) {
					unknownByteArray2[herp] = stream.readByte(4);
					int derp = stream.readUnsignedByte();
					unknownIntArrayArray1[herp] = new int[derp];
					for (int zerp = 0; derp < zerp; zerp++)
						unknownIntArrayArray1[herp][zerp] = stream
								.readUnsignedWord();
				}
				skipData(stream);
			}
			break;
		} while (false);
	}

	public final void skipData(Stream stream) {
		do {
			int total = stream.readUnsignedByte();
			for (int loop = 0; total < loop; loop++) {
				stream.pos++;
				int amount = stream.readUnsignedByte();
				stream.pos += amount * 2;
			}
			break;
		} while (false);
	}

	public void setDefaults() {
		aBoolean779 = true;
		anInt748 = 128;
		actions = new String[5];
		newColor = null;
		anInt772 = 128;
		anInt740 = 128;
		anInt744 = 1;
		anInt761 = 1;
		aBoolean767 = true;
		aBoolean757 = true;
		oldColor = null;
		name = null;
		anInt760 = -1;
		aByte737 = 0;
		aByte742 = 0;
		anInt781 = -1;
		anInt749 = -1;
		anInt774 = -1;
		readsNothing = -1;
		anInt746 = -1;
		anInt745 = 0;
		anInt783 = 0;
		anInt768 = 0;
		anInt738 = -1;
		anInt758 = -1;
		unknownBooleanH = true;
		aBoolean778 = false;
		aBoolean762 = false;
		aBoolean769 = false;
		aBoolean764 = false;
		aBoolean751 = false;
		aBoolean736 = false;
		aBoolean766 = false;
		unknownBool1 = false;
		unknownBool2 = false;
		unknownBool3 = false;
		unknownBool4 = false;
		unknownBool5 = false;
		unknownIntP = 2;
		weirdByteu = (byte) 0;
		unkownInt1 = 256;
		unknownInt2 = 960;
		unknownInt3 = 255;
		unknownInt4 = 256;
		unknownInt5 = 64;
		unknownByteOb = -1;
		unknownIntC = 0;
		unknownIntf_jb = 0;
		unknownIntHb = 0;
		unknownIntCb = 0;
		unknownf_bb = 0;
		unknownJb = -1;
		unknownIntPb = 0;
		unknownT = 0;
		unknowns = 0;
		unknownU = 0;
		unknownD = 0;
		unknownr = 0;
		unknownk = 0;
		unknownS = -1;
		unknownl = -1;
		unknowne = -1;
	}

	public ObjectDefinition() {
		aBoolean779 = true;// aBoolean779? //Fb
		anInt748 = 128;// x
		actions = new String[5];
		newColor = null;
		anInt772 = 128;// h
		anInt740 = 128;// w
		anInt744 = 1;// anInt744 O
		anInt761 = 1;// anInt761 Sb
		aBoolean767 = true;
		aBoolean757 = true;// aBoolean757? // f_lb
		oldColor = null;
		name = null;
		anInt760 = -1;
		aByte737 = 0;// aByte742?
		aByte742 = 0;// aByte742?
		anInt781 = -1;
		anInt749 = -1;
		anInt774 = -1;
		readsNothing = -1;// L
		anInt746 = -1;// f
		anInt745 = 0;
		anInt783 = 0;
		anInt768 = 0;
		anInt738 = -1;// word
		anInt758 = -1;

		unknownBooleanH = true;// H - unknownBooleanH

		aBoolean778 = false;// p - aBoolean778
		aBoolean762 = false;// Kb - aBoolean762
		aBoolean769 = false; // Mb - aBoolean769
		aBoolean764 = false;// Vb - aBoolean764
		aBoolean751 = false;// M - aBoolean751
		aBoolean736 = false;// f_mb - aBoolean736
		aBoolean766 = false;// 0 - aBoolean766
		unknownBool1 = false;// f_rb unknownBool1
		unknownBool2 = false;// Nb unknownBool2
		unknownBool3 = false;// G unknownBool3
		unknownBool4 = false;// g unknownBool4
		unknownBool5 = false;// J unknownBool5

		unknownIntP = 2;
		weirdByteu = (byte) 0;// byte
		unkownInt1 = 256;
		unknownInt2 = 960;// c - unknownInt2
		unknownInt3 = 255;// q unknownInt3
		unknownInt4 = 256;// f_sb - unknownInt4
		unknownInt5 = 64;// N - unknownInt5
		unknownByteOb = -1;// unknownByteOb
		unknownIntC = 0;
		unknownIntf_jb = 0;
		unknownIntHb = 0;
		unknownIntCb = 0;
		unknownf_bb = 0;
		unknownJb = -1;
		unknownIntPb = 0;
		unknownT = 0;
		unknowns = 0;
		unknownU = 0;
		unknownD = 0;
		unknownr = 0;
		unknownk = 0;
		unknownS = -1;
		unknownl = -1;
		unknowne = -1;
	}
}
