package com.suite.loaders;

import com.suite.cachelib.cache.Cache;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class VarBitDefinition {
	public static int id;
	public int baseVar;
	public int startBit;
	public int endBit;
	public static HashMap<Integer, VarBitDefinition> objectDefs = new HashMap<Integer, VarBitDefinition>();

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

	public static VarBitDefinition getVarBitDefinition(Cache cache, int itemId) {
		return getVarBitDefinition(cache, itemId, true);
	}

	public static VarBitDefinition getVarBitDefinition(Cache cache, int itemId,
                                                       boolean load) {
		if (objectDefs.containsKey(itemId))
			return objectDefs.get(itemId);
		VarBitDefinition def = new VarBitDefinition(cache, itemId, load);
		objectDefs.put(itemId, def);
		return def;
	}

	public VarBitDefinition(Cache cache, int id) {
		this(cache, id, true);
	}

	public VarBitDefinition(Cache cache, int id, boolean load) {
		VarBitDefinition.id = id;
		setDefaults();
		if (load)
			loadItemDefinition(cache);
	}

	public static int variable = 22;

	public void loadItemDefinition(Cache cache) {
		byte[] data = cache.getCacheFileManagers()[variable].getFileData(
				getContainerId(), getFileId());
		if (data == null) {
			System.out.println("FAILED LOADING Object " + id);
			//return;// tkan 7 zijn
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
		return id >>> 1416501898;
	}

	public int getFileId() {
		return  id & 0x3ff;
	}




	public static void objectPack(Cache cache) {
		try {
			DataOutputStream dat = new DataOutputStream(new FileOutputStream(
					"varbit.dat"));
			dat.writeShort(getAmountOfObjects(cache));
			for (int i = 0; i <= getAmountOfObjects(cache); i++) {
				VarBitDefinition item = new VarBitDefinition(cache, i);
				if (item.baseVar != -1 || item.startBit != -1 || item.endBit != -1) {
					dat.writeByte(1);
					dat.writeShort(item.baseVar);
					dat.writeByte(item.startBit);
					dat.writeByte(item.endBit);
				}

				dat.writeByte(0);
			}
			dat.close();
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
		if (i == 1) {
			baseVar = stream.readUnsignedShort(8104);
			startBit = stream.readUnsignedByte();
			endBit = stream.readUnsignedByte();
		}
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
	}

	public VarBitDefinition() {
	}
}
