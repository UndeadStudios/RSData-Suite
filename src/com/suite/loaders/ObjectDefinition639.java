package com.suite.loaders;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import com.suite.cachelib.cache.Cache;

public class ObjectDefinition639 {
	
	boolean aBoolean757;
	int[] anIntArray2926;
	boolean aBoolean2927;
	public int[] alternative_ids;
	private int modelSizeY;
	private byte aByte2930;
	private int brightness = 0;
	private byte aByte2932;
	int anInt2933;
	int[] modelArray;
	boolean aBoolean2935;
	private int[] anIntArray2937;
	private int modelSizeX;
	public String[] actions;
	private int anInt2940;
	int animation_id;
	private byte aByte2942;
	//static IncomingOpcode aClass58_2943 = new IncomingOpcode(108, 2);
	//private Class377 aClass377_2944;
	int anInt2945 = 0;
	private int offsetY;
	boolean aBoolean779;
	int anInt768;
	int anInt2949;
	int anInt2950;
	int[][] object_model_ids;
	public int[] object_model_ids1d;
	public String name;
	int anInt2953;
	private int modelSizeH;
	private byte[] aByteArray2955;
	int aBoolean764;
	boolean aBoolean2957;
	int mapFunctionID;
	boolean isSolidObject;
	boolean isUnwalkable;
	boolean aBoolean751;
	int anInt2962;
	//Class302 aClass302_2963;
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
	short[] modifiedModelTextures;
	int anInt2975;
	boolean aBoolean2976;
	int somethingElse;
	int height;
	int[] anIntArray2979;
	public int contrast;
	int anInt2981;
	boolean aBoolean2982;
	private int variable_id_bitfield;
	boolean hasActions;
	private int anInt2985;
	int anInt2986;
	int anInt2987;
	private int anInt2988;
	private int anInt2989;
	int mapSceneID;
	int width;
	boolean aBoolean2992;
	//static IncomingOpcode aClass58_2993 = new IncomingOpcode(41, 4);
	public byte[] object_model_type;
	private short[] originalModelTextures;
	int anInt2996;
	private int offsetH;
	int anInt2703;
	int actionCount;
	//static Class332[] aClass332Array3000;
	static int[] anIntArray3001;
	int anInt3002;
	private short[] modifiedModelColors;
	boolean aBoolean3004;
	boolean aBoolean3005;
	int anInt3006;
	boolean nonFlatShading;
	int anInt3008;
	static int id;// id? // Ub
	public static HashMap<Integer, ObjectDefinition639> objectDefs = new HashMap<Integer, ObjectDefinition639>();

	final void readOpcodes(Stream stream) {
		do {
			for (;;) {
				int opCode = stream.readUnsignedByte((byte) 24);
				if (opCode == 0)
					break;
				readValues(opCode, stream);
			}
			break;
		} while (false);
	}

	public static HashMap<Integer, Object> clientScriptData;

	public static ObjectDefinition639 getObjectDefinition(Cache cache,
			int itemId) {
		return getObjectDefinition(cache, itemId, true);
	}

	public static ObjectDefinition639 getObjectDefinition(Cache cache,
			int itemId, boolean load) {
		if (objectDefs.containsKey(itemId))
			return objectDefs.get(itemId);
		ObjectDefinition639 def = new ObjectDefinition639(cache, itemId, load);
		objectDefs.put(itemId, def);
		return def;
	}

	public ObjectDefinition639(Cache cache, int id) {
		this(cache, id, true);
	}

	public ObjectDefinition639(Cache cache, int id, boolean load) {
		ObjectDefinition639.id = id;
		setDefaults();
		if (load)
			loadItemDefinition(cache);
	}

