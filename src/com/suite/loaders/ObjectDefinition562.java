package com.suite.loaders;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

import com.suite.cachelib.cache.Cache;
import com.suite.openrs.util.ArrayUtils;

public class ObjectDefinition562 {

	//@OriginalMember(owner = "client!wf", name = "o", descriptor = "[Lclient!gb;")

	//@OriginalMember(owner = "client!pb", name = "a", descriptor = "[S")
	private static short[] retex_s;

	//@OriginalMember(owner = "client!pb", name = "b", descriptor = "[S")
	private short[] recol_s;

	//@OriginalMember(owner = "client!pb", name = "n", descriptor = "[I")
	public int[] shapes;

	//@OriginalMember(owner = "client!pb", name = "v", descriptor = "[S")
	private static short[] retex_d;

	//@OriginalMember(owner = "client!pb", name = "B", descriptor = "Lclient!sc;")
	private HashMap params;

	//@OriginalMember(owner = "client!pb", name = "H", descriptor = "[S")
	private short[] recol_d;

	//@OriginalMember(owner = "client!pb", name = "P", descriptor = "[B")
	public byte[] recol_p;

	//@OriginalMember(owner = "client!pb", name = "X", descriptor = "[I")
	public int[] models;

	//@OriginalMember(owner = "client!pb", name = "db", descriptor = "[I")
	public int[] multiLocs;

	//@OriginalMember(owner = "client!pb", name = "hb", descriptor = "I")
	public static int id;

	//@OriginalMember(owner = "client!pb", name = "wb", descriptor = "[I")
	public int[] bgsounds;

	//@OriginalMember(owner = "client!pb", name = "e", descriptor = "I")
	public int width = 1;

	//@OriginalMember(owner = "client!pb", name = "i", descriptor = "Z")
	public boolean forcedecor = false;

	//@OriginalMember(owner = "client!pb", name = "l", descriptor = "I")
	public int length = 1;

	//@OriginalMember(owner = "client!pb", name = "C", descriptor = "Z")
	public boolean aBoolean211 = false;

	//@OriginalMember(owner = "client!pb", name = "u", descriptor = "I")
	private int ambient = 0;

	//@OriginalMember(owner = "client!gg", name = "W", descriptor = "Lclient!na;")
	public static final String aClass100_475 = null;
	//@OriginalMember(owner = "client!pb", name = "E", descriptor = "Lclient!na;")
	public String name = aClass100_475;

	//@OriginalMember(owner = "client!pb", name = "D", descriptor = "Z")
	public boolean castshadow = true;

	//@OriginalMember(owner = "client!pb", name = "t", descriptor = "I")
	public int cursor1Op = -1;

	//@OriginalMember(owner = "client!pb", name = "R", descriptor = "I")
	public int bgsoundmax = 0;

	//@OriginalMember(owner = "client!pb", name = "S", descriptor = "I")
	public int mapscene = -1;

	//@OriginalMember(owner = "client!pb", name = "G", descriptor = "B")
	private byte hillskewType = 0;

	//@OriginalMember(owner = "client!pb", name = "r", descriptor = "Z")
	public boolean members = false;

	//@OriginalMember(owner = "client!pb", name = "T", descriptor = "I")
	public int cursor1 = -1;

	//@OriginalMember(owner = "client!pb", name = "w", descriptor = "I")
	private int xoff = 0;

	//@OriginalMember(owner = "client!pb", name = "W", descriptor = "I")
	public int bgsoundmin = 0;

	//@OriginalMember(owner = "client!pb", name = "h", descriptor = "I")
	public int mapfunction = -1;

	//@OriginalMember(owner = "client!pb", name = "L", descriptor = "Z")
	public boolean aBoolean214 = false;

	//@OriginalMember(owner = "client!pb", name = "Y", descriptor = "I")
	public int cursor2Op = -1;

	//@OriginalMember(owner = "client!pb", name = "A", descriptor = "S")
	public short hillskewAmount = -1;

	//@OriginalMember(owner = "client!pb", name = "g", descriptor = "I")
	private int resizez = 128;

