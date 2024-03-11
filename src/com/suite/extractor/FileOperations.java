/*    */ package com.suite.extractor;
/*    */ 
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ 
/*    */ public class FileOperations
/*    */ {
/*    */   public static int TotalRead;
/*    */   public static int TotalWrite;
/*    */   public static int CompleteWrite;
/*    */ 
/*    */   public static final ByteStream ReadFileStream(String paramString)
/*    */   {
/* 22 */     return new ByteStream(ReadFile(paramString));
/*    */   }
/*    */ 
/*    */   public static final void WriteFileStream(String paramString, ByteStream paramByteStream)
/*    */   {
/* 27 */     WriteFile(paramString, paramByteStream.Buffer);
/*    */   }
/*    */ 
/*    */   public static final String ReadFileText(String paramString)
/*    */   {
/* 32 */     return new String(ReadFile(paramString));
/*    */   }
/*    */ 
/*    */   public static void WriteFileText(String paramString, byte[] paramArrayOfByte)
/*    */   {
/* 37 */     WriteFile(paramString, paramString.getBytes());
/*    */   }
/*    */ 
/*    */   public static final byte[] ReadFile(String paramString)
/*    */   {
/*    */     try
/*    */     {
/* 44 */       File localFile = new File(paramString);
/* 45 */       int i = (int)localFile.length();
/* 46 */       byte[] arrayOfByte = new byte[i];
/* 47 */       DataInputStream localDataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(paramString)));
/* 48 */       localDataInputStream.readFully(arrayOfByte, 0, i);
/* 49 */       localDataInputStream.close();
/* 50 */       TotalRead += 1;
/* 51 */       return arrayOfByte;
/*    */     }
/*    */     catch (Throwable localThrowable)
/*    */     {
/* 55 */       System.out.println("Read Error: " + paramString);
/*    */     }
/* 57 */     return null;
/*    */   }
/*    */ 
/*    */   public static final void WriteFile(String paramString, byte[] paramArrayOfByte)
/*    */   {
/*    */     try
/*    */     {
/* 64 */       new File(new File(paramString).getParent()).mkdirs();
/* 65 */       FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
/* 66 */       localFileOutputStream.write(paramArrayOfByte, 0, paramArrayOfByte.length);
/* 67 */       localFileOutputStream.close();
/* 68 */       TotalWrite += 1;
/* 69 */       CompleteWrite += 1;
/*    */     }
/*    */     catch (Throwable localThrowable)
/*    */     {
/* 73 */       System.out.println("Write Error: " + paramString);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\HaiderPC\Desktop\Cache Extractor\data\CacheExport2.jar
 * Qualified Name:     cacheexport2.FileOperations
 * JD-Core Version:    0.6.2
 */