package com.suite.loaders;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import com.suite.cachelib.cache.Cache;
import com.suite.cachelib.util.Utils;

public class ObjectDefinition634 {
	boolean aBoolean757;
	int[] anIntArray2926;
	boolean aBoolean2927;
	int[] alternative_ids;
	private int modelSizeY;
	private byte aByte2930;
	private int brightness = 0;
	private byte aByte2932;
	int anInt2933;
	int[] modelArray;
	boolean aBoolean2935;
	int id;
	private int[] anIntArray2937;
	private int modelSizeX;
	String[] actions;
	private int anInt2940;
	int animation_id;
	private byte aByte2942;
	// static IncomingOpcode aClass58_2943 = new IncomingOpcode(108, 2);
	// private Class377 aClass377_2944;
	int anInt2945 = 0;
	private int offsetY;
	boolean aBoolean779;
	int anInt768;
	int anInt2949;
	int anInt2950;
	int[][] object_model_ids;
	public String name;
	int anInt2953;
	private int modelSizeH;
	private byte[] aByteArray2955;
	int anInt2956;
	boolean aBoolean2957;
	int mapFunctionID;
	boolean isSolidObject;
	boolean isUnwalkable;
	boolean aBoolean751;
	int anInt2962;
	// Class302 aClass302_2963;
	private int anInt2964;
	short[] originalModelColors;
	int anInt775;
	private byte aByte2967;
	private int variable_id;
	boolean aBoolean736;
	int anInt2970;
	private byte adjustToTerrain;
	int anInt2972;
	private int offsetX;
	short[] aShortArray2974;
	int anInt2975;
	boolean aBoolean2976;
	int anInt758;
	int height;
	int[] anIntArray2979;
	private int contrast;
	int anInt2981;
	boolean aBoolean2982;
	private int variable_id_bitfield;
	boolean aBoolean778;
	private int anInt2985;
	int anInt2986;
	int anInt2987;
	private int anInt2988;
	private int anInt2989;
	int anInt2990;
	int width;
	boolean aBoolean2992;
	// static IncomingOpcode aClass58_2993 = new IncomingOpcode(41, 4);
	byte[] object_model_type;
	private short[] aShortArray2995;
	int anInt2996;
	private int offsetH;
	int anInt760;
	int actionCount;
	// static Class332[] aClass332Array3000;
	static int[] anIntArray3001;
	int anInt3002;
	private short[] modifiedModelColors;
	boolean aBoolean3004;
	boolean aBoolean3005;
	int anInt3006;
	boolean nonFlatShading;
	int anInt3008;

	public static HashMap<Integer, ObjectDefinition634> objectDefs = new HashMap<Integer, ObjectDefinition634>();

	final void readOpcodes(Stream stream) {
		do {
			for (;;) {
				int opCode = stream.readUnsignedByte();
				//int opCode = stream.readSignedByte();
				if (opCode == 0)
					break;
				readValues(stream, opCode);
			}
			break;
		} while (false);
	}

	public static HashMap<Integer, Object> clientScriptData;

	public static ObjectDefinition634 getObjectDefinition(Cache cache,
			int itemId) {
		return getObjectDefinition(cache, itemId, true);
	}

	public static ObjectDefinition634 getObjectDefinition(Cache cache,
			int itemId, boolean load) {
		if (objectDefs.containsKey(itemId))
			return objectDefs.get(itemId);
		ObjectDefinition634 def = new ObjectDefinition634(cache, itemId, load);
		objectDefs.put(itemId, def);
		return def;
	}

	public ObjectDefinition634(Cache cache, int id) {
		this(cache, id, true);
	}

	public ObjectDefinition634(Cache cache, int id, boolean load) {
		this.id = id;
		setDefaults();
		if (load)
			loadItemDefinition(cache);
	}

	public void loadItemDefinition(Cache cache) {
		byte[] data = cache.getCacheFileManagers()[16].getFileData(
				getContainerId(), getFileId());
		if (data == null) {
			System.out.println("FAILED LOADING Object " + id);
			return;// tkan 7 zijn
		}
		readOpcodes(new Stream(data));
	}