	//@OriginalMember(owner = "client!pb", name = "z", descriptor = "[Lclient!na;")
	public String[] ops = new String[5];

	//@OriginalMember(owner = "client!pb", name = "d", descriptor = "I")
	private int resizex = 128;

	//@OriginalMember(owner = "client!pb", name = "s", descriptor = "Z")
	public boolean allowrandomizedanimation = true;

	//@OriginalMember(owner = "client!pb", name = "o", descriptor = "I")
	private int resizey = 128;

	//@OriginalMember(owner = "client!pb", name = "y", descriptor = "Z")
	public boolean breakroutefinding = false;

	//@OriginalMember(owner = "client!pb", name = "kb", descriptor = "I")
	public int interactable = -1;

	//@OriginalMember(owner = "client!pb", name = "lb", descriptor = "Z")
	public boolean render = false;

	//@OriginalMember(owner = "client!pb", name = "fb", descriptor = "Z")
	public boolean active = true;

	//@OriginalMember(owner = "client!pb", name = "nb", descriptor = "I")
	private int multiLocVarp = -1;

	//@OriginalMember(owner = "client!pb", name = "bb", descriptor = "I")
	public int cursor2 = -1;

	//@OriginalMember(owner = "client!pb", name = "pb", descriptor = "I")
	public int blocksides = 0;

	//@OriginalMember(owner = "client!pb", name = "m", descriptor = "Z")
	public boolean blockrange = true;

	//@OriginalMember(owner = "client!pb", name = "qb", descriptor = "I")
	private int zoff = 0;

	//@OriginalMember(owner = "client!pb", name = "c", descriptor = "I")
	public int mapSceneAngleOffset = 0;

	//@OriginalMember(owner = "client!pb", name = "jb", descriptor = "I")
	public int walloff = 16;

	//@OriginalMember(owner = "client!pb", name = "tb", descriptor = "Z")
	public boolean mapSceneRotated = false;

	//@OriginalMember(owner = "client!pb", name = "N", descriptor = "I")
	private int yoff = 0;

	//@OriginalMember(owner = "client!pb", name = "k", descriptor = "I")
	public int bgsoundrange = 0;

	//@OriginalMember(owner = "client!pb", name = "p", descriptor = "I")
	private int contrast = 0;

	//@OriginalMember(owner = "client!pb", name = "mb", descriptor = "I")
	public int anim = -1;

	//@OriginalMember(owner = "client!pb", name = "I", descriptor = "Z")
	public boolean hasanimation = false;

	//@OriginalMember(owner = "client!pb", name = "O", descriptor = "I")
	public int bgsound = -1;

	//@OriginalMember(owner = "client!pb", name = "ub", descriptor = "I")
	public int blockwalk = 2;


	private boolean mirror = false;

	//@OriginalMember(owner = "client!pb", name = "gb", descriptor = "I")
	private int multiLocVarbit = -1;

	//@OriginalMember(owner = "client!pb", name = "yb", descriptor = "I")
	public int supportitems = -1;

	//@OriginalMember(owner = "client!pb", name = "zb", descriptor = "Z")
	private boolean computeVertexColors = false;

	//@OriginalMember(owner = "client!pb", name = "Ab", descriptor = "Z")
	public boolean occlude = false;
	public static HashMap<Integer, ObjectDefinition562> objectDefs = new HashMap<Integer, ObjectDefinition562>();

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

	public static ObjectDefinition562 getObjectDefinition(Cache cache,
			int itemId) {
		return getObjectDefinition(cache, itemId, true);
	}

	public static ObjectDefinition562 getObjectDefinition(Cache cache,
			int itemId, boolean load) {
		if (objectDefs.containsKey(itemId))
			return objectDefs.get(itemId);
		ObjectDefinition562 def = new ObjectDefinition562(cache, itemId, load);
		objectDefs.put(itemId, def);
		return def;
	}

	public ObjectDefinition562(Cache cache, int id) {
		this(cache, id, true);
	}

