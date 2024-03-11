/*    */ package com.suite.extractor;
/*    */ 
/*    */ import java.io.RandomAccessFile;
/*    */ 
/*    */ public class CacheFile
/*    */ {
/*    */   public RandomAccessFile DataFile;
/*    */   public int NumberOfFiles;
/*    */   public int[] FileLength;
/*    */   public int[] FileStart;
/*    */ 
/*    */   public CacheFile(byte[] paramArrayOfByte, RandomAccessFile paramRandomAccessFile)
/*    */   {
/* 18 */     this.DataFile = paramRandomAccessFile;
/* 19 */     this.NumberOfFiles = (paramArrayOfByte.length / 6);
/* 20 */     this.FileStart = new int[this.NumberOfFiles];
/* 21 */     this.FileLength = new int[this.NumberOfFiles];
/* 22 */     ByteStream localByteStream = new ByteStream(paramArrayOfByte);
/* 23 */     for (int i = 0; i < this.NumberOfFiles; i++)
/*    */     {
/* 25 */       this.FileLength[i] = localByteStream.Read3Bytes();
/* 26 */       this.FileStart[i] = localByteStream.Read3Bytes();
/*    */     }
/*    */   }
/*    */ 
/*    */   private byte[] ReadBlock(int paramInt)
/*    */   {
/*    */     try
/*    */     {
/* 35 */       byte[] arrayOfByte = new byte[520];
/* 36 */       this.DataFile.seek(paramInt * 520);
/* 37 */       this.DataFile.readFully(arrayOfByte);
/* 38 */       return arrayOfByte;
/*    */     }
/*    */     catch (Exception localException) {
/*    */     }
/* 42 */     return null;
/*    */   }
/*    */ 
/*    */   public ByteStream GetFile(int paramInt)
/*    */   {
/* 48 */     if (this.FileStart[paramInt] <= 0)
/* 49 */       return null;
/* 50 */     byte[] arrayOfByte1 = new byte[this.FileLength[paramInt]];
/* 51 */     int i = this.FileStart[paramInt];
/* 52 */     int j = 0;
/*    */     while (true)
/*    */     {
/* 55 */       byte[] arrayOfByte2 = ReadBlock(i);
/* 56 */       if (arrayOfByte2 == null)
/* 57 */         return null;
/* 58 */       i = ((arrayOfByte2[4] & 0xFF) << 16) + ((arrayOfByte2[5] & 0xFF) << 8) + (arrayOfByte2[6] & 0xFF);
/* 59 */       if (this.FileLength[paramInt] - j - 8 > 512)
/*    */       {
/* 61 */         System.arraycopy(arrayOfByte2, 8, arrayOfByte1, j, 512);
/*    */       }
/*    */       else {
/* 64 */         System.arraycopy(arrayOfByte2, 8, arrayOfByte1, j, this.FileLength[paramInt] - j - 8);
/* 65 */         return new ByteStream(arrayOfByte1);
/*    */       }
/* 67 */       j += 512;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\HaiderPC\Desktop\Cache Extractor\data\CacheExport2.jar
 * Qualified Name:     cacheexport2.CacheFile
 * JD-Core Version:    0.6.2
 */