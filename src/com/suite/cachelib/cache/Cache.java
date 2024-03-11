// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 24-7-2011 18:16:21
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Cache.java

package com.suite.cachelib.cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;

import cacheLib.cache.CacheFile;
import cacheLib.io.Stream;
import cacheLib.util.UpdateServer;

// Referenced classes of package cacheLib.cache:
//            CacheFile, CacheFileManager, ContainersInformation, CacheContainer

public class Cache {

	public CacheFileManager[] getCacheFileManagers() {
		return cacheFileManagers;
	}

	public Cache(String path) {
		HashMap indexesFiles = new HashMap();
		RandomAccessFile containersInformFile = null;
		RandomAccessFile dataFile = null;
		File files[] = (new File(path)).listFiles();
		int lastIndexFileId = -1;
		File afile[];
		int j = (afile = files).length;
		for (int i = 0; i < j; i++) {
			File file = afile[i];
			if (file.length() != 0L)
				if (file.getName().startsWith("main_file_cache.idx")) {
					int id = Integer.parseInt(file.getName().split(".idx")[1]);
					if (id != 255)
						try {
							indexesFiles.put(Integer.valueOf(id),
									new RandomAccessFile(file, "rw"));
							if (id > lastIndexFileId)
								lastIndexFileId = id;
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					else
						try {
							containersInformFile = new RandomAccessFile(file,
									"rw");
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
				} else if (file.getName().equals("main_file_cache.dat2"))
					try {
						dataFile = new RandomAccessFile(file, "rw");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
		}

		if (lastIndexFileId == -1 || containersInformFile == null
				|| dataFile == null)
			throw new Error("Missing one or more cache files.");
		byte cacheFileBuffer[] = new byte[520];
		containersInformCacheFile = new CacheFile(255, containersInformFile,
				dataFile, 0x7a120, cacheFileBuffer);
		cacheFileManagers = new CacheFileManager[lastIndexFileId + 1];
		for (Iterator iterator = indexesFiles.keySet().iterator(); iterator
				.hasNext();) {
			int indexFileId = ((Integer) iterator.next()).intValue();
			cacheFileManagers[indexFileId] = new CacheFileManager(this,
					new CacheFile(indexFileId,
							(RandomAccessFile) indexesFiles.get(Integer
									.valueOf(indexFileId)), dataFile, 0xf4240,
							cacheFileBuffer), true);
		}

	}

	public byte[] generateUkeys() {
		return UpdateServer.getContainerPacketData(255, 255, 0,
				generateUkeysContainer());
	}

	public byte[] generateUkeysContainer() {
		Stream stream = new Stream(cacheFileManagers.length * 8);
		for (int index = 0; index < cacheFileManagers.length; index++)
			if (cacheFileManagers[index] == null) {
				stream.putInt(0);
				stream.putInt(0);
			} else {
				stream.putInt(cacheFileManagers[index].getInformation()
						.getInformationContainer().getCrc());
				stream.putInt(cacheFileManagers[index].getInformation()
						.getRevision());
			}

		byte ukeysContainer[] = new byte[stream.offset];
		stream.offset = 0;
		stream.getBytes(ukeysContainer, 0, ukeysContainer.length);
		return ukeysContainer;
	}

	public CacheFile getConstainersInformCacheFile() {
		return containersInformCacheFile;
	}

	private CacheFileManager cacheFileManagers[];
	private CacheFile containersInformCacheFile;
}