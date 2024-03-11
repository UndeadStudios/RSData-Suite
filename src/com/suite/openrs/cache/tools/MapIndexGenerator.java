package com.suite.openrs.cache.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.util.HashMap;
import java.util.Map;

import com.suite.Constants;
import com.suite.openrs.cache.Cache;
import com.suite.openrs.cache.Container;
import com.suite.openrs.cache.FileStore;
import com.suite.openrs.cache.ReferenceTable;
import com.suite.openrs.cache.tools.XTeaChecker.Square;
import com.suite.openrs.util.FileOperations;
import com.suite.openrs.util.crypto.Xtea;


/**
 * Just a quick class I wrote up to generate map_index.dat and the
 * corresponding files.
 * @author `Discardedx2
 */
public class MapIndexGenerator {
	
	/**
	 * Represents the loaded squares.
	 */
	private static Map<Integer, Square> squares = new HashMap<Integer, Square>();
	
	/**
	 * The main method.
	 * @param args the arguments of this program.
	 */
	public static void dumpMapIndex() {
		try {
			if (!new File(Constants.xteaDir+"/decoded/").exists())
				new File(Constants.xteaDir+"/decoded/").mkdir();
		//	loadCompressed();
			loadUncompressed();
			System.out.println("Loaded "+squares.size()+" XTEA files.");
			Cache cache = new Cache(FileStore.open(Constants.cacheDir));
			ReferenceTable table = ReferenceTable.decode(Container.decode(cache.getStore().read(255, 5)).getData());
			RandomAccessFile raf = new RandomAccessFile(Constants.xteaDir+"/decoded/map_index.dat", "rw");
			
			System.out.print("Generating map_index.dat...\t");
			int total = 0;
			raf.seek(2);
			for (int squareX = 0; squareX < 256; squareX++) {// Could do it all in one loop but i want to keep it seperate
				for (int squareY = 0; squareY < 256; squareY++) {//just to keep it neat.
					int squareId = squareX << 8 | squareY;
					int mapFile = cache.findName(table, "m"+squareX+"_"+squareY);
					int landscapeFile = cache.findName(table, "l"+squareX+"_"+squareY);		
					if (mapFile == -1 || landscapeFile == -1) {
						continue;
					}
					raf.writeShort(squareId);
					raf.writeShort(mapFile);
					raf.writeShort(landscapeFile);
					total++;
				}
			}
			int end = (int) raf.getFilePointer();
			raf.seek(0);
			raf.writeShort(total);
			raf.seek(end);
			raf.close();
			
			System.out.print("Done\nDecoding and writing index 5 containers...\t");
			StringBuilder sb = new StringBuilder("map_index.dat ->\n");
			int file = 0;
			for (int squareX = 0; squareX < 256; squareX++) {
				for (int squareY = 0; squareY < 256; squareY++) {
					int mapFile = cache.findName(table, "m"+squareX+"_"+squareY);
					int landscapeFile = cache.findName(table, "l"+squareX+"_"+squareY);
					if (mapFile == -1 || landscapeFile == -1) {
						continue;
					}
					ByteBuffer map = decodeMap(cache, mapFile); 
					ByteBuffer landscape = decodeLandscape(cache, landscapeFile, (squareX << 8 | squareY)); 
					if (map == null) {
						continue;
					}
					cache.save3File(mapFile, map.array());
					sb.append("map "+file);
					if (landscape == null) {
						sb.append(", none\n");
						continue;
					}
					cache.save3File(landscapeFile, landscape.array());
					sb.append(", landscape "+file+"\n");
				}
			}
			System.out.print("Done\nGenerating log and exiting...");
			BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.xteaDir+"/log.txt"));
			bw.write(sb.toString());
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to load compressed xtea files into memory.
	 */
	public static void loadCompressed() {
		try {
			RandomAccessFile raf = new RandomAccessFile(Constants.xteaDir+"/compressed.dat", "rw");
			final ByteBuffer in = raf.getChannel().map(MapMode.READ_ONLY, 0, raf.getChannel().size());
			while(in.remaining() > 0) {
				int square = in.getShort() & 0xFFFF;
				int[] keys = new int[4];
				for (int key = 0; key < 4; key++) {
					keys[key]= in.getInt();
				}
				squares.put(square, new Square(square, keys));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to load uncompressed xtea files into memory.
	 */
	public static void loadUncompressed() throws NumberFormatException, IOException {
		String[] files = new File(Constants.xteaDir).list();
		for (String s : files) {
			if (s.equalsIgnoreCase("decoded"))
				return;
			s = s.replace(".txt", "");
			String file = Constants.xteaDir+"/"+s;
			if (s.contains("(") || s.contains(")")) {
				System.out.println("breaked: "+  s);
				continue;
			}
			System.out.println(s);
			int square = Integer.parseInt(s);
			String line;
			int[] keys = new int[4];
			BufferedReader reader = new BufferedReader(new FileReader(file+".txt"));
			try {
				int key = 0;
				while ((line = reader.readLine()) != null) {
					if (!line.equals("")) {
						keys[key++] = Integer.parseInt(line);
					}
				}
				squares.put(square, new Square(square, keys));
			} finally {
				reader.close();
			}
		}
	}

	/**
	 * Decodes the specified landscape file (if possible).
	 * @param x The region x of the landscape file name.
	 * @param y The region y of the landscape file name.
	 * @return The decoded (?) landscape file.
	 * @throws Exception
	 */
	private static ByteBuffer decodeLandscape(Cache cache, int file, int squareId) throws Exception {
		ByteBuffer landData = cache.getStore().read(5, file);
		if (landData == null) {
			return null;
		}
		Square def = squares.get(squareId);
		int[] keys = def != null ? def.getKeys() : new int[4];
		byte[] array = landData.array();
		ByteBuffer data = ByteBuffer.wrap(isKeysEncrypted(keys) ? Xtea.decipher(keys, array, 5, array.length) : array);
		try {
			return Container.decode(data).getData();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Checks if the set xtea keys are encrypted or not.
	 * @param keys The keys to check.
	 * @return {@code true} if the keys are encrypted.
	 */
	private static boolean isKeysEncrypted(int[] keys) {
		for (int key : keys) {
			if (key == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Decodes the specified map file.
	 * @param x The region x of the map file name.
	 * @param y The region y of the map file name.
	 * @return The decoded (?) landscape file.
	 * @throws Exception
	 */
	private static ByteBuffer decodeMap(Cache cache, int file) throws Exception {
		return Container.decode(cache.getStore().read(5, file)).getData();
	}

}