	public ObjectDefinition562(Cache cache, int id, boolean load) {
		ObjectDefinition562.id = id;
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
		return 255 & id;
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
				ObjectDefinition562 object = new ObjectDefinition562(cache, i);
				int offset1 = dat.size();
				if (object.models != null) {
					if (object.shapes == null) {// gedaan
						dat.writeByte(5);
						dat.writeByte(object.models.length);
						if(object.models.length > 0) {
							for (int ii = 0; ii < object.models.length; ii++) {
								dat.writeShort(object.models[ii]);
							}
						}
					} else {
						dat.writeByte(1);
						dat.writeByte(object.models.length);

						if (object.models.length > 0) {
							for (int ii = 0; ii < object.models.length; ii++) {// gedaan
								dat.writeShort(object.models[ii]);
								dat.writeByte(object.shapes[ii]);
							}
						}
					}
				}
				if (object.name != null) {// gedaan
					dat.writeByte(2);
					dat.write(ArrayUtils.toByteArray(object.name));
					dat.writeByte(10);
				}
				if (object.width != 1) {// gedaan
					dat.writeByte(14);
					dat.writeByte(object.width);
				}
				if (object.length != 1) {// gedaan
					dat.writeByte(15);
					dat.writeByte(object.length);
				}
				if (!object.blockrange) {
					dat.writeByte(17);
				}
				if (!object.blockrange) {
					dat.writeByte(18);
				}
				if (object.interactable != -1) {// gedaan
					dat.writeByte(19);
					dat.writeByte(object.interactable);
				}
				if (object.hillskewType == 1)// gedaan
					dat.writeByte(21);// gedaan
				if (object.computeVertexColors)
					dat.writeByte(22);// gedaan
				if (object.occlude) {
					dat.writeByte(23);// gedaan
				}
				if (object.anim != -1) {
					dat.writeByte(24);
					dat.writeShort(object.anim);// gedaan
				}
				if(object.blockwalk == 1){
					dat.writeByte(27);
				}
				if (object.walloff != 16) {
					dat.writeByte(28);
					dat.writeByte(object.walloff);// gedaan
				}
				if (object.ambient != 0) {// gedaan
					dat.writeByte(29);
					dat.writeByte(object.ambient);
				}
				if (object.contrast != 0) {// gedaan
					dat.writeByte(39);
					dat.writeByte(object.contrast / 25);
				}
				if (object.ops != null && !ArrayUtils.isEmpty(object.ops)) {// gedaan
					for (int ii = 0; ii < object.ops.length; ii++) {
						if (object.ops[ii] == null) {
							continue;
						}
						dat.write(30 + ii);
						dat.write(ArrayUtils.toByteArray(object.ops[ii]));
						dat.writeByte(10);
					}
				}
				if (object.recol_s != null && object.recol_d  != null) {// gedaan
					dat.writeByte(40);
					dat.writeByte(object.recol_s.length);

					for (int ii = 0; ii < object.recol_s.length; ii++) {
						dat.writeShort(object.recol_s[ii]);
						dat.writeShort(object.recol_d [ii]);
					}
				}
				if (retex_s != null && retex_d != null) { // good
					dat.writeByte(41);
					dat.writeByte(retex_s.length);

					for (int ii = 0; i < retex_s.length; i++) {
						dat.writeShort(retex_s[ii]);
						dat.writeShort(retex_d[ii]);
					}
				}
				if (object.mapfunction != -1) {
					dat.writeByte(60);
					dat.writeShort(object.mapfunction);
				}
				if (object.mirror) {
					dat.writeByte(62);
					}
				if (!object.active) {
					dat.writeByte(64);
				}
				if (object.resizex != 128) {
					dat.writeByte(65);
					dat.writeShort(object.resizex);// gedaan
				}
				if (object.resizey != 128) {
					dat.writeByte(66);
					dat.writeShort(object.resizey);// gedaan
				}
				if (object.resizez  != 128) {
					dat.writeByte(67);
					dat.writeShort(object.resizez );// gedaan
				}
				if (object.blocksides  != 0) {
					dat.writeByte(69);// gedaan
					dat.writeByte(object.blocksides );
				}
				if (object.xoff  != 0) {// gedaan
					dat.writeByte(70);
					dat.writeShort(object.xoff );
				}
				if (object.yoff != 0) {// 411
					dat.writeByte(71);// gedaan
					dat.writeShort(object.yoff);
				}
				if (object.zoff != 0) {// 411
					dat.writeByte(72);// moet nog
					dat.writeShort(object.zoff);
				}
				if (object.forcedecor) {
					dat.writeByte(73);
				}
				if (object.breakroutefinding) {
					dat.writeByte(74);
				}
				if (object.supportitems != -1) {
					dat.writeByte(75);
					dat.writeByte(object.supportitems);// gedaan
				}
				if (object.multiLocVarbit != -1 || object.multiLocVarp != -1 || object.multiLocs != null && object.multiLocs.length > 0) {
					int value = object.multiLocs [object.multiLocs .length - 1];

					dat.writeByte(value != -1 ? 92 : 77);
					dat.writeShort(object.multiLocVarbit);// gdaan
					dat.writeShort(object.multiLocVarp);// gdaan

					if (value != -1) {
						dat.writeShort(object.multiLocs [object.multiLocs .length - 1]);
					}

					dat.writeByte(object.multiLocs.length - 2);// gedaan
					for (int i3 = 0; i3 < object.multiLocs.length - 2; i3++) {
						dat.writeShort(object.multiLocs [i3]);

					}
				}
				if (object.bgsound != -1 || object.bgsoundrange != 0 /*|| field2130 != 0*/) { // good
					dat.writeByte(78);
					dat.writeShort(object.bgsound);
					dat.writeByte(object.bgsoundrange);
				}

				if ((object.bgsoundmin != 0 || object.bgsoundmax != 0 || object.bgsoundrange != 0) && object.bgsounds != null) { // good
					dat.writeByte(79);
					dat.writeShort(object.bgsoundmin);
					dat.writeShort(object.bgsoundmax);
					dat.writeByte(object.bgsoundrange);

					dat.writeByte(object.bgsounds.length);
					for (int i3 = 0; i3 < object.bgsounds.length; i3++) {
						dat.writeShort(object.bgsounds[i3]);
					}
				}
				if(object.hillskewType == 2 || object.hillskewAmount != -1){
					dat.writeByte(81);
					dat.writeByte(object.hillskewAmount);
				}
				if(object.render){
					dat.writeByte(82);
				}
				if(!object.castshadow){
					dat.writeByte(88);
				}
				if(!object.allowrandomizedanimation){
					dat.writeByte(89);
				}
				if(object.aBoolean211){
					dat.writeByte(90);
				}
				if(object.members){
					dat.writeByte(91);
				}
				if(object.hillskewType == 3 || object.hillskewAmount != -1){
					dat.writeByte(93);
					dat.writeByte(object.hillskewAmount);
				}
				if(object.hillskewType == 4){
					dat.writeByte(94);
				}
				if(object.hillskewType == 5){
					dat.writeByte(95);
				}
				if(object.hasanimation){
					dat.writeByte(96);
				}
				if(object.mapSceneRotated){
					dat.writeByte(97);
				}
				if(object.aBoolean214){
					dat.writeByte(98);
				}
				if(object.cursor1Op != -1 || object.cursor1 != -1){
					dat.writeByte(99);
					dat.writeByte(object.cursor1Op);
					dat.writeShort(object.cursor1);
				}
				if(object.cursor2Op != -1 || object.cursor2 != -1){
					dat.writeByte(100);
					dat.writeByte(object.cursor2Op);
					dat.writeShort(object.cursor2);
				}
				if(object.mapSceneAngleOffset != 0){
					dat.writeByte(101);
					dat.writeByte(object.mapSceneAngleOffset);
				}
				if(object.mapscene != 0){
					dat.writeByte(102);
					dat.writeShort(object.mapscene);
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

	public void readValues(int opcode, Stream buffer) { // readValues
		int count;
		int len;
		if (opcode == 1) {
			count = buffer.readUnsignedByte();
			if (count > 0) {
				if (this.models == null) {
					this.shapes = new int[count];
					this.models = new int[count];
					for (len = 0; len < count; len++) {
						this.models[len] = buffer.readUnsignedWord();
						this.shapes[len] = buffer.readUnsignedByte();
					}
				} else {
					buffer.pos += count * 3;
				}
			}
		} else if (opcode == 2) {
			this.name = getString(buffer);
		} else if (opcode == 5) {
			count = buffer.readUnsignedByte();
			if (count > 0) {
				if (this.models == null) {
					this.models = new int[count];
					this.shapes = null;
					for (len = 0; len < count; len++) {
						this.models[len] = buffer.readUnsignedWord();
					}
				} else {
					buffer.pos += count * 2;
				}
			}
		} else if (opcode == 14) {
			this.width = buffer.readUnsignedByte();
		} else if (opcode == 15) {
			this.length = buffer.readUnsignedByte();
		} else if (opcode == 17) {
			this.blockwalk = 0;
			this.blockrange = false;
		} else if (opcode == 18) {
			this.blockrange = false;
		} else if (opcode == 19) {
			this.interactable = buffer.readUnsignedByte();
		} else if (opcode == 21) {
			this.hillskewType = 1;
		} else if (opcode == 22) { // sharelight
			this.computeVertexColors = true;
		} else if (opcode == 23) {
			this.occlude = true;
		} else if (opcode == 24) {
			this.anim = buffer.readUnsignedWord();
			if (this.anim == 65535) {
				this.anim = -1;
			}
		} else if (opcode == 27) {
			this.blockwalk = 1;
		} else if (opcode == 28) {
			this.walloff = buffer.readUnsignedByte();
		} else if (opcode == 29) {
			this.ambient = buffer.readSignedByte();
		} else if (opcode == 39) {
			this.contrast = buffer.readSignedByte() * 25;
		} else if (opcode >= 30 && opcode < 35) {
			this.ops[opcode - 30] = getString(buffer);
			if (this.ops[opcode - 30].equalsIgnoreCase("Hidden")) {
				this.ops[opcode - 30] = null;
			}
		} else if (opcode == 40) {
			count = buffer.readUnsignedByte();
			this.recol_s = new short[count];
			this.recol_d = new short[count];
			for (len = 0; len < count; len++) {
				this.recol_s[len] = (short) buffer.readUnsignedWord();
				this.recol_d[len] = (short) buffer.readUnsignedWord();
			}
		} else if (opcode == 41) {
			count = buffer.readUnsignedByte();
			this.retex_d = new short[count];
			this.retex_s = new short[count];
			for (len = 0; len < count; len++) {
				this.retex_s[len] = (short) buffer.readUnsignedWord();
				this.retex_d[len] = (short) buffer.readUnsignedWord();
			}
		} else if (opcode == 42) {
			count = buffer.readUnsignedByte();
			this.recol_p = new byte[count];
			for (len = 0; len < count; len++) {
				this.recol_p[len] = buffer.readSignedByte();
			}
		} else if (opcode == 60) {
			this.mapfunction = buffer.readUnsignedWord();
		} else if (opcode == 62) {
			this.mirror = true;
		} else if (opcode == 64) {
			this.active = false;
		} else if (opcode == 65) {
			this.resizex = buffer.readUnsignedWord();
		} else if (opcode == 66) {
			this.resizey = buffer.readUnsignedWord();
		} else if (opcode == 67) {
			this.resizez = buffer.readUnsignedWord();
		} else if (opcode == 69) {
			this.blocksides = buffer.readUnsignedByte();
		} else if (opcode == 70) {
			this.xoff = buffer.g2b();
		} else if (opcode == 71) {
			this.yoff = buffer.g2b();
		} else if (opcode == 72) {
			this.zoff = buffer.g2b();
		} else if (opcode == 73) {
			this.forcedecor = true;
		} else if (opcode == 74) {
			this.breakroutefinding = true;
		} else if (opcode == 75) {
			this.supportitems = buffer.readUnsignedByte();
		} else if (opcode == 77 || opcode == 92) {
			count = -1;
			this.multiLocVarbit = buffer.readUnsignedWord();
			if (this.multiLocVarbit == 65535) {
				this.multiLocVarbit = -1;
			}
			this.multiLocVarp = buffer.readUnsignedWord();
			if (this.multiLocVarp == 65535) {
				this.multiLocVarp = -1;
			}
			if (opcode == 92) {
				count = buffer.readUnsignedWord();
				if (count == 65535) {
					count = -1;
				}
			}
			len = buffer.readUnsignedByte();
			this.multiLocs = new int[len + 2];
			for ( int i = 0; i <= len; i++) {
				this.multiLocs[i] = buffer.readUnsignedWord();
				if (this.multiLocs[i] == 65535) {
					this.multiLocs[i] = -1;
				}
			}
			this.multiLocs[len + 1] = count;
		} else if (opcode == 78) {
			this.bgsound = buffer.readUnsignedWord();
			this.bgsoundrange = buffer.readUnsignedByte();
		} else if (opcode == 79) {
			this.bgsoundmin = buffer.readUnsignedWord(); // interval
			this.bgsoundmax = buffer.readUnsignedWord(); // interval
			this.bgsoundrange = buffer.readUnsignedByte();

			count = buffer.readUnsignedByte();
			this.bgsounds = new int[count];
			for (len = 0; len < count; len++) {
				this.bgsounds[len] = buffer.readUnsignedWord();
			}
		} else if (opcode == 81) { // sethillskew
			this.hillskewType = 2;
			this.hillskewAmount = (short) (buffer.readUnsignedByte() * 256);
		} else if (opcode == 82) {
			this.render = true;
		} else if (opcode == 88) {
			this.castshadow = false;
		} else if (opcode == 89) {
			this.allowrandomizedanimation = false;
		} else if (opcode == 90) {
			this.aBoolean211 = true;
		} else if (opcode == 91) {
			this.members = true;
		} else if (opcode == 93) {
			this.hillskewType = 3;
			this.hillskewAmount = (short) buffer.readUnsignedWord();
		} else if (opcode == 94) {
			this.hillskewType = 4;
		} else if (opcode == 95) {
			this.hillskewType = 5;
		} else if (opcode == 96) {
			this.hasanimation = true;
		} else if (opcode == 97) {
			this.mapSceneRotated = true;
		} else if (opcode == 98) {
			this.aBoolean214 = true;
		} else if (opcode == 99) {
			this.cursor1Op = buffer.readUnsignedByte();
			this.cursor1 = buffer.readUnsignedWord();
		} else if (opcode == 100) {
			this.cursor2Op = buffer.readUnsignedByte();
			this.cursor2 = buffer.readUnsignedWord();
		} else if (opcode == 101) {
			this.mapSceneAngleOffset = buffer.readUnsignedByte();
		} else if (opcode == 102) {
			this.mapscene = buffer.readUnsignedWord();
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
	private static char CHARACTERS[] = { '\u20AC', '\0', '\u201A', '\u0192', '\u201E', '\u2026', '\u2020', '\u2021',
			'\u02C6', '\u2030', '\u0160', '\u2039', '\u0152', '\0', '\u017D', '\0', '\0', '\u2018', '\u2019', '\u201C',
			'\u201D', '\u2022', '\u2013', '\u2014', '\u02DC', '\u2122', '\u0161', '\u203A', '\u0153', '\0', '\u017E',
			'\u0178' };

	public static String getString(Stream buf) {
		StringBuilder bldr = new StringBuilder();
		int b;
		while ((b = buf.readSignedByte()) != 0) {
			if (b >= 127 && b < 160) {
				char curChar = CHARACTERS[b - 128];
				if (curChar == 0) {
					curChar = 63;
				}

				bldr.append(curChar);
			} else {
				bldr.append((char) b);
			}
		}
		return bldr.toString();
	}
	public void setDefaults() {
	}

	public ObjectDefinition562() {
	}
}