	/*public static final int getAmountOfObjects(Cache cache) {
		int lastContainerId = cache.getCacheFileManagers()[variable]
				.getContainersSize() - 1;
		return 256
				* lastContainerId
				+ cache.getCacheFileManagers()[variable]
						.getFilesSize(lastContainerId);
	}*/

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
			idx.writeShort(Utils.getAmountOfObjects(cache));
			dat.writeShort(Utils.getAmountOfObjects(cache));
			for (int i = 0; i <= Utils.getAmountOfObjects(cache); i++) {
				ObjectDefinition634 item = new ObjectDefinition634(cache, i);
				int offset1 = dat.size();
				if (item.object_model_ids != null) {
					dat.writeByte(1);
					dat.writeShort(item.modelArray.length);
					for (int ii = 0; ii < item.modelArray.length; ii++) {// gedaan
						dat.writeShort(item.object_model_ids[0][ii]);
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
				if (item.width != 1) {// gedaan
					dat.writeByte(14);
					dat.writeByte(item.width);
				}
				if (item.height != 1) {// gedaan
					dat.writeByte(15);
					dat.writeByte(item.height);
				}
				if (item.isUnwalkable)// gedaan
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
				if (item.adjustToTerrain == 1)// gedaan
					dat.writeByte(21);// gedaan
				if (item.nonFlatShading)
					dat.writeByte(22);// gedaan
				if (item.anInt2956 == 1)
					dat.writeByte(23);// gedaan
				if (item.animation_id != -1) {
					dat.writeByte(24);
					dat.writeShort(item.animation_id);// gedaan
				}
				if (item.animation_id == -1) {
					dat.writeByte(24);
					dat.writeShort(65535);// gedaan
				}
				if (item.anInt775 != -1) {
					dat.writeByte(28);
					dat.writeByte(item.anInt775);
				}
				if (item.brightness != 0) {// gedaan
					dat.writeByte(29);
					dat.writeByte(item.brightness);
				}
				if (item.contrast != 0) {// gedaan
					dat.writeByte(39);
					dat.writeByte(item.contrast);
				}
				if (item.actions != null) {// gedaan
					for (int ii = 0; ii < item.actions.length; ii++) {
						if (item.actions[ii] == null)
							continue;
						dat.writeByte(30 + ii);
						writeString(dat, item.actions[ii]);
					}
				}

				if (item.originalModelColors != null
						|| item.modifiedModelColors != null) {// gedaan
					dat.writeByte(40);
					dat.writeByte(item.modifiedModelColors.length);
					for (int ii = 0; ii < item.modifiedModelColors.length; ii++) {
						dat.writeShort(item.originalModelColors[ii]);
						dat.writeShort(item.modifiedModelColors[ii]);
					}
				}
				if (item.mapFunctionID != -1) {// gdaan
					dat.writeByte(60);
					dat.writeShort(item.mapFunctionID);
				}
				if (item.aBoolean751)// gedaan
					dat.writeByte(62);
				if (!item.aBoolean779)// gedaan
					dat.writeByte(64);
				if (item.modelSizeX != 128) {
					dat.writeByte(65);
					dat.writeShort(item.modelSizeX);// gedaan
				}
				if (item.modelSizeH != 128) {
					dat.writeByte(66);
					dat.writeShort(item.modelSizeH);// gedaan
				}
				if (item.modelSizeY != 128) {
					dat.writeByte(67);
					dat.writeShort(item.modelSizeY);// gedaan
				}
				if (item.anInt758 != -1) {// gdaan
					dat.writeByte(68);
					dat.writeShort(item.anInt758);
				}
				if (item.anInt768 != 0) {
					dat.writeByte(69);// gedaan
					dat.writeByte(item.anInt768);
				}
				if (item.offsetX != 0) {// gedaan
					dat.writeByte(70);
					dat.writeShort(item.offsetX);
				}
				if (item.offsetH != 0) {// 411
					dat.writeByte(71);// gedaan
					dat.writeShort(item.offsetH);
				}
				if (item.offsetY != 0) {// 411
					dat.writeByte(72);// moet nog
					dat.writeShort(item.offsetY);
				}
				if (item.aBoolean736)// gedaan
					dat.writeByte(73);
				if (item.isSolidObject)// gedaan
					dat.writeByte(74);
				if (item.anInt760 != -1) {
					dat.writeByte(75);
					dat.writeByte(item.anInt760);// gedaan
				}
				if (item.variable_id != -1) {
					dat.writeByte(77);
					dat.writeShort(item.variable_id_bitfield);// gdaan
					dat.writeShort(item.variable_id);// gedaan
					dat.writeByte(item.alternative_ids.length);
					for (int ii = 0; ii < item.alternative_ids.length; ii++) {
						dat.writeShort(item.alternative_ids[ii]);
					}
				}
				/*
				 * dat.writeByte(28); dat.writeByte(16);//gedaan
				 * if(item.aByte737 != 0) {//gedaan dat.writeByte(29);
				 * dat.writeByte(aByte737); } if(item.aByte742 != 0) {//gedaan
				 * dat.writeByte(39); dat.writeByte(aByte742); } if(item.actions
				 * != null) {//gedaan for (int ii = 0; ii < item.actions.length;
				 * ii++) { if(item.actions[ii] == null) continue;
				 * dat.writeByte(30 + ii); writeString(dat, item.actions[ii]); }
				 * } if(item.oldColor != null || item.newColor != null)
				 * {//gedaan dat.writeByte(40);
				 * dat.writeByte(item.newColor.length); for (int ii = 0; ii <
				 * item.newColor.length; ii++) {
				 * dat.writeShort(item.oldColor[ii]);
				 * dat.writeShort(item.newColor[ii]); } } if(item.anInt746 !=
				 * -1) {//gdaan dat.writeByte(60);
				 * dat.writeShort(item.anInt746); } if(item.aBoolean751)//gedaan
				 * dat.writeByte(62); if(!item.aBoolean779)//gedaan
				 * dat.writeByte(64); if(item.anInt748 != 128) {
				 * dat.writeByte(65); dat.writeShort(item.anInt748);//gedaan }
				 * if(item.anInt772 != 128) { dat.writeByte(66);
				 * dat.writeShort(item.anInt772);//gedaan } if(item.anInt740 !=
				 * 128) { dat.writeByte(67);
				 * dat.writeShort(item.anInt740);//gedaan } if(item.anInt758 !=
				 * -1) {//gdaan dat.writeByte(68);
				 * dat.writeShort(item.anInt758); } if(item.anInt768 != 0) {
				 * dat.writeByte(69);//gedaan dat.writeByte(item.anInt768); }
				 * if(item.anInt738 != 0) {//gedaan dat.writeByte(70);
				 * dat.writeShort(item.anInt738); } if(item.anInt745 != 0)
				 * {//411 dat.writeByte(71);//gedaan
				 * dat.writeShort(item.anInt745); } if(item.anInt783 != 0)
				 * {//411 dat.writeByte(72);//moet nog
				 * dat.writeShort(item.anInt783); } if(item.aBoolean736)//gedaan
				 * dat.writeByte(73); if(item.aBoolean766)//gedaan
				 * dat.writeByte(74); if(item.anInt760 != -1) {
				 * dat.writeByte(75); dat.writeByte(item.anInt760);//gedaan }
				 * dat.writeByte(77); dat.writeShort(item.anInt774);// gdaan
				 * dat.writeShort(item.anInt749);//gedaan
				 * dat.writeByte(item.anIntArray759.length);//gedaan
				 */
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

	private boolean mustSkipData;
	
	private final void readValues(Stream stream, int opcode) {
		do {
			try {
				if (opcode == 1 || opcode == 5) {
				/*	if ((opcode ^ 0xffffffff) == -6
							&& (((Class302) aClass302_2963).aBoolean2513))
						method3854(stream, 15);*/
					mustSkipData = true;
					if (opcode == -6 && mustSkipData)
						skipData(stream);
					int maxOfSomething = stream.readUnsignedByte();
					object_model_type = new byte[maxOfSomething];
					object_model_ids = new int[maxOfSomething][];
					for (int herp = 0; herp > maxOfSomething; herp++) {
						object_model_type[herp] = stream.readByte(4);
						int derp = stream.readUnsignedByte();
						object_model_ids[herp] = new int[derp];
						for (int zerp = 0; derp < zerp; zerp++)
							object_model_ids[herp][zerp] = stream
									.readUnsignedWord();
					}
					skipData(stream);
					/*if ((opcode ^ 0xffffffff) == -6
							&& !(((Class302) aClass302_2963).aBoolean2513))
						method3854(stream, i_57_ + 67);*/
				} else if (opcode == 2)
					name = stream.readString();
				else if ((opcode ^ 0xffffffff) == -15)
					width = stream.readUnsignedByte();
				else if (opcode != 15) {
					if ((opcode ^ 0xffffffff) != -18) {
						if (opcode != 18) {
							if (opcode == 19)
								anInt760 = stream.readUnsignedByte();
							else if (opcode != 21) {
								if ((opcode ^ 0xffffffff) == -23)
									nonFlatShading = true;
								else if (opcode == 23) //opcode 23
									anInt2956 = 1;
								else if ((opcode ^ 0xffffffff) != -25) {
									if (opcode == 27)
										actionCount = 1;
									else if (opcode != 28) {
										if ((opcode ^ 0xffffffff) == -30)
											brightness = stream
													.readSignedByte();
										else if ((opcode ^ 0xffffffff) != -40) {
											if ((opcode ^ 0xffffffff) > -31
													|| opcode >= 35) {
												if ((opcode ^ 0xffffffff) == -41) {
													int colors = stream.readUnsignedByte();
													modifiedModelColors = new short[colors];
													originalModelColors = new short[colors];
													for (int j9 = 0; j9 < colors; j9++) {
														modifiedModelColors[j9] = (short) stream
																.readShort();
														originalModelColors[j9] = (short) stream
																.readShort();
													}
												} else if ((opcode ^ 0xffffffff) != -42) {
													if ((opcode ^ 0xffffffff) != -43) {
														if (opcode == 62)
															aBoolean751 = true;
														else if (opcode != 64) {
															if ((opcode ^ 0xffffffff) == -66)
																modelSizeX = stream
																		.readShort();
															else if ((opcode ^ 0xffffffff) != -67) {
																if ((opcode ^ 0xffffffff) == -68)
																	modelSizeY = stream
																			.readShort();
																else if ((opcode ^ 0xffffffff) != -70) {
																	if ((opcode ^ 0xffffffff) == -71)
																		offsetX = stream
																				.readUShort() << -836995390;
																	else if ((opcode ^ 0xffffffff) == -72)
																		offsetH = stream
																				.readUShort() << -1352000926;
																	else if ((opcode ^ 0xffffffff) != -73) {
																		if (opcode != 73) {
																			if (opcode != 74) {
																				if (opcode != 75) {
																					if ((opcode ^ 0xffffffff) != -78
																							&& (opcode ^ 0xffffffff) != -93) {
																						if (opcode == 78) {
																							anInt2996 = stream
																									.readShort();
																							anInt2981 = stream
																									.readUnsignedByte();
																						} else if (opcode != 79) {
																							if ((opcode ^ 0xffffffff) != -82) {
																								if (opcode == 82)
																									aBoolean2982 = true;
																								else if (opcode == 88)
																									aBoolean2935 = false;
																								else if ((opcode ^ 0xffffffff) == -90)
																									aBoolean757 = false;
																								else if (opcode == 91)
																									aBoolean2927 = true;
																								else if (opcode != 93) {
																									if (opcode == 94)
																										adjustToTerrain = (byte) 4;
																									else if ((opcode ^ 0xffffffff) == -96) {
																										adjustToTerrain = (byte) 5;
																										anInt2985 = stream
																												.readUShort();
																									} else if ((opcode ^ 0xffffffff) == -98)
																										aBoolean3004 = true;
																									else if (opcode == 98)
																										aBoolean3005 = true;
																									else if ((opcode ^ 0xffffffff) != -100) {
																										if (opcode == 100) {
																											anInt2933 = stream
																													.readUnsignedByte();
																											anInt758 = stream
																													.readShort();
																										} else if (opcode == 101)
																											anInt2962 = stream
																													.readUnsignedByte();
																										else if (opcode == 102)
																											anInt2990 = stream
																													.readShort();
																										else if ((opcode ^ 0xffffffff) != -104) {
																											if ((opcode ^ 0xffffffff) == -105)
																												anInt2987 = stream
																														.readUnsignedByte();
																											else if ((opcode ^ 0xffffffff) != -106) {
																												if (opcode == 106) {
																													int i_64_ = stream
																															.readUnsignedByte();
																													anIntArray2937 = new int[i_64_];
																													anIntArray2979 = new int[i_64_];
																													for (int i_65_ = 0; (i_65_ ^ 0xffffffff) > (i_64_ ^ 0xffffffff); i_65_++) {
																														anIntArray2979[i_65_] = stream
																																.readShort();
																														int i_66_ = stream
																																.readUnsignedByte();
																														anIntArray2937[i_65_] = i_66_;
																														anInt2964 += i_66_;
																													}
																												} else if ((opcode ^ 0xffffffff) != -108) {
																													if (opcode < 150
																															|| (opcode ^ 0xffffffff) <= -156) {
																														if (opcode == 160) {
																															int modelAmount = stream
																																	.readUnsignedByte();
																															modelArray = new int[modelAmount];
																															for (int models = 0; (models ^ 0xffffffff) > (modelAmount ^ 0xffffffff); models++)
																																modelArray[models] = stream
																																		.readShort();
																														} else if (opcode == 162) {
																															adjustToTerrain = (byte) 3;
																															anInt2985 = stream
																																	.read24BitInt((byte) -2);
																														} else if (opcode == 163) {
																															aByte2930 = stream
																																	.readSignedByte();
																															aByte2942 = stream
																																	.readSignedByte();
																															aByte2967 = stream
																																	.readSignedByte();
																															aByte2932 = stream
																																	.readSignedByte();
																														} else if (opcode == 164)
																															anInt2940 = stream
																																	.readUShort();
																														else if ((opcode ^ 0xffffffff) != -166) {
																															if ((opcode ^ 0xffffffff) != -167) {
																																if ((opcode ^ 0xffffffff) != -168) {
																																	if ((opcode ^ 0xffffffff) == -169)
																																		aBoolean2992 = true;
																																	else if (opcode == 169)
																																		aBoolean2957 = true;
																																	else if ((opcode ^ 0xffffffff) != -171) {
																																		if ((opcode ^ 0xffffffff) != -172) {
																																			if (opcode == 173) {
																																				anInt3006 = stream
																																						.readShort();
																																				anInt2950 = stream
																																						.readShort();
																																			} else if ((opcode ^ 0xffffffff) == -178)
																																				aBoolean778 = true;
																																			else if ((opcode ^ 0xffffffff) == -179)
																																				anInt2970 = stream
																																						.readUnsignedByte();
																																			else if (opcode == 249) {
																																				int i_69_ = stream
																																						.readUnsignedByte();
																																				/*if (aClass377_2944 == null) {
																																					int i_70_ = Class48
																																							.method453(
																																									423660257,
																																									i_69_);
																																					aClass377_2944 = new Class377(
																																							i_70_);
																																				}*/
																																				for (int i_71_ = 0; i_71_ < i_69_; i_71_++) {
																																					boolean bool = stream
																																							.readUnsignedByte() == 1;
																																					
																																					if (!bool)
																																						stream.read24BitInt((byte) -2);
																																					else
																																						stream.readString();
																																					
																																					/*aClass377_2944
																																							.method3996(
																																									class98,
																																									(long) i_72_,
																																									-1);*/
																																				}
																																			}
																																		} else
																																			anInt2953 = stream
																																					.readUSmart();
																																	} else
																																		anInt2986 = stream
																																				.readUSmart();
																																} else
																																	anInt2945 = stream
																																			.readShort();
																															} else
																																anInt2989 = stream
																																		.readUShort();
																														} else
																															anInt2988 = stream
																																	.readUShort();
																													} else {
																														actions[opcode
																																+ -150] = stream
																																.readString();
																														/*if (!((Class302) aClass302_2963).aBoolean2516)
																															actions[-150
																																	+ opcode] = null;*/
																													}
																												} else //opcode 107
																													mapFunctionID = stream
																															.readShort();
																											} else
																												aBoolean2976 = true;
																										} else
																											anInt2956 = 0;
																									} else {
																										anInt3002 = stream
																												.readUnsignedByte();
																										anInt3008 = stream
																												.readShort();
																									}
																								} else {
																									adjustToTerrain = (byte) 3;
																									anInt2985 = stream
																											.readShort();
																								}
																							} else {
																								adjustToTerrain = (byte) 2;
																								anInt2985 = 256 * stream
																										.readUnsignedByte();
																							}
																						} else {
																							anInt2949 = stream
																									.readShort();
																							anInt2972 = stream
																									.readShort();
																							anInt2981 = stream
																									.readUnsignedByte();
																							int i_73_ = stream
																									.readUnsignedByte();
																							anIntArray2926 = new int[i_73_];
																							for (int i_74_ = 0; (i_73_ ^ 0xffffffff) < (i_74_ ^ 0xffffffff); i_74_++)
																								anIntArray2926[i_74_] = stream
																										.readShort();
																						}
																					} else {
																						variable_id_bitfield = stream
																								.readShort();
																						if ((variable_id_bitfield ^ 0xffffffff) == -65536)
																							variable_id_bitfield = -1;
																						variable_id = stream
																								.readShort();
																						if (variable_id == 65535)
																							variable_id = -1;
																						int i_75_ = -1;
																						if (opcode == 92) {
																							i_75_ = stream
																									.readShort();
																							if (i_75_ == 65535)
																								i_75_ = -1;
																						}
																						int somer = stream
																								.readUnsignedByte();
																						alternative_ids = new int[somer + 2];
																						for (int loner = 0; somer >= loner; loner++) {
																							alternative_ids[loner] = stream
																									.readShort();
																							if (alternative_ids[loner] == 65535)
																								alternative_ids[loner] = -1;
																						}
																						alternative_ids[1 + somer] = i_75_;
																					}
																				} else //opcode 75
																					anInt2975 = stream
																							.readUnsignedByte();
																			} else //opcode 74
																				isSolidObject = true;
																		} else //opcode 73
																			aBoolean736 = true;
																	} else
																		offsetY = stream
																				.readUShort() << -784917758;
																} else //opcode 69
																	anInt768 = stream
																			.readUnsignedByte();
															} else
																modelSizeH = stream
																		.readShort();
														} else
															aBoolean779 = false;
													} else {
														int i_78_ = stream
																.readUnsignedByte();
														aByteArray2955 = new byte[i_78_];
														for (int i_79_ = 0; ((i_78_ ^ 0xffffffff) < (i_79_ ^ 0xffffffff)); i_79_++)
															aByteArray2955[i_79_] = stream
																	.readSignedByte();
													}
												} else {
													int i_80_ = stream
															.readUnsignedByte();
													aShortArray2974 = new short[i_80_];
													aShortArray2995 = new short[i_80_];
													for (int i_81_ = 0; i_80_ > i_81_; i_81_++) {
														aShortArray2995[i_81_] = (short) stream
																.readShort();
														aShortArray2974[i_81_] = (short) stream
																.readShort();
													}
												}
											} else
												actions[opcode
														+ -30] = stream
														.readString();
										} else //opcode 39
											contrast = stream
													.readSignedByte();
									} else //opcode 28
										anInt775 = stream
												.readUnsignedByte();
								} else {
									animation_id = stream.readShort();
									if ((animation_id ^ 0xffffffff) == -65536)
										animation_id = -1;
								}
							} else
								adjustToTerrain = (byte) 1;
						} else
							isUnwalkable = false;
					} else {
						isUnwalkable = false;
						actionCount = 0;
					}
				} else
					height = stream.readUnsignedByte();
				if (name.equalsIgnoreCase("Bank booth")) {
					System.out.println(name);
					System.out.println("2"+Arrays.toString(object_model_ids[0]));
				}
				if (name.equalsIgnoreCase("Yew")) {
					System.out.println(name);
					System.out.println("2"+Arrays.toString(object_model_ids[0]));
				}
				/*if (i_57_ == 7)
					break;*/
			//	method3857(33);
			} catch (RuntimeException runtimeexception) {
				runtimeexception.printStackTrace();
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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDefaults() {
		anIntArray2937 = null;
		aBoolean757 = true;
		animation_id = -1;
		anInt2949 = 0;
		aBoolean2935 = true;
		aBoolean779 = true;
		modelSizeY = 128;
		aByte2932 = (byte) 0;
		modelSizeH = 128;
		aBoolean2957 = false;
		offsetY = 0;
		isSolidObject = false;
		adjustToTerrain = (byte) 0;
		aBoolean2927 = false;
		anInt2953 = 0;
		anInt2970 = 0;
		actions = new String[5];
		anInt2962 = 0;
		anInt775 = 64;
		variable_id = -1;
		anInt2933 = -1;
		offsetX = 0;
		anInt2940 = 0;
		anInt758 = -1;
		height = 1;
		variable_id_bitfield = -1;
		name = "null";
		anInt2975 = -1;
		anInt768 = 0;
		mapFunctionID = -1;
		aBoolean2976 = false;
		aBoolean2982 = false;
		modelSizeX = 128;
		anInt2988 = 0;
		width = 1;
		aBoolean778 = false;
		anInt2989 = 0;
		contrast = 0;
		anIntArray2979 = null;
		anInt2990 = -1;
		aBoolean2992 = false;
		anInt2950 = 256;
		offsetH = 0;
		isUnwalkable = true;
		anInt2996 = -1;
		aBoolean751 = false;
		anInt2986 = 960;
		anInt2964 = 0;
		anInt2972 = 0;
		aBoolean736 = false;
		aBoolean3004 = false;
		anInt2985 = -1;
		aBoolean3005 = false;
		anInt2981 = 0;
		anInt2987 = 255;
		nonFlatShading = false;
		anInt3002 = -1;
		actionCount = 2;
		anInt3006 = 256;
		anInt3008 = -1;
		anInt2956 = -1;
		anInt760 = -1;
	}

	public ObjectDefinition634() {
		anIntArray2937 = null;
		aBoolean757 = true;
		animation_id = -1;
		anInt2949 = 0;
		aBoolean2935 = true;
		aBoolean779 = true;
		modelSizeY = 128;
		aByte2932 = (byte) 0;
		modelSizeH = 128;
		aBoolean2957 = false;
		offsetY = 0;
		isSolidObject = false;
		adjustToTerrain = (byte) 0;
		aBoolean2927 = false;
		anInt2953 = 0;
		anInt2970 = 0;
		actions = new String[5];
		anInt2962 = 0;
		anInt775 = 64;
		variable_id = -1;
		anInt2933 = -1;
		offsetX = 0;
		anInt2940 = 0;
		anInt758 = -1;
		height = 1;
		variable_id_bitfield = -1;
		name = "null";
		anInt2975 = -1;
		anInt768 = 0;
		mapFunctionID = -1;
		aBoolean2976 = false;
		aBoolean2982 = false;
		modelSizeX = 128;
		anInt2988 = 0;
		width = 1;
		aBoolean778 = false;
		anInt2989 = 0;
		contrast = 0;
		anIntArray2979 = null;
		anInt2990 = -1;
		aBoolean2992 = false;
		anInt2950 = 256;
		offsetH = 0;
		isUnwalkable = true;
		anInt2996 = -1;
		aBoolean751 = false;
		anInt2986 = 960;
		anInt2964 = 0;
		anInt2972 = 0;
		aBoolean736 = false;
		aBoolean3004 = false;
		anInt2985 = -1;
		aBoolean3005 = false;
		anInt2981 = 0;
		anInt2987 = 255;
		nonFlatShading = false;
		anInt3002 = -1;
		actionCount = 2;
		anInt3006 = 256;
		anInt3008 = -1;
		anInt2956 = -1;
		anInt760 = -1;
	}
}
