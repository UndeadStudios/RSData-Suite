package com.suite.loaders;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import com.suite.cachelib.cache.Cache;
import com.suite.cachelib.util.Utils;

import cacheLib.io.Stream;

public class NpcDefinition {

	private static HashMap<Integer, NpcDefinition> npcDefs = new HashMap<Integer, NpcDefinition>();

	public int id;

	public int anInt833;
	public static int anInt834;
	public static int anInt835;
	public int anInt836;
	public int anInt837;
	public byte aByte838;
	public int boundDim = 1;
	public int[][] anIntArrayArray840;
	public boolean clickable;
	public int turn180AnimIndex;
	public int varBitID;
	public int[] childrenIDs;
	public int anInt846;
	public static int anInt847;
	public int anInt848;
	public boolean aBoolean849 = false;
	public int anInt850;
	public byte aByte851;
	public boolean aBoolean852;
	public int degreesToTurn;
	public byte aByte854;
	public static int anInt855;
	public boolean aBoolean856;
	public boolean aBoolean857;
	public static int anInt858;
	public short[] aShortArray859;
	public int combatLevel;
	public byte[] aByteArray861;
	public short aShort862;
	public boolean aBoolean863;
	public int scaleXZ;
	public String name;
	public short[] recolourTarget;
	public byte aByte867;
	public int[] npcModels;
	public int lightModifier;
	public int anInt870;
	public int turn90CCWAnimIndex;
	public int anInt872;
	public static int anInt873;
	public int anInt874;
	public int anInt875;
	public int walkAnim;
	public int headIcon;
	public static int anInt878;
	public int standAnim;
	public short[] recolourOriginal;
	public static int anInt881;
	public int[][] anIntArrayArray882;
	public static int anInt883;
	public int turn90CWAnimIndex;
	public int[] anIntArray885;
	public static int anInt887;
	public int sessionSettingID;
	public int anInt889;
	public static String aString890 = "white:";
	public boolean drawMinimapDot;
	public int[] aditionalModels;
	public static int anInt893;
	public short aShort894;
	public String[] actions;
	public short[] aShortArray896;
	public int shadowModifier;
	public static int anInt898;
	public int scaleY;
	public int anInt900;
	public int anInt901;
	public static int anInt902;
	public byte[] description = new String("Its a " + name).getBytes();

	private static HashMap<Integer, Object> clientScriptData;

	public static NpcDefinition getNpcDefinition(Cache cache, int itemId) {
		return getNpcDefinition(cache, itemId, true);
	}

	public static NpcDefinition getNpcDefinition(Cache cache, int itemId,
			boolean load) {
		if (npcDefs.containsKey(itemId))
			return npcDefs.get(itemId);
		NpcDefinition def = new NpcDefinition(cache, itemId, load);
		npcDefs.put(itemId, def);
		return def;
	}

	public NpcDefinition(Cache cache, int id) {
		this(cache, id, true);
	}

	public NpcDefinition(Cache cache, int id, boolean load) {
		this.id = id;
		setDefaultsVariableValules();
		setDefaultOptions();
		if (load)
			loadNpcDefinition(cache);
	}

