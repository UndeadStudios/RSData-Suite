package com.suite.loaders;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import com.suite.cachelib.cache.Cache;
import com.suite.cachelib.util.Utils;

import cacheLib.io.Stream;

public class ItemDefinition {

	private static HashMap<Integer, ItemDefinition> itemsDefs = new HashMap<Integer, ItemDefinition>();

	public int id;

	private int modelID;
	public String name;

	// model size information
	private int modelZoom;
	private int modelRotation1;
	private int modelRotation2;
	private int modelOffset1;
	private int modelOffset2;

	// extra information
	private int stackable;
	private int value;
	private boolean membersOnly;

	// wearing model information
	private int maleWornModelId1;
	private int femaleWornModelId1;
	private int maleWornModelId2;
	private int femaleWornModelId2;

	// options
	private String[] groundOptions;
	public String[] inventoryOptions;

	// model information
	private short[] originalModelColors;
	private short[] modifiedModelColors;
	private short[] textureColour1;
	private short[] textureColour2;
	private byte[] unknownArray1;
	private int[] unknownArray2;
	// extra information, not used for newer items
	private boolean unnoted;

	private int colourEquip1;
	private int colourEquip2;
	private int unknownInt1;
	private int unknownInt2;
	private int unknownInt3;
	private int unknownInt4;
	private int unknownInt5;
	private int unknownInt6;
	private int certID;
	private int certTemplateID;
	private int[] stackIDs;
	private int[] stackAmounts;
	private int unknownInt7;
	private int unknownInt8;
	private int unknownInt9;
	private int unknownInt10;
	private int unknownInt11;
	private int teamId;
	private int lendID;
	private int lendTemplateID;
	private int unknownInt12;
	private int unknownInt13;
	private int unknownInt14;
	private int unknownInt15;
	private int unknownInt16;
	private int unknownInt17;
	private int unknownInt18;
	private int unknownInt19;
	private int unknownInt20;
	private int unknownInt21;
	private int unknownInt22;
	private int unknownInt23;
	private int anInt1902;
	private int anInt1875;
	private int anInt1885;
	private static HashMap<Integer, Object> clientScriptData;

	public static ItemDefinition getItemDefinition(Cache cache, int itemId) {
		return getItemDefinition(cache, itemId, true);
	}

	public static ItemDefinition getItemDefinition(Cache cache, int itemId,
			boolean load) {
		if (itemsDefs.containsKey(itemId))
			return itemsDefs.get(itemId);
		ItemDefinition def = new ItemDefinition(cache, itemId, load);
		itemsDefs.put(itemId, def);
		return def;
	}

	public ItemDefinition(Cache cache, int id) {
		this(cache, id, true);
	}

	public ItemDefinition(Cache cache, int id, boolean load) {
		this.id = id;
		setDefaultsVariableValules();
		setDefaultOptions();
		if (load)
			loadItemDefinition(cache);
	}

	private void loadItemDefinition(Cache cache) {
		byte[] data = cache.getCacheFileManagers()[19].getFileData(
				getContainerId(), getFileId());
		if (data == null) {
			System.out.println("FAILED LOADING ITEM " + id);
			return;
		}
		readOpcodeValues(new Stream(data));
	}

	public int getContainerId() {
		return id >>> 8;
	}

	public int getFileId() {
		return 0xff & id;
	}

	public boolean hasSpecialBar() {
		if (clientScriptData == null)
			return false;
		Object specialBar = clientScriptData.get(686);
		if (specialBar != null && specialBar instanceof Integer)
			return (Integer) specialBar == 1;
		return false;
	}

	public int getRenderAnimId() {
		if (clientScriptData == null)
			return 1426;
		Object animId = clientScriptData.get(644);
		if (animId != null && animId instanceof Integer)
			return (Integer) animId;
		return 1426;
	}