	public static int variable = 16;

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
				ObjectDefinition639 object = new ObjectDefinition639(cache, i);
				int offset1 = dat.size();
				if (object.object_model_ids1d != null) {
					dat.writeByte(1);
				/*	System.out.println("Object ID "+id);
					System.out.println("Model id length: "+object.object_model_ids1d.length);
					System.out.println("Model type length: "+object.object_model_type.length);*/
					dat.writeShort(object.object_model_ids1d.length);
					if (object.object_model_type != null)
					dat.writeShort(object.object_model_type.length);
					else
					dat.writeShort(0);
					for (int ii = 0; ii < object.object_model_ids1d.length; ii++) {// gedaan
						dat.writeShort(object.object_model_ids1d[ii]);
					}
					if (object.object_model_type != null) {
					for (int ii = 0; ii < object.object_model_type.length; ii++) {// gedaan
						dat.writeByte(object.object_model_type[ii]);
					}
					}
				}
				if (object.name != null) {// gedaan
					dat.writeByte(2);
					writeString(dat, object.name);
					dat.writeByte(3);
					writeString(dat, "It's a " + object.name);
				}
				if (object.object_model_ids1d != null) {// gedaan
					dat.writeByte(5);
					dat.writeByte(object.object_model_ids1d.length);
					for (int ii = 0; ii < object.object_model_ids1d.length; ii++) {
						dat.writeShort(object.object_model_ids1d[ii]);
					}
				}
				if (object.width != 1) {// gedaan
					dat.writeByte(14);
					dat.writeByte(object.width);
				}
				if (object.height != 1) {// gedaan
					dat.writeByte(15);
					dat.writeByte(object.height);
				}
				if (!object.isUnwalkable)// gedaan
					dat.writeByte(17);
				if (!object.isUnwalkable)// NOT SURE
					dat.writeByte(18);
				if (object.hasActions) {// gedaan
					dat.writeByte(19);
					dat.writeByte(1);
				}
				/* if (!item.aBoolean778) {// gedaan 
					 dat.writeByte(19);
					 dat.writeByte(2); 
				 }*/
				if (object.adjustToTerrain == 1)// gedaan
					dat.writeByte(21);// gedaan
				if (object.nonFlatShading)
					dat.writeByte(22);// gedaan
				if (object.aBoolean764 == 1)
					dat.writeByte(23);// gedaan
				if (object.animation_id == -1) {
					dat.writeByte(24);
					dat.writeShort(65535);// gedaan
				}
				if (object.animation_id != -1) {
					dat.writeByte(24);
					dat.writeShort(object.animation_id);// gedaan
				}
				if (object.anInt775 != 16) {
					dat.writeByte(28);
					dat.writeByte(object.anInt775);// gedaan
				}
				if (object.brightness != 0) {// gedaan
					dat.writeByte(29);
					dat.writeByte(object.brightness);
				}
				if (object.contrast != 0) {// gedaan
					dat.writeByte(39);
					dat.writeByte(object.contrast);
				}
				if (object.actions != null) {// gedaan
					for (int ii = 0; ii < object.actions.length; ii++) {
						if (object.actions[ii] == null)
							continue;
						dat.writeByte(30 + ii);
						writeString(dat, object.actions[ii]);
					}
				}
				if (object.originalModelColors != null
						|| object.modifiedModelColors != null) {// gedaan
					dat.writeByte(40);
					dat.writeByte(object.modifiedModelColors.length);
					for (int ii = 0; ii < object.modifiedModelColors.length; ii++) {
						dat.writeShort(object.originalModelColors[ii]);
						dat.writeShort(object.modifiedModelColors[ii]);
					}
				}
				if (object.mapFunctionID != -1) {// gedaan
					dat.writeByte(60);
					dat.writeShort(object.mapFunctionID);
				}
				if (object.aBoolean751)// gedaan
					dat.writeByte(62);
				if (!object.aBoolean779)// gedaan
					dat.writeByte(64);
				if (object.modelSizeX != 128) {
					dat.writeByte(65);
					dat.writeShort(object.modelSizeX);// gedaan
				}
				if (object.modelSizeH != 128) {
					dat.writeByte(66);
					dat.writeShort(object.modelSizeH);// gedaan
				}
				if (object.modelSizeY != 128) {
					dat.writeByte(67);
					dat.writeShort(object.modelSizeY);// gedaan
				}
				if (object.mapSceneID != -1) {// gdaan nog
					dat.writeByte(68);
					dat.writeShort(object.mapSceneID);
				}
				if (object.anInt768 != 0) {
					dat.writeByte(69);// gedaan nog
					dat.writeByte(object.anInt768);
				}
				if (object.offsetX != 0) {// gedaan
					dat.writeByte(70);
					dat.writeShort(object.offsetX);
				}
				if (object.offsetH != 0) {// 411
					dat.writeByte(71);// gedaan
					dat.writeShort(object.offsetH);
				}
				if (object.offsetY != 0) {// 411
					dat.writeByte(72);// moet nog
					dat.writeShort(object.offsetY);
				}
				if (object.aBoolean736)// gedaan
					dat.writeByte(73);
				if (object.isSolidObject)// gedaan
					dat.writeByte(74);
				if (object.anInt2703 != -1) {
					dat.writeByte(75);
					dat.writeByte(object.anInt2703);// gedaan
				}
				/*
																variable_id_bitfield = (stream
																		.readUnsignedShort(8104));
																if (variable_id_bitfield == 65535)
																	variable_id_bitfield = -1;
																variable_id = (stream
																		.readUnsignedShort(8104));
																if (variable_id == 65535)
																	variable_id = -1;
																int i = -1;
																if ((opcode ^ 0xffffffff) == -93) {
																	i = (stream
																			.readUnsignedShort(8104));
																	if (i == 65535)
																		i = -1;
																}
																int i_10_ = (stream
																		.readUnsignedByte(79));
																alternative_ids = (new int[2 + i_10_]);
																for (int i_11_ = 0; (i_10_ >= i_11_); i_11_++) {
																	alternative_ids[i_11_] = (stream
																			.readUnsignedShort(8104));
																	if ((alternative_ids[i_11_]) == 65535)
																		alternative_ids[i_11_] = -1;
																}
																alternative_ids[1 + i_10_] = i;				 
				 */
				if (object.variable_id_bitfield != -1 || object.variable_id != -1 || object.alternative_ids != null) {
				dat.writeByte(77);
				dat.writeShort(object.variable_id_bitfield);// gdaan
				dat.writeShort(object.variable_id);// gdaan
				dat.writeByte(object.alternative_ids.length);// gedaan
					for (int i3 = 0; i3 < object.alternative_ids.length; i3++) {
						if (object.alternative_ids[i3] != -1)
						dat.writeShort(object.alternative_ids[i3]);
			
					}
				}
				dat.writeByte(0);
				int offset2 = dat.size();
				int writeOffset = offset2 - offset1;
				idx.writeShort(writeOffset);
			}
			dat.close();
			idx.close();
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeString(DataOutputStream dos, String toWrite) {
		try {
			dos.write(toWrite.getBytes());
			dos.writeByte(10);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private final void readValues(int opcode, Stream stream) {
		do {
				if (opcode == 1 || opcode == 5) {
					boolean mustSkipData = true; //false 27, true 28
					if ((opcode ^ 0xffffffff) == -6 && mustSkipData)
						method3854(stream);
					
					int modelLength = stream.readUnsignedByte((byte) -117);
					object_model_type = new byte[modelLength];
					object_model_ids = new int[modelLength][];
					for (int modelIndex = 0; modelIndex < modelLength; modelIndex++) {
						object_model_type[modelIndex] = stream
								.readSignedByte((byte) -19);
						int readValue = stream.readUnsignedByte((byte) -101);
						object_model_ids[modelIndex] = new int[readValue];
						for (int valueIndex = 0; valueIndex < readValue; valueIndex++)
							object_model_ids[modelIndex][valueIndex] = stream
									.readShorter((byte) 127);
					}
					
					int amt = 0, mid = 0;
					for(int i = 0; i < object_model_ids.length; i++)
					amt += object_model_ids[i].length;
					object_model_ids1d = new int[amt];
					for(int i =0 ; i < object_model_ids.length; i++) {
					for(int j = 0; j < object_model_ids[i].length; j++) {
						object_model_ids1d[mid] = object_model_ids[i][j];
					mid++;
					}
					}
					
					if ((opcode ^ 0xffffffff) == -6
							&& !mustSkipData)
						method3854(stream);
				} else if (opcode == 2)
					name = stream.readString((byte) 84);
				else if ((opcode ^ 0xffffffff) == -15)
					width = stream.readUnsignedByte((byte) 107);
				else if (opcode != 15) {
					if ((opcode ^ 0xffffffff) != -18) {
						if (opcode != 18) {
							if (opcode == 19)
								anInt2703 = stream.readUnsignedByte((byte) 3);
							else if (opcode != 21) {
								if ((opcode ^ 0xffffffff) == -23)
									nonFlatShading = true;
								else if (opcode == 23) //opcode 23
									aBoolean764 = 1;
								else if ((opcode ^ 0xffffffff) != -25) {
									if (opcode == 27)
										actionCount = 1;
									else if (opcode != 28) {
										if ((opcode ^ 0xffffffff) == -30)
											brightness = stream
													.readSignedByte((byte) -19);
										else if ((opcode ^ 0xffffffff) != -40) {
											if ((opcode ^ 0xffffffff) > -31
													|| opcode >= 35) {
												if ((opcode ^ 0xffffffff) == -41) {
													int colors = (stream.readUnsignedByte((byte) 73));
													modifiedModelColors = new short[colors];
													originalModelColors = new short[colors];
													for (int j9 = 0; j9 < colors; j9++) {
														modifiedModelColors[j9] = (short) (stream
																.readShorter((byte) 127));
														originalModelColors[j9] = (short) (stream
																.readShorter((byte) 127));
													}
												} else if ((opcode ^ 0xffffffff) != -42) {
													if ((opcode ^ 0xffffffff) != -43) {
														if (opcode == 62)
															aBoolean751 = true;
														else if (opcode != 64) {
															if ((opcode ^ 0xffffffff) == -66)
																modelSizeX = (stream
																		.readShorter((byte) 127));
															else if ((opcode ^ 0xffffffff) != -67) {
																if ((opcode ^ 0xffffffff) == -68)
																	modelSizeY = (stream
																			.readShorter((byte) 127));
																else if ((opcode ^ 0xffffffff) != -70) {
																	if ((opcode ^ 0xffffffff) == -71)
																		offsetX = stream
																				.readUShort(false) << -836995390;
																	else if ((opcode ^ 0xffffffff) == -72)
																		offsetH = stream
																				.readUShort(false) << -1352000926;
																	else if ((opcode ^ 0xffffffff) != -73) {
																		if (opcode != 73) {
																			if (opcode != 74) {
																				if (opcode != 75) {
																					if ((opcode ^ 0xffffffff) != -78
																							&& (opcode ^ 0xffffffff) != -93) {
																						if (opcode == 78) {
																							anInt2996 = stream
																									.readShorter((byte) 127);
																							anInt2981 = stream
																									.readUnsignedByte((byte) -108);
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
																												.readUShort(false);
																									} else if ((opcode ^ 0xffffffff) == -98)
																										aBoolean3004 = true;
																									else if (opcode == 98)
																										aBoolean3005 = true;
																									else if ((opcode ^ 0xffffffff) != -100) {
																										if (opcode == 100) {
																											anInt2933 = stream
																													.readUnsignedByte((byte) -123);
																											somethingElse = stream
																													.readShorter((byte) 127);
																										} else if (opcode == 101)
																											anInt2962 = stream
																													.readUnsignedByte((byte) -105);
																										else if (opcode == 102)
																											mapSceneID = stream
																													.readShorter((byte) 127);
																										else if ((opcode ^ 0xffffffff) != -104) {
																											if ((opcode ^ 0xffffffff) == -105)
																												anInt2987 = stream
																														.readUnsignedByte((byte) -5);
																											else if ((opcode ^ 0xffffffff) != -106) {
																												if (opcode == 106) {
																													int i_64_ = stream
																															.readUnsignedByte((byte) -112);
																													anIntArray2937 = new int[i_64_];
																													anIntArray2979 = new int[i_64_];
																													for (int i_65_ = 0; (i_65_ ^ 0xffffffff) > (i_64_ ^ 0xffffffff); i_65_++) {
																														anIntArray2979[i_65_] = stream
																																.readShorter((byte) 127);
																														int i_66_ = stream
																																.readUnsignedByte((byte) -116);
																														anIntArray2937[i_65_] = i_66_;
																														anInt2964 += i_66_;
																													}
																												} else if ((opcode ^ 0xffffffff) != -108) {
																													if (opcode < 150
																															|| (opcode ^ 0xffffffff) <= -156) {
																														if (opcode == 160) {
																															int modelAmount = stream
																																	.readUnsignedByte((byte) -102);
																															modelArray = new int[modelAmount];
																															for (int models = 0; (models ^ 0xffffffff) > (modelAmount ^ 0xffffffff); models++)
																																modelArray[models] = stream
																																		.readShorter((byte) 127);
																														} else if (opcode == 162) {
																															adjustToTerrain = (byte) 3;
																															anInt2985 = stream
																																	.readInt(-2);
																														} else if (opcode == 163) {
																															aByte2930 = stream
																																	.readSignedByte((byte) -19);
																															aByte2942 = stream
																																	.readSignedByte((byte) -19);
																															aByte2967 = stream
																																	.readSignedByte((byte) -19);
																															aByte2932 = stream
																																	.readSignedByte((byte) -19);
																														} else if (opcode == 164)
																															anInt2940 = stream
																																	.readUShort(false);
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
																																						.readShorter((byte) 127);
																																				anInt2950 = stream
																																						.readShorter((byte) 127);
																																			} else if ((opcode ^ 0xffffffff) == -178)
																																				hasActions = true;
																																			else if ((opcode ^ 0xffffffff) == -179)
																																				anInt2970 = stream
																																						.readUnsignedByte((byte) -111);
																																			else if (opcode == 249) {
																																				int i_69_ = stream
																																						.readUnsignedByte((byte) 107);
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
																																							.readUnsignedByte((byte) -109) == 1;
																																					int i_72_ = stream
																																							.method1186(7
																																									+ -133);
																																					//Class98 class98;
																																					if (!bool)
																																						stream.readInt(-2);
																																					else
																																						stream.readString((byte) 84);
																																					/*aClass377_2944
																																							.method3996(
																																									class98,
																																									(long) i_72_,
																																									-1);*/
																																				}
																																			}
																																		} else
																																			anInt2953 = stream
																																					.readSmart(1689622712);
																																	} else
																																		anInt2986 = stream
																																				.readSmart(1689622712);
																																} else
																																	anInt2945 = stream
																																			.readShorter((byte) 127);
																															} else
																																anInt2989 = stream
																																		.readUShort(false);
																														} else
																															anInt2988 = stream
																																	.readUShort(false);
																													} else {
																														actions[opcode
																																+ -150] = stream
																																.readString((byte) 84);
																														if (/*
																																 * !
																																 * Class131_Sub2_Sub13
																																 * .
																																 * aBoolean5738
																																 * ||
																																 */actions[opcode
																																	+ -150]
																																	.equalsIgnoreCase("hidden"))
																															actions[-150
																																	+ opcode] = null;
																													}
																												} else //opcode 107
																													mapFunctionID = stream
																															.readShorter((byte) 127);
																											} else
																												aBoolean2976 = true;
																										} else
																											aBoolean764 = 0;
																									} else {
																										anInt3002 = stream
																												.readUnsignedByte((byte) -112);
																										anInt3008 = stream
																												.readShorter((byte) 127);
																									}
																								} else {
																									adjustToTerrain = (byte) 3;
																									anInt2985 = stream
																											.readShorter((byte) 127);
																								}
																							} else {
																								adjustToTerrain = (byte) 2;
																								anInt2985 = 256 * stream
																										.readUnsignedByte((byte) 76);
																							}
																						} else {
																							anInt2949 = stream
																									.readShorter((byte) 127);
																							anInt2972 = stream
																									.readShorter((byte) 127);
																							anInt2981 = stream
																									.readUnsignedByte((byte) -121);
																							int i_73_ = stream
																									.readUnsignedByte((byte) 121);
																							anIntArray2926 = new int[i_73_];
																							for (int i_74_ = 0; (i_73_ ^ 0xffffffff) < (i_74_ ^ 0xffffffff); i_74_++)
																								anIntArray2926[i_74_] = stream
																										.readShorter((byte) 127);
																						}
																					} else {
																						variable_id_bitfield = stream
																								.readShorter((byte) 127);
																						if ((variable_id_bitfield ^ 0xffffffff) == -65536)
																							variable_id_bitfield = -1;
																						variable_id = stream
																								.readShorter((byte) 127);
																						if (variable_id == 65535)
																							variable_id = -1;
																						int i_75_ = -1;
																						if (opcode == 92) {
																							i_75_ = stream
																									.readShorter((byte) 127);
																							if (i_75_ == 65535)
																								i_75_ = -1;
																						}
																						int somer = stream
																								.readUnsignedByte((byte) -106);
																						alternative_ids = new int[somer + 2];
																						for (int loner = 0; somer >= loner; loner++) {
																							alternative_ids[loner] = stream
																									.readShorter((byte) 127);
																							if (alternative_ids[loner] == 65535)
																								alternative_ids[loner] = -1;
																						}
																						alternative_ids[1 + somer] = i_75_;
																					}
																				} else //opcode 75
																					anInt2975 = stream
																							.readUnsignedByte((byte) 74);
																			} else //opcode 74
																				isSolidObject = true;
																		} else //opcode 73
																			aBoolean736 = true;
																	} else
																		offsetY = stream
																				.readUShort(false) << -784917758;
																} else //opcode 69
																	anInt768 = (stream
																			.readUnsignedByte((byte) 78));
															} else
																modelSizeH = (stream
																		.readShorter((byte) 127));
														} else
															aBoolean779 = false;
													} else {
														int i_78_ = (stream
																.readUnsignedByte((byte) 106));
														aByteArray2955 = new byte[i_78_];
														for (int i_79_ = 0; ((i_78_ ^ 0xffffffff) < (i_79_ ^ 0xffffffff)); i_79_++)
															aByteArray2955[i_79_] = (stream
																	.readSignedByte((byte) -19));
													}
												} else {
													int count = (stream
															.readUnsignedByte((byte) 2));
													modifiedModelTextures = new short[count];
													originalModelTextures = new short[count];
													for (int index = 0; count > index; index++) {
														originalModelTextures[index] = (short) (stream
																.readShorter((byte) 127));
														modifiedModelTextures[index] = (short) (stream
																.readShorter((byte) 127));
													}
												}
											} else
												actions[opcode
														+ -30] = (stream
														.readString((byte) 84));
										} else //opcode 39
											contrast = (stream
													.readSignedByte((byte) -19) * 5);
									} else //opcode 28
										anInt775 = (stream
												.readUnsignedByte((byte) 110) << -69774750);
								} else {
									animation_id = stream.readShorter((byte) 127);
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
					height = stream.readUnsignedByte((byte) 35);
				int i_57_ = 7;
				if (i_57_ == 7)
					break;
				//method3857(33);
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
	
	private final void method3854(Stream stream) {
			int i_16_ = stream.readUnsignedByte((byte) -113);
			//int i_17_ = -19 / ((i - -66) / 54);
			for (int i_18_ = 0; i_18_ < i_16_; i_18_++) {
				stream.pos++;
				int i_19_ = stream.readUnsignedByte((byte) 25);
				stream.pos += 2 * i_19_;
			}
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
		somethingElse = -1;
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
		hasActions = false;
		anInt2989 = 0;
		contrast = 0;
		anIntArray2979 = null;
		mapSceneID = -1;
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
		aBoolean764 = -1;
		anInt2703 = -1;
	}

	public ObjectDefinition639() {
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
		somethingElse = -1;
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
		hasActions = false;
		anInt2989 = 0;
		contrast = 0;
		anIntArray2979 = null;
		mapSceneID = -1;
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
		aBoolean764 = -1;
		anInt2703 = -1;
	}
}
