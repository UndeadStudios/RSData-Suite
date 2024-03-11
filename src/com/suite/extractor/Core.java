/*     */ package com.suite.extractor;
/*     */ import java.io.RandomAccessFile;

/*     */ 
/*     */ public class Core
/*     */ {
/* 163 */   public static boolean[] fFound = new boolean[0];
/* 164 */   public static int[] fInts = new int[0];
/* 165 */   public static String[] fNames = new String[0];
/* 166 */   public static String InputDir = "./obj/";
/* 167 */   public static String OutputDir = "./out/";
/*     */   public static CacheFile[] Cache;
/*     */   public static String[] CacheType;
/*     */   public static String[] CacheExt;
/*     */   public static RandomAccessFile CacheData;
/* 172 */   public static String CacheVersion = "";
			public static boolean wantSpritesExtracted = false;
/*     */   public static String NameFile = Constants.nameFile;
/*     */ 
/*     */   public static void start()
/*     */   {
/*  36 */       processConfiguration();
/*  37 */       if (NameFile != null)
/*  38 */         ProcessNames(FileOperations.ReadFileText("./data/names.dat"));
				try {
/*  39 */       ExtractCacheFiles();
				} catch (Exception e) {
					e.printStackTrace();
				}
/*     */   }
/*     */ 
/*     */   public static void ExtractCacheFiles()
/*     */   {
/*  48 */     System.out.println("Extracting Cache Files...");
/*  49 */     for (int i = 0; i < Cache.length; i++)
/*     */     {
				if (Constants.toExtract[i]) {
				if (Cache[i] == null) {
					System.out.println("Cache is null skipped: index "+i);
					break;
				}
/*  51 */       System.out.println(" - Extracting Expected " + Cache[i].NumberOfFiles + " in Cache " + i + " \"" + CacheType[i] + "\"...");
/*  52 */       FileOperations.TotalWrite = 0;
/*  53 */       for (int j = 0; j < Cache[i].NumberOfFiles; j++)
/*     */       {
/*  55 */         ByteStream localByteStream = Cache[i].GetFile(j);
/*  56 */         if (localByteStream != null)
/*     */         {
/*     */           byte[] arrayOfByte;
/*  58 */           if (CacheVersion == "new")
/*     */           {
/*  60 */             arrayOfByte = Unpack.Unpack2(localByteStream);
/*  61 */             FileOperations.WriteFile(OutputDir + CacheType[i] + "/" + j + "." + CacheExt[i], arrayOfByte);
/*     */           } else {
/*  64 */             arrayOfByte = Unpack.Unpack1(localByteStream, OutputDir + CacheType[i] + "/" + j);
/*  65 */             FileOperations.WriteFile(OutputDir + CacheType[i] + "/" + j + "." + CacheExt[i], arrayOfByte);
/*     */           }
/*     */         } else {
					System.out.println("localByteStream is null");
					}
/*     */       }
/*  69 */       System.out.println("  - " + FileOperations.TotalWrite + " Files Extracted to /" + CacheType[i] + "/*." + CacheExt[i] + ".");
				//break;
/*     */     }
			}
/*  77 */    // if (NameFile != null)
/*     */    // {
/*  79 */    //   System.out.println("Now Checking to see if any Naming was not found");
/*  80 */    //   for (int i = 0; i < fFound.length; i++)
/*  81 */    //     if (fFound[i] == false)
/*  82 */    //       System.out.println("Did not find " + fNames[i] + " filesize: " + fInts[i]);
/*     */    // }
				System.out.println("Succesfully extracted.");
/*     */   }
/*     */ 
/*     */   public static String CheckName(int paramInt)
/*     */   {
/*  89 */     for (int i = 0; i < fInts.length; i++) {
/*  90 */       if (fInts[i] == paramInt)
/*     */       {
/*  92 */         fFound[i] = true;
/*  93 */         return fNames[i];
/*     */       }
/*     */     }
/*  96 */     System.out.println("Could Not Find Name for: " + paramInt);
/*  97 */     return Integer.toString(paramInt) + ".DAT";
/*     */   }
/*     */ 
/*     */   public static void ProcessNames(String paramString)
/*     */   {
/* 102 */     System.out.println("Parsing Names...");
/* 103 */     fNames = paramString.split("\r\n");
/* 104 */     fFound = new boolean[fNames.length];
/* 105 */     int[] arrayOfInt = new int[fNames.length];
/* 106 */     for (int i = 0; i < fNames.length; i++) {
/* 107 */       if (!fNames[i].startsWith("#"))
/*     */       {
/* 109 */         String[] arrayOfString = fNames[i].split(",");
/* 110 */         arrayOfInt[i] = Integer.parseInt(arrayOfString[0]);
/* 111 */         fNames[i] = arrayOfString[1];
/*     */       }
/*     */     }
/* 114 */     fInts = arrayOfInt;
/* 115 */     System.out.println(" - " + fNames.length + " Names read.");
/*     */   }
/*     */ 
/*     */   public static void processConfiguration()
/*     */   {
					try {
/* 120 */     		System.out.println("Parsing configuration...");
/* 130 */         //  NameFile = Constants.nameFile;
/* 134 */           CacheVersion = Constants.format;
					InputDir = Constants.inputFolder;
/* 157 */           OutputDir = Constants.outputFolder;

					if (CacheVersion == "old") {
/* 138 */             Cache = new CacheFile[5];
/* 139 */             CacheType = new String[5];
/* 140 */             CacheExt = new String[5];
/* 141 */             CacheData = new RandomAccessFile(InputDir + "/main_file_cache.dat", "rw");
					} else {
						Cache = new CacheFile[Constants.toExtract.length];
						CacheType = new String[Constants.toExtract.length];
						CacheExt = new String[Constants.toExtract.length];
						CacheData = new RandomAccessFile(InputDir + "/main_file_cache.dat2", "rw");
					}

/* 147 */         for (int i = 0; i < Constants.toExtract.length; i++) {
					if (Constants.toExtract[i] == true) {
/* 149 */           Cache[i] = new CacheFile(FileOperations.ReadFile(InputDir + "/main_file_cache.idx"+i), CacheData);
/* 150 */           CacheType[i] = "/index"+i;
/* 151 */           CacheExt[i] = "dat";
/* 152 */           }
/*     */         }
/* 155 */          
					} catch (Exception e) {
						e.printStackTrace();
					}
/*     */       }

/* Location:           C:\Users\HaiderPC\Desktop\Cache Extractor\data\CacheExport2.jar
 * Qualified Name:     cacheexport2.Main
 * JD-Core Version:    0.6.2
 */

}