	private void loadNpcDefinition(Cache cache) {
		try {
			byte[] data = cache.getCacheFileManagers()[18].getFileData(
					getContainerId(), getFileId());
			if (data == null) {
				System.out.println("FAILED LOADING NPC " + id);
				return;
			}
			readOpcodeValues(new Stream(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getContainerId() {
		return id >>> 8;
	}

	public int getFileId() {
		return 0xff & id;
	}

	// test :P
	public void printClientScriptData() {
		for (int key : clientScriptData.keySet()) {
			Object value = clientScriptData.get(key);
			System.out.println("KEY: " + key + ", VALUE: " + value);
		}
		/*
		 * if(requiriments == null) { System.out.println("null."); return; }
		 * System.out.println(requiriments.keySet().size()); for(int key :
		 * requiriments.keySet()) { Object value = requiriments.get(key);
		 * System.out.println("SKILL: "+key+", LEVEL: "+value); }
		 */
	}

	private void setDefaultOptions() {
		actions = new String[] { "Talk-to", null, null, null, null };
		// inventoryOptions = new String[] { null, null, null, null, "Drop" };
	}

	private void setDefaultsVariableValules() {
		turn180AnimIndex = -1;
		varBitID = -1;
		anInt837 = -1;
		anInt846 = -1;
		degreesToTurn = 32;
		combatLevel = -1;
		anInt836 = -1;
		name = "null";
		lightModifier = 0;
		aByte867 = (byte) 0;
		anInt850 = 255;
		turn90CCWAnimIndex = -1;
		aBoolean852 = true;
		aShort862 = (short) 0;
		walkAnim = -1;
		aByte851 = (byte) -96;
		anInt875 = 0;
		anInt872 = -1;
		anInt848 = -1;
		aByte838 = (byte) 7;
		aBoolean857 = true;
		anInt870 = -1;
		anInt874 = -1;
		anInt833 = -1;
		scaleXZ = 128;
		headIcon = -1;
		aBoolean856 = false;
		sessionSettingID = -1;
		aByte854 = (byte) -16;
		aBoolean863 = false;
		drawMinimapDot = true;
		anInt889 = -1;
		turn90CWAnimIndex = -1;
		clickable = true;
		standAnim = -1;
		scaleY = 128;
		aShort894 = (short) 0;
		actions = new String[5];
		shadowModifier = 0;
		anInt901 = -1;
	}

	public static void npcPack(Cache cache) {
		try {
			DataOutputStream dat = new DataOutputStream(new FileOutputStream(
					"npc.dat"));
			DataOutputStream idx = new DataOutputStream(new FileOutputStream(
					"npc.idx"));
			idx.writeShort(Utils.getAmountOfNpcs(cache));
			dat.writeShort(Utils.getAmountOfNpcs(cache));
			for (int i = 0; i <= Utils.getAmountOfNpcs(cache); i++) {
				NpcDefinition npc = new NpcDefinition(cache, i);
				int offset1 = dat.size();
				if (npc.npcModels != null) {
					dat.writeByte(1);
					dat.writeByte(npc.npcModels.length);
					for (int ii = 0; ii < npc.npcModels.length; ii++) {
						dat.writeShort(npc.npcModels[ii]);
					}
				}
				if (npc.name != "null") {
					dat.writeByte(2);
					writeString(dat, npc.name);
					dat.writeByte(3);
					writeString(dat, "It's a " + npc.name + "");
				}
				if (npc.boundDim != 1) {
					dat.writeByte(12);
					dat.writeByte(npc.boundDim);
				}
				if (npc.standAnim != -1) {
					dat.writeByte(13);
					dat.writeShort(npc.standAnim);
				}
				if (npc.walkAnim != -1) {
					dat.writeByte(17);
					dat.writeShort(npc.walkAnim);
					dat.writeShort(npc.turn180AnimIndex);
					dat.writeShort(npc.turn90CWAnimIndex);
					dat.writeShort(npc.turn90CCWAnimIndex);
				}
				if (npc.actions != null) {
					for (int ii = 0; ii < npc.actions.length; ii++) {
						if (npc.actions[ii] == null)
							continue;
						dat.writeByte(30 + ii);
						writeString(dat, npc.actions[ii]);
					}
				}
				if (npc.recolourOriginal != null || npc.recolourTarget != null) {
					dat.writeByte(40);
					dat.writeByte(npc.recolourOriginal.length);
					for (int ii = 0; ii < npc.recolourOriginal.length; ii++) {
						dat.writeShort(npc.recolourOriginal[ii]);
						dat.writeShort(npc.recolourTarget[ii]);
					}
				}
				if (npc.aditionalModels != null) {
					dat.writeByte(60);
					dat.writeByte(npc.aditionalModels.length);
					for (int ii = 0; ii < npc.aditionalModels.length; ii++) {
						dat.writeShort(npc.aditionalModels[ii]);
					}
				}
				// 90 opcodes
				if (!npc.drawMinimapDot) {
					dat.writeByte(93);
				}
				if (npc.combatLevel != -1) {
					dat.writeByte(95);
					dat.writeShort(npc.combatLevel);
				}
				if (npc.scaleXZ != 128) {
					dat.writeByte(97);
					dat.writeShort(npc.scaleXZ);
				}
				if (npc.scaleY != 128) {
					dat.writeByte(98);
					dat.writeShort(npc.scaleY);
				}
				if (npc.aBoolean863) {
					dat.writeByte(99);
				}
				if (npc.lightModifier != 0) {
					dat.writeByte(100);
					dat.writeByte(npc.lightModifier);
				}
				if (npc.shadowModifier != 0) {
					dat.writeByte(101);
					dat.writeByte(npc.shadowModifier);
				}
				if (npc.headIcon != -1) {
					dat.writeByte(102);
					dat.writeShort(npc.headIcon);
				}
				if (npc.degreesToTurn != 32) {
					dat.writeByte(103);
					dat.writeShort(npc.degreesToTurn);
				}
				if (npc.varBitID != -1 || npc.sessionSettingID != -1
						|| npc.childrenIDs != null) {
					dat.writeByte(106);
					dat.writeShort(npc.varBitID);
					dat.writeShort(npc.sessionSettingID);
					dat.writeByte(npc.childrenIDs.length);
					for (int ii = 0; ii < npc.childrenIDs.length; ii++) {
						dat.writeShort(npc.childrenIDs[ii]);
					}
				}
				if (!npc.clickable) {
					dat.writeByte(107);
				}
				/*
				 * } if(npc.modelZoom != 2000) { dat.writeByte(4);
				 * dat.writeShort(npc.modelZoom); } if(npc.modelRotation1 != 0)
				 * { dat.writeByte(5); dat.writeShort(npc.modelRotation1); }
				 * if(npc.modelRotation2 != 0) { dat.writeByte(6);
				 * dat.writeShort(npc.modelRotation2); } if(npc.modelOffset1 !=
				 * 0) { dat.writeByte(7); dat.writeShort(npc.modelOffset1); }
				 * if(npc.modelOffset2 != 0) { dat.writeByte(8);
				 * dat.writeShort(npc.modelOffset2); } if(npc.stackable == 1) {
				 * dat.writeByte(11); } if(npc.value != 1) { dat.writeByte(12);
				 * dat.writeInt(npc.value); } if(npc.membersOnly) {
				 * dat.writeByte(16); } if(npc.maleWornModelId1 != -1) {
				 * dat.writeByte(23); dat.writeShort(npc.maleWornModelId1);
				 * dat.writeByte(0); } if(npc.maleWornModelId2 != -1) {
				 * dat.writeByte(24); dat.writeShort(npc.maleWornModelId2); }
				 * if(npc.femaleWornModelId1 != -1) { dat.writeByte(25);
				 * dat.writeShort(npc.femaleWornModelId1); dat.writeByte(-11); }
				 * if(npc.femaleWornModelId2 != -1) { dat.writeByte(26);
				 * dat.writeShort(npc.femaleWornModelId2); }
				 * if(npc.groundOptions != null) { for (int ii = 0; ii <
				 * npc.groundOptions.length; ii++) { if(npc.groundOptions[ii] ==
				 * null) continue; dat.writeByte(30 + ii); writeString(dat,
				 * npc.groundOptions[ii]); } } if(npc.inventoryOptions != null)
				 * { for (int z = 0; z < npc.inventoryOptions.length; z++) {
				 * if(npc.inventoryOptions[z] == null) continue;
				 * dat.writeByte(35 + z); writeString(dat,
				 * npc.inventoryOptions[z]); } } if(npc.originalModelColors !=
				 * null || npc.modifiedModelColors != null) { dat.writeByte(40);
				 * dat.writeByte(npc.originalModelColors.length); for (int ii =
				 * 0; ii < npc.originalModelColors.length; ii++) {
				 * dat.writeShort(npc.originalModelColors[ii]);
				 * dat.writeShort(npc.modifiedModelColors[ii]); } }
				 * if(npc.colourEquip1 != -1) { dat.writeByte(78);
				 * dat.writeShort(npc.colourEquip1); } if(npc.colourEquip2 !=
				 * -1) { dat.writeByte(79); dat.writeShort(npc.colourEquip2); }
				 * if(npc.unknownInt1 != -1) { dat.writeByte(90);
				 * dat.writeShort(npc.unknownInt1); } if(npc.unknownInt2 != -1)
				 * { dat.writeByte(91); dat.writeShort(npc.unknownInt2); }
				 * if(npc.unknownInt3 != -1) { dat.writeByte(92);
				 * dat.writeShort(npc.unknownInt3); } if(npc.unknownInt4 != -1)
				 * { dat.writeByte(93); dat.writeShort(npc.unknownInt4); }
				 * if(npc.unknownInt5 != 0) { dat.writeByte(95);
				 * dat.writeShort(npc.unknownInt5); } if(npc.certID != -1) {
				 * dat.writeByte(97); dat.writeShort(npc.certID); }
				 * if(npc.certTemplateID != -1) { dat.writeByte(98);
				 * dat.writeShort(npc.certTemplateID); } if(npc.stackIDs !=
				 * null) { for (int ii = 0; ii < npc.stackIDs.length; ii++) {
				 * dat.writeByte(100 + ii); dat.writeShort(npc.stackIDs[ii]);
				 * dat.writeShort(npc.stackAmounts[ii]); } } if(npc.unknownInt7
				 * != 128) { dat.writeByte(110);
				 * dat.writeShort(npc.unknownInt7); } if(npc.unknownInt8 != 128)
				 * { dat.writeByte(111); dat.writeShort(npc.unknownInt8); }
				 * if(npc.unknownInt9 != 128) { dat.writeByte(112);
				 * dat.writeShort(npc.unknownInt9); } if(npc.unknownInt10 != 0)
				 * { dat.writeByte(113); dat.writeByte(npc.unknownInt10); }
				 * if(npc.unknownInt11 != 0) { dat.writeByte(114);
				 * dat.writeByte(npc.unknownInt11/5); } if(npc.teamId != 0) {
				 * dat.writeByte(115); dat.writeByte(npc.teamId); }
				 * if(npc.lendID != -1) { dat.writeByte(121);
				 * dat.writeShort(npc.lendID); } if(npc.lendTemplateID != -1) {
				 * dat.writeByte(122); dat.writeShort(npc.lendTemplateID); }
				 */
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

	public void readValues(Stream stream, int opcode) { // readvalues
		// try {
		// anInt847++;
		// if (arg0 >= -112)
		// anInt901 = -93;
		if (opcode != 1) {
			if (opcode == 2)
				setName(stream.getString());
			else if ((opcode ^ 0xffffffff) != -13) {
				if (opcode >= 30 && (opcode ^ 0xffffffff) > -36) {
					actions[opcode - 30] = stream.getString();
					if (actions[-30 + opcode].equalsIgnoreCase("Hidden"))
						actions[-30 + opcode] = null;
				} else if ((opcode ^ 0xffffffff) != -41) {
					if (opcode == 41) {
						int i = stream.getUByte();
						recolourOriginal = new short[i];
						recolourTarget = new short[i];
						for (int i_54_ = 0; (i_54_ ^ 0xffffffff) > (i ^ 0xffffffff); i_54_++) {
							recolourOriginal[i_54_] = (short) stream
									.getUShort();
							recolourTarget[i_54_] = (short) stream.getUShort();
						}
					} else if ((opcode ^ 0xffffffff) == -43) {
						int i = stream.getUByte();
						aByteArray861 = new byte[i];
						for (int i_55_ = 0; i > i_55_; i_55_++)
							aByteArray861[i_55_] = stream.getByte();
					} else if ((opcode ^ 0xffffffff) != -61) {
						if (opcode == 93)
							drawMinimapDot = false;
						else if ((opcode ^ 0xffffffff) == -96)
							combatLevel = stream.getUShort();
						else if (opcode != 97) {
							if ((opcode ^ 0xffffffff) == -99)
								scaleY = stream.getUShort();
							else if ((opcode ^ 0xffffffff) == -100)
								aBoolean863 = true;
							else if (opcode == 100)
								lightModifier = stream.getByte();
							else if ((opcode ^ 0xffffffff) == -102)
								shadowModifier = stream.getByte() * 5;
							else if ((opcode ^ 0xffffffff) == -103)
								headIcon = stream.getUShort();
							else if (opcode != 103) {
								if (opcode == 106 || opcode == 118) {
									varBitID = stream.getUShort();
									if (varBitID == 65535)
										varBitID = -1;
									sessionSettingID = stream.getUShort();
									if (sessionSettingID == 65535)
										sessionSettingID = -1;
									int i = -1;
									if ((i ^ 0xffffffff) == -119) {
										i = stream.getUShort();
										if ((i ^ 0xffffffff) == -65536)
											i = -1;
									}
									int childLength = stream.getUByte();
									childrenIDs = new int[2 + childLength];
									for (int shim = 0; childLength >= shim; shim++) {
										childrenIDs[shim] = stream.getUShort();
										if (childrenIDs[shim] == 65535)
											childrenIDs[shim] = -1;
									}
									childrenIDs[childLength - -1] = i;
								} else if ((opcode ^ 0xffffffff) != -108) {
									if ((opcode ^ 0xffffffff) == -110)
										aBoolean852 = false;
									else if ((opcode ^ 0xffffffff) != -112) {
										if (opcode != 113) {
											if (opcode == 114) {
												aByte851 = stream.getByte();
												aByte854 = stream.getByte();
											} else if (opcode == 115) {
												stream.getUByte();
												stream.getUByte();
											} else if ((opcode ^ 0xffffffff) != -120) {
												if (opcode != 121) {
													if ((opcode ^ 0xffffffff) != -123) {
														if (opcode == 123)
															anInt846 = stream
																	.getUShort();
														else if (opcode != 125) {
															if (opcode == 127)
																anInt848 = stream
																		.getUShort();
															else if ((opcode ^ 0xffffffff) == -129)
																stream.getUByte();
															else if (opcode != 134) {
																if (opcode == 135) {
																	anInt833 = stream
																			.getUByte();
																	anInt874 = stream
																			.getUShort();
																} else if (opcode != 136) {
																	if (opcode != 137) {
																		if (opcode != 138) {
																			if ((opcode ^ 0xffffffff) != -140) {
																				if (opcode == 140)
																					anInt850 = stream
																							.getUByte();
																				else if (opcode == 141)
																					aBoolean849 = true;
																				else if ((opcode ^ 0xffffffff) != -143) {
																					if (opcode == 143)
																						aBoolean856 = true;
																					else if ((opcode ^ 0xffffffff) <= -151
																							&& opcode < 155) {
																						actions[opcode - 150] = stream
																								.getString();
																						if (/*
																							 * !
																							 * Class35
																							 * .
																							 * aBoolean494
																							 * ||
																							 */actions[opcode - 150]
																								.equalsIgnoreCase("Hidden"))
																							actions[opcode
																									+ -150] = null;
																					} else if ((opcode ^ 0xffffffff) == -161) {
																						int i = stream
																								.getUByte();
																						anIntArray885 = new int[i];
																						for (int i_58_ = 0; i > i_58_; i_58_++)
																							anIntArray885[i_58_] = stream
																									.getUShort();
																					} else if (opcode == 249) {
																						int i = stream
																								.getUByte();
																						/*
																						 * if
																						 * (
																						 * aClass180_832
																						 * ==
																						 * null
																						 * )
																						 * {
																						 * int
																						 * i_59_
																						 * =
																						 * Class101
																						 * .
																						 * method887
																						 * (
																						 * 1388313616
																						 * ,
																						 * i
																						 * )
																						 * ;
																						 * aClass180_832
																						 * =
																						 * new
																						 * Class180
																						 * (
																						 * i_59_
																						 * )
																						 * ;
																						 * }
																						 */
																						/*
																						 * for
																						 * (
																						 * int
																						 * i_60_
																						 * =
																						 * 0
																						 * ;
																						 * i
																						 * >
																						 * i_60_
																						 * ;
																						 * i_60_
																						 * ++
																						 * )
																						 * {
																						 * boolean
																						 * bool
																						 * =
																						 * stream
																						 * .
																						 * getUByte
																						 * (
																						 * )
																						 * ==
																						 * 1
																						 * ;
																						 * int
																						 * i_61_
																						 * =
																						 * stream
																						 * .
																						 * read3Bytes
																						 * (
																						 * false
																						 * )
																						 * ;
																						 */
																						// Class131
																						// class131;
																						/*
																						 * if
																						 * (
																						 * bool
																						 * )
																						 * class131
																						 * =
																						 * new
																						 * Class131_Sub29
																						 * (
																						 * stream
																						 * .
																						 * getString
																						 * (
																						 * )
																						 * )
																						 * ;
																						 * else
																						 * class131
																						 * =
																						 * new
																						 * Class131_Sub24
																						 * (
																						 * stream
																						 * .
																						 * getUInt
																						 * (
																						 * -
																						 * 2
																						 * )
																						 * )
																						 * ;
																						 */
																						/*
																						 * Class180_832
																						 * .
																						 * method2523
																						 * (
																						 * false
																						 * ,
																						 * (
																						 * long
																						 * )
																						 * i_61_
																						 * ,
																						 * class131
																						 * )
																						 * ;
																						 * }
																						 */
																					}
																				} else
																					anInt870 = stream
																							.getUShort();
																			} else
																				standAnim = stream
																						.getUShort();
																		} else
																			anInt901 = stream
																					.getUShort();
																	} else
																		anInt872 = stream
																				.getUShort();
																} else {
																	anInt837 = stream
																			.getUByte();
																	anInt889 = stream
																			.getUShort();
																}
															} else {
																walkAnim = stream
																		.getUShort();
																if (walkAnim == 65535)
																	walkAnim = -1;
																turn180AnimIndex = stream
																		.getUShort();
																if (turn180AnimIndex == 65535)
																	turn180AnimIndex = -1;
																turn90CWAnimIndex = stream
																		.getUShort();
																if ((turn90CWAnimIndex ^ 0xffffffff) == -65536)
																	turn90CWAnimIndex = -1;
																turn90CCWAnimIndex = stream
																		.getUShort();
																if ((turn90CCWAnimIndex ^ 0xffffffff) == -65536)
																	turn90CCWAnimIndex = -1;
																anInt875 = stream
																		.getUByte();
															}
														} else
															aByte838 = stream
																	.getByte();
													} else
														anInt836 = stream
																.getUShort();
												} else {
													anIntArrayArray840 = (new int[npcModels.length][]);
													int i = stream.getUByte();
													for (int i_62_ = 0; ((i_62_ ^ 0xffffffff) > (i ^ 0xffffffff)); i_62_++) {
														int i_63_ = stream
																.getUByte();
														int[] is = (anIntArrayArray840[i_63_] = (new int[3]));
														is[0] = stream
																.getByte();
														is[1] = stream
																.getByte();
														is[2] = stream
																.getByte();
													}
												}
											} else
												aByte867 = stream.getByte();
										} else {
											aShort862 = (short) stream
													.getUShort();
											aShort894 = (short) stream
													.getUShort();
										}
									} else
										aBoolean857 = false;
								} else
									clickable = false;
							} else
								degreesToTurn = stream.getUShort();
						} else
							scaleXZ = stream.getUShort();
					} else {
						int i = stream.getUByte();
						aditionalModels = new int[i];
						for (int i_64_ = 0; (i_64_ ^ 0xffffffff) > (i ^ 0xffffffff); i_64_++)
							aditionalModels[i_64_] = stream.getUShort();
					}
				} else {
					int i = stream.getUByte();
					aShortArray859 = new short[i];
					aShortArray896 = new short[i];
					for (int i_65_ = 0; (i ^ 0xffffffff) < (i_65_ ^ 0xffffffff); i_65_++) {
						aShortArray896[i_65_] = (short) stream.getUShort();
						aShortArray859[i_65_] = (short) stream.getUShort();
					}
				}
			} else
				boundDim = stream.getUByte();
		} else {
			int i = stream.getUByte();
			npcModels = new int[i];
			for (int i_66_ = 0; i_66_ < i; i_66_++) {
				npcModels[i_66_] = stream.getUShort();
				if ((npcModels[i_66_] ^ 0xffffffff) == -65536)
					npcModels[i_66_] = -1;
			}
		}
		/*
		 * } catch (RuntimeException runtimeexception) { throw
		 * Class131_Sub2_Sub6.method1495(runtimeexception, ("gj.K(" + arg0 + ','
		 * + (stream != null ? "{...}" : "null") + ',' + opcode + ')')); }
		 */
	}

	/*
	 * private final void readValuesOld(Stream stream, int i) { if (i == 1)
	 * modelID = stream.getUShort(); else if (i != 2) { if (i == 4) modelZoom =
	 * stream.getUShort(); else if (i == 5) modelRotation1 = stream.getUShort();
	 * else if (i == 6) modelRotation2 = stream.getUShort(); else if (i != 7) {
	 * if (i == 8) { modelOffset2 = stream.getUShort(); if (modelOffset2 >
	 * 32767) modelOffset2 -= 65536; } else if (i == 11) stackable = 1; else if
	 * (i != 12) { if (i == 16) membersOnly = true; else if (i != 18) { if (i ==
	 * 23) setMaleWornModelId1(stream.getUShort()); else if (i != 24) { if (i !=
	 * 25) { if (i != 26) { if (i < 30 || i >= 35) { if (i >= 35 && i < 40)
	 * inventoryOptions[i - 35] = stream.getString(); else if (i != 40) { if (i
	 * == 41) { int i_23_ = (stream.getUByte()); textureColour1 = new
	 * short[i_23_]; textureColour2 = new short[i_23_]; for (int i_24_ = 0;
	 * i_24_ < i_23_; i_24_++) { textureColour2[i_24_] = (short)
	 * (stream.getUShort()); textureColour1[i_24_] = (short)
	 * (stream.getUShort()); } } else if (i != 42) { if (i == 65) unnoted =
	 * true; else if (i != 78) { if (i == 79) colourEquip2 =
	 * (stream.getUShort()); else if (i != 90) { if (i == 91) unknownInt2 =
	 * (stream.getUShort()); else if (i != 92) { if (i == 93) unknownInt4 =
	 * (stream.getUShort()); else if (i == 95) unknownInt5 =
	 * (stream.getUShort()); else if (i == 96) unknownInt6 =
	 * (stream.getUByte()); else if (i != 97) { if (i == 98) certTemplateID =
	 * stream.getUShort(); else if (i < 100 || i >= 110) { if (i != 110) { if (i
	 * != 111) { if (i == 112) unknownInt9 = stream.getUShort(); else if (i !=
	 * 113) { if (i != 114) { if (i == 115) teamId = stream.getUByte(); else if
	 * (i != 121) { if (i != 122) { if (i != 125) { if (i != 126) { if (i ==
	 * 127) { unknownInt18 = stream.getUByte(); unknownInt19 =
	 * stream.getUShort(); } else if (i == 128) { unknownInt20 =
	 * stream.getUByte(); unknownInt21 = stream.getUShort(); } else if (i ==
	 * 129) { unknownInt20 = stream.getUByte(); unknownInt21 =
	 * stream.getUShort(); } else if (i == 130) { unknownInt22 =
	 * stream.getUByte(); unknownInt23 = stream.getUShort(); } else if (i !=
	 * 132) { if (i == 134) anInt1902 = stream.getUByte(); else if (i != 139) {
	 * if (i == 140) anInt1885 = stream.getUShort(); else if (i == 249) { int
	 * length = stream.getUByte(); if(clientScriptData == null) clientScriptData
	 * = new HashMap<Integer, Object>(length); for (int index = 0; index <
	 * length; index++) { boolean stringInstance = stream.getUByte() == 1; int
	 * key = stream.getMediumInt(); Object value = stringInstance ?
	 * stream.getString() : stream.getInt(); clientScriptData.put(key, value); }
	 * } } else anInt1875 = stream.getUShort(); } else { int i_29_ =
	 * stream.getUByte(); unknownArray2 = new int[i_29_]; for (int i_30_ = 0;
	 * i_30_ < i_29_; i_30_++) unknownArray2[i_30_] = stream.getUShort(); } }
	 * else { unknownInt15 = stream.getByte() << 2; unknownInt16 =
	 * stream.getByte() << 2; unknownInt17 = stream.getByte() << 2; } } else {
	 * unknownInt12 = stream.getByte() << 2; unknownInt13 = stream.getByte() <<
	 * 2; unknownInt14 = stream.getByte() << 2; } } else lendTemplateID =
	 * stream.getUShort(); } else lendID = stream.getUShort(); } else
	 * unknownInt11 = stream.getByte() * 5; } else unknownInt10 =
	 * stream.getByte(); } else unknownInt8 = stream.getUShort(); } else
	 * unknownInt7 = stream.getUShort(); } else { if (stackIDs == null) {
	 * stackIDs = new int[10]; stackAmounts = new int[10]; } stackIDs[i - 100] =
	 * stream.getUShort(); stackAmounts[i - 100] = stream.getUShort(); } } else
	 * certID = (stream.getUShort()); } else unknownInt3 = (stream.getUShort());
	 * } else unknownInt1 = (stream.getUShort()); } else colourEquip1 =
	 * (stream.getUShort()); } else { int i_31_ = (stream.getUByte());
	 * unknownArray1 = new byte[i_31_]; for (int i_32_ = 0; i_32_ < i_31_;
	 * i_32_++) unknownArray1[i_32_] = (stream.getByte()); } } else { int i_33_
	 * = stream.getUByte(); originalModelColors = new short[i_33_];
	 * modifiedModelColors = new short[i_33_]; for (int i_34_ = 0; i_33_ >
	 * i_34_; i_34_++) { originalModelColors[i_34_] = (short)
	 * (stream.getUShort()); modifiedModelColors[i_34_] = (short)
	 * (stream.getUShort()); } } } else groundOptions[i - 30] =
	 * stream.getString(); } else femaleWornModelId2 = stream.getUShort(); }
	 * else setMaleWornModelId2(stream.getUShort()); } else femaleWornModelId1 =
	 * stream.getUShort(); } else stream.getUShort(); } else value =
	 * stream.getInt(); } else { modelOffset1 = stream.getUShort(); if
	 * (modelOffset1 > 32767) modelOffset1 -= 65536; } } else
	 * setName(stream.getString()); }
	 */
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

	/*
	 * public void setMaleWornModelId1(int maleWornModelId1) {
	 * this.maleWornModelId1 = maleWornModelId1; }
	 * 
	 * public int getMaleWornModelId1() { return maleWornModelId1; }
	 * 
	 * public void setMaleWornModelId2(int maleWornModelId2) {
	 * this.maleWornModelId2 = maleWornModelId2; }
	 * 
	 * public int getMaleWornModelId2() { return maleWornModelId2; }
	 */
}