	public int getQuestId() {
		if (clientScriptData == null)
			return -1;
		Object questId = clientScriptData.get(861);
		if (questId != null && questId instanceof Integer)
			return (Integer) questId;
		return -1;
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

	// test :P
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

	private void setDefaultOptions() {
		groundOptions = new String[] { null, null, "Take", null, null };
		inventoryOptions = new String[] { null, null, null, null, "Drop" };
	}

	private void setDefaultsVariableValules() {
		modelID = 0;
		name = null;
		modelZoom = 2000;
		modelRotation1 = 0;
		modelRotation2 = 0;
		modelOffset1 = 0;
		modelOffset2 = 0;
		stackable = 0;
		value = 1;
		membersOnly = false;
		maleWornModelId1 = -1;
		maleWornModelId2 = -1;
		femaleWornModelId1 = -1;
		femaleWornModelId2 = -1;
		originalModelColors = null;
		modifiedModelColors = null;
		colourEquip1 = -1;
		colourEquip2 = -1;
		unknownInt1 = -1;
		unknownInt2 = -1;
		unknownInt3 = -1;
		unknownInt4 = -1;
		unknownInt5 = 0;
		certID = -1;
		certTemplateID = -1;
		stackIDs = null;
		unknownInt7 = 128;
		unknownInt8 = 128;
		unknownInt9 = 128;
		unknownInt10 = 0;
		unknownInt11 = 0;
		teamId = 0;
		lendID = -1;
		lendTemplateID = -1;
	}

	public static void itemPack(Cache cache) {
		try {
			DataOutputStream dat = new DataOutputStream(new FileOutputStream(
					"obj.dat"));
			DataOutputStream idx = new DataOutputStream(new FileOutputStream(
					"obj.idx"));
			idx.writeShort(Utils.getAmountOfItems(cache));
			dat.writeShort(Utils.getAmountOfItems(cache));
			for (int i = 0; i <= Utils.getAmountOfItems(cache); i++) {
				ItemDefinition item = new ItemDefinition(cache, i);
				int offset1 = dat.size();
				if (item.modelID != 0) {
					dat.writeByte(1);
					dat.writeShort(item.modelID);
				}
				if (item.name != null) {
					dat.writeByte(2);
					writeString(dat, item.name);
					dat.writeByte(3);
					writeString(dat, "It's a " + item.name + "");
				}
				if (item.modelZoom != 2000) {
					dat.writeByte(4);
					dat.writeShort(item.modelZoom);
				}
				if (item.modelRotation1 != 0) {
					dat.writeByte(5);
					dat.writeShort(item.modelRotation1);
				}
				if (item.modelRotation2 != 0) {
					dat.writeByte(6);
					dat.writeShort(item.modelRotation2);
				}
				if (item.modelOffset1 != 0) {
					dat.writeByte(7);
					dat.writeShort(item.modelOffset1);
				}
				if (item.modelOffset2 != 0) {
					dat.writeByte(8);
					dat.writeShort(item.modelOffset2);
				}
				if (item.stackable == 1) {
					dat.writeByte(11);
				}
				if (item.value != 1) {
					dat.writeByte(12);
					dat.writeInt(item.value);
				}
				if (item.membersOnly) {
					dat.writeByte(16);
				}
				if (item.maleWornModelId1 != -1) {
					dat.writeByte(23);
					dat.writeShort(item.maleWornModelId1);
					dat.writeByte(0);
				}
				if (item.maleWornModelId2 != -1) {
					dat.writeByte(24);
					dat.writeShort(item.maleWornModelId2);
				}
				if (item.femaleWornModelId1 != -1) {
					dat.writeByte(25);
					dat.writeShort(item.femaleWornModelId1);
					dat.writeByte(-11);
				}
				if (item.femaleWornModelId2 != -1) {
					dat.writeByte(26);
					dat.writeShort(item.femaleWornModelId2);
				}
				if (item.groundOptions != null) {
					for (int ii = 0; ii < item.groundOptions.length; ii++) {
						if (item.groundOptions[ii] == null)
							continue;
						dat.writeByte(30 + ii);
						writeString(dat, item.groundOptions[ii]);
					}
				}
				if (item.inventoryOptions != null) {
					for (int z = 0; z < item.inventoryOptions.length; z++) {
						if (item.inventoryOptions[z] == null)
							continue;
						dat.writeByte(35 + z);
						writeString(dat, item.inventoryOptions[z]);
					}
				}
				if (item.originalModelColors != null
						|| item.modifiedModelColors != null) {
					dat.writeByte(40);
					dat.writeByte(item.originalModelColors.length);
					for (int ii = 0; ii < item.originalModelColors.length; ii++) {
						dat.writeShort(item.originalModelColors[ii]);
						dat.writeShort(item.modifiedModelColors[ii]);
					}
				}
				if (item.colourEquip1 != -1) {
					dat.writeByte(78);
					dat.writeShort(item.colourEquip1);
				}
				if (item.colourEquip2 != -1) {
					dat.writeByte(79);
					dat.writeShort(item.colourEquip2);
				}
				if (item.unknownInt1 != -1) {
					dat.writeByte(90);
					dat.writeShort(item.unknownInt1);
				}
				if (item.unknownInt2 != -1) {
					dat.writeByte(91);
					dat.writeShort(item.unknownInt2);
				}
				if (item.unknownInt3 != -1) {
					dat.writeByte(92);
					dat.writeShort(item.unknownInt3);
				}
				if (item.unknownInt4 != -1) {
					dat.writeByte(93);
					dat.writeShort(item.unknownInt4);
				}
				if (item.unknownInt5 != 0) {
					dat.writeByte(95);
					dat.writeShort(item.unknownInt5);
				}
				if (item.certID != -1) {
					dat.writeByte(97);
					dat.writeShort(item.certID);
				}
				if (item.certTemplateID != -1) {
					dat.writeByte(98);
					dat.writeShort(item.certTemplateID);
				}
				if (item.stackIDs != null) {
					for (int ii = 0; ii < item.stackIDs.length; ii++) {
						dat.writeByte(100 + ii);
						dat.writeShort(item.stackIDs[ii]);
						dat.writeShort(item.stackAmounts[ii]);
					}
				}
				if (item.unknownInt7 != 128) {
					dat.writeByte(110);
					dat.writeShort(item.unknownInt7);
				}
				if (item.unknownInt8 != 128) {
					dat.writeByte(111);
					dat.writeShort(item.unknownInt8);
				}
				if (item.unknownInt9 != 128) {
					dat.writeByte(112);
					dat.writeShort(item.unknownInt9);
				}
				if (item.unknownInt10 != 0) {
					dat.writeByte(113);
					dat.writeByte(item.unknownInt10);
				}
				if (item.unknownInt11 != 0) {
					dat.writeByte(114);
					dat.writeByte(item.unknownInt11 / 5);
				}
				if (item.teamId != 0) {
					dat.writeByte(115);
					dat.writeByte(item.teamId);
				}
				if (item.lendID != -1) {
					dat.writeByte(121);
					dat.writeShort(item.lendID);
				}
				if (item.lendTemplateID != -1) {
					dat.writeByte(122);
					dat.writeShort(item.lendTemplateID);
				}
				dat.writeByte(0);
				int offset2 = dat.size();
				int writeOffset = offset2 - offset1;
				idx.writeShort(writeOffset);
			}
			dat.close();
			idx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeString(DataOutputStream dos, String toWrite) {
		try {
			dos.write(toWrite.getBytes());
			dos.writeByte(10);
		} catch (IOException ioe) {
		}

	}

	/*
	 * public byte[] encodeItemDefinition() { Stream stream = new Stream(10000);
	 * 
	 * if(modelID != 0) { stream.putByte(1); stream.putShort(modelID); }
	 * 
	 * if(!name.equals("null")) { stream.putByte(2); stream.putString(name);
	 * stream.putByte(3); stream.putString("It's a "+name+""); }
	 * 
	 * if(modelZoom != 2000) { stream.putByte(4); stream.putShort(modelZoom); }
	 * 
	 * if(modelRotation1 != 0) { stream.putByte(5);
	 * stream.putShort(modelRotation1); }
	 * 
	 * if(modelRotation2 != 0) { stream.putByte(6);
	 * stream.putShort(modelRotation2); }
	 * 
	 * if(modelOffset1 != 0) { stream.putByte(7); int value = modelOffset1 >>=
	 * 0; if (value < 0) value += 65536; stream.putShort(value); }
	 * 
	 * if(modelOffset2 != 0) { stream.putByte(8); int value = modelOffset2 >>=
	 * 0; if (value < 0) value += 65536; stream.putShort(value); }
	 * 
	 * if(stackable >= 1) { stream.putByte(11); }
	 * 
	 * if(value != 1) { stream.putByte(12); stream.putInt(value); }
	 * 
	 * if(membersOnly) { stream.putByte(16); }
	 * 
	 * if(maleWornModelId1 != -1) { stream.putByte(23);
	 * stream.putShort(maleWornModelId1); stream.putByte(0); }
	 * 
	 * if(maleWornModelId2 != -1) { stream.putByte(24);
	 * stream.putShort(maleWornModelId2); }
	 * 
	 * if(femaleWornModelId1 != -1) { stream.putByte(25);
	 * stream.putShort(femaleWornModelId1); stream.putByte(0); }
	 * 
	 * if(femaleWornModelId2 != -1) { stream.putByte(26);
	 * stream.putShort(femaleWornModelId2); }
	 * 
	 * for(int index = 0; index < groundOptions.length; index++) {
	 * if(groundOptions[index] == null || (index == 2 &&
	 * groundOptions[index].equals("Take"))) continue; stream.putByte(30+index);
	 * stream.putString(groundOptions[index]); }
	 * 
	 * for(int index = 0; index < inventoryOptions.length; index++) {
	 * if(inventoryOptions[index] == null || (index == 4 &&
	 * inventoryOptions[index].equals("Drop"))) continue;
	 * stream.putByte(35+index); stream.putString(inventoryOptions[index]); }
	 * 
	 * if(originalModelColors != null && modifiedModelColors != null) {
	 * stream.putByte(40); stream.putByte(originalModelColors.length); for(int
	 * index = 0; index < originalModelColors.length; index++) {
	 * stream.putShort(originalModelColors[index]);
	 * stream.putShort(modifiedModelColors[index]); } }
	 * 
	 * if(colourEquip1 != -1) { stream.putByte(78);
	 * stream.putShort(colourEquip1); }
	 * 
	 * if(colourEquip2 != -1) { stream.putByte(79);
	 * stream.putShort(colourEquip2); } if(unknownInt1 != -1) {
	 * stream.putByte(90); stream.putShort(unknownInt1); } if(unknownInt2 != -1)
	 * { stream.putByte(91); stream.putShort(unknownInt2); } if(unknownInt3 !=
	 * -1) { stream.putByte(92); stream.putShort(unknownInt3); } if(unknownInt4
	 * != -1) { stream.putByte(93); stream.putShort(unknownInt4); }
	 * if(unknownInt5 != 0) { stream.putByte(95); stream.putShort(unknownInt5);
	 * }
	 * 
	 * if(certID != -1) { stream.putByte(97); stream.putShort(certID); }
	 * 
	 * if(certTemplateID != -1) { stream.putByte(98);
	 * stream.putShort(certTemplateID); }
	 * 
	 * if(stackIDs != null && stackAmounts != null) { for(int index = 0; index <
	 * stackIDs.length; index++) { if(stackIDs[index] == 0 &&
	 * stackAmounts[index] == 0) continue; stream.putByte(100+index);
	 * stream.putShort(stackIDs[index]); stream.putShort(stackAmounts[index]); }
	 * } if(unknownInt7 != 128) { stream.putByte(110);
	 * stream.putShort(unknownInt7); } if(unknownInt8 != 128) {
	 * stream.putByte(111); stream.putShort(unknownInt8); } if(unknownInt9 !=
	 * 128) { stream.putByte(112); stream.putShort(unknownInt9); }
	 * if(unknownInt10 != 0) { stream.putByte(113);
	 * stream.putByte(unknownInt10); } if(unknownInt11 != 0) {
	 * stream.putByte(114); stream.putByte(unknownInt11/5); }
	 * 
	 * if(teamId != 0) { stream.putByte(115); stream.putByte(teamId); }
	 * 
	 * if(lendID != -1) { stream.putByte(121); stream.putShort(lendID); }
	 * 
	 * if(lendTemplateID != -1) { stream.putByte(122);
	 * stream.putShort(lendTemplateID); } //end stream.putByte(0);
	 * 
	 * byte[] data = new byte[stream.offset]; stream.offset = 0;
	 * stream.getBytes(data, 0, data.length); return data; }
	 */

	private final void readValues(Stream stream, int i) {
		if (i == 1)
			modelID = stream.getUShort();
		else if (i != 2) {
			if (i == 4)
				modelZoom = stream.getUShort();
			else if (i == 5)
				modelRotation1 = stream.getUShort();
			else if (i == 6)
				modelRotation2 = stream.getUShort();
			else if (i != 7) {
				if (i == 8) {
					modelOffset2 = stream.getUShort();
					if (modelOffset2 > 32767)
						modelOffset2 -= 65536;
				} else if (i == 11)
					stackable = 1;
				else if (i != 12) {
					if (i == 16)
						membersOnly = true;
					else if (i != 18) {
						if (i == 23)
							setMaleWornModelId1(stream.getUShort());
						else if (i != 24) {
							if (i != 25) {
								if (i != 26) {
									if (i < 30 || i >= 35) {
										if (i >= 35 && i < 40)
											inventoryOptions[i - 35] = stream
													.getString();
										else if (i != 40) {
											if (i == 41) {
												int i_23_ = (stream.getUByte());
												textureColour1 = new short[i_23_];
												textureColour2 = new short[i_23_];
												for (int i_24_ = 0; i_24_ < i_23_; i_24_++) {
													textureColour2[i_24_] = (short) (stream
															.getUShort());
													textureColour1[i_24_] = (short) (stream
															.getUShort());
												}
											} else if (i != 42) {
												if (i == 65)
													unnoted = true;
												else if (i != 78) {
													if (i == 79)
														colourEquip2 = (stream
																.getUShort());
													else if (i != 90) {
														if (i == 91)
															unknownInt2 = (stream
																	.getUShort());
														else if (i != 92) {
															if (i == 93)
																unknownInt4 = (stream
																		.getUShort());
															else if (i == 95)
																unknownInt5 = (stream
																		.getUShort());
															else if (i == 96)
																unknownInt6 = (stream
																		.getUByte());
															else if (i != 97) {
																if (i == 98)
																	certTemplateID = stream
																			.getUShort();
																else if (i < 100
																		|| i >= 110) {
																	if (i != 110) {
																		if (i != 111) {
																			if (i == 112)
																				unknownInt9 = stream
																						.getUShort();
																			else if (i != 113) {
																				if (i != 114) {
																					if (i == 115)
																						teamId = stream
																								.getUByte();
																					else if (i != 121) {
																						if (i != 122) {
																							if (i != 125) {
																								if (i != 126) {
																									if (i == 127) {
																										unknownInt18 = stream
																												.getUByte();
																										unknownInt19 = stream
																												.getUShort();
																									} else if (i == 128) {
																										unknownInt20 = stream
																												.getUByte();
																										unknownInt21 = stream
																												.getUShort();
																									} else if (i == 129) {
																										unknownInt20 = stream
																												.getUByte();
																										unknownInt21 = stream
																												.getUShort();
																									} else if (i == 130) {
																										unknownInt22 = stream
																												.getUByte();
																										unknownInt23 = stream
																												.getUShort();
																									} else if (i != 132) {
																										if (i == 134)
																											anInt1902 = stream
																													.getUByte();
																										else if (i != 139) {
																											if (i == 140)
																												anInt1885 = stream
																														.getUShort();
																											else if (i == 249) {
																												int length = stream
																														.getUByte();
																												if (clientScriptData == null)
																													clientScriptData = new HashMap<Integer, Object>(
																															length);
																												for (int index = 0; index < length; index++) {
																													boolean stringInstance = stream
																															.getUByte() == 1;
																													int key = stream
																															.getMediumInt();
																													Object value = stringInstance ? stream
																															.getString()
																															: stream.getInt();
																													clientScriptData
																															.put(key,
																																	value);
																												}
																											}
																										} else
																											anInt1875 = stream
																													.getUShort();
																									} else {
																										int i_29_ = stream
																												.getUByte();
																										unknownArray2 = new int[i_29_];
																										for (int i_30_ = 0; i_30_ < i_29_; i_30_++)
																											unknownArray2[i_30_] = stream
																													.getUShort();
																									}
																								} else {
																									unknownInt15 = stream
																											.getByte() << 2;
																									unknownInt16 = stream
																											.getByte() << 2;
																									unknownInt17 = stream
																											.getByte() << 2;
																								}
																							} else {
																								unknownInt12 = stream
																										.getByte() << 2;
																								unknownInt13 = stream
																										.getByte() << 2;
																								unknownInt14 = stream
																										.getByte() << 2;
																							}
																						} else
																							lendTemplateID = stream
																									.getUShort();
																					} else
																						lendID = stream
																								.getUShort();
																				} else
																					unknownInt11 = stream
																							.getByte() * 5;
																			} else
																				unknownInt10 = stream
																						.getByte();
																		} else
																			unknownInt8 = stream
																					.getUShort();
																	} else
																		unknownInt7 = stream
																				.getUShort();
																} else {
																	if (stackIDs == null) {
																		stackIDs = new int[10];
																		stackAmounts = new int[10];
																	}
																	stackIDs[i - 100] = stream
																			.getUShort();
																	stackAmounts[i - 100] = stream
																			.getUShort();
																}
															} else
																certID = (stream
																		.getUShort());
														} else
															unknownInt3 = (stream
																	.getUShort());
													} else
														unknownInt1 = (stream
																.getUShort());
												} else
													colourEquip1 = (stream
															.getUShort());
											} else {
												int i_31_ = (stream.getUByte());
												unknownArray1 = new byte[i_31_];
												for (int i_32_ = 0; i_32_ < i_31_; i_32_++)
													unknownArray1[i_32_] = (stream
															.getByte());
											}
										} else {
											int i_33_ = stream.getUByte();
											originalModelColors = new short[i_33_];
											modifiedModelColors = new short[i_33_];
											for (int i_34_ = 0; i_33_ > i_34_; i_34_++) {
												originalModelColors[i_34_] = (short) (stream
														.getUShort());
												modifiedModelColors[i_34_] = (short) (stream
														.getUShort());
											}
										}
									} else
										groundOptions[i - 30] = stream
												.getString();
								} else
									femaleWornModelId2 = stream.getUShort();
							} else
								setMaleWornModelId2(stream.getUShort());
						} else
							femaleWornModelId1 = stream.getUShort();
					} else
						stream.getUShort();
				} else
					value = stream.getInt();
			} else {
				modelOffset1 = stream.getUShort();
				if (modelOffset1 > 32767)
					modelOffset1 -= 65536;
			}
		} else
			setName(stream.getString());
	}

	private void readOpcodeValues(Stream stream) {
		while (true) {
			int opcode = stream.getUByte();
			if (opcode == 0)
				break;
			readValues(stream, opcode);
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setMaleWornModelId1(int maleWornModelId1) {
		this.maleWornModelId1 = maleWornModelId1;
	}

	public int getMaleWornModelId1() {
		return maleWornModelId1;
	}

	public void setMaleWornModelId2(int maleWornModelId2) {
		this.maleWornModelId2 = maleWornModelId2;
	}

	public int getMaleWornModelId2() {
		return maleWornModelId2;
	}
}
