/*    */ package com.suite.extractor;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.zip.GZIPInputStream;
/*    */ 
/*    */ public class Unpack
/*    */ {
/*    */   public static byte[] Unpack1(ByteStream paramByteStream, String paramString)
/*    */   {
/*    */     byte[] arrayOfByte2;
/* 24 */     if ((paramByteStream.ReadUnsignedByte() == 31) && (paramByteStream.ReadUnsignedByte() == 139) && (paramByteStream.ReadUnsignedByte() == 8))
/*    */     {
/* 26 */       int i = 0;
/* 27 */       byte[] arrayOfByte1 = new byte[9999999];
/*    */       try
/*    */       {
/* 30 */         GZIPInputStream localGZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(paramByteStream.Buffer));
/*    */         while (true)
/*    */         {
/* 33 */           if (i == arrayOfByte1.length)
/* 34 */             throw new RuntimeException("buffer overflow!");
/* 35 */           int k = localGZIPInputStream.read(arrayOfByte1, i, arrayOfByte1.length - i);
/* 36 */           if (k == -1)
/*    */             break;
/* 38 */           i += k;
/*    */         }
/*    */       } catch (IOException localIOException) {
/*    */       }
/* 42 */       arrayOfByte2 = new byte[i];
/* 43 */       for (int k = 0; k < i; k++) {
/* 44 */         arrayOfByte2[k] = arrayOfByte1[k];
/*    */       }
/* 46 */       return arrayOfByte2;
/*    */     }
/* 48 */     JagFile localJagFile = new JagFile(paramByteStream.Buffer);
/* 49 */     for (int j = 0; j < localJagFile.datasize; j++)
/*    */     {
/* 51 */       arrayOfByte2 = new byte[localJagFile.filesize[j]];
/* 52 */       byte[] arrayOfByte3 = localJagFile.readfile(j, arrayOfByte2);
/* 53 */       if (arrayOfByte3 != null) {
/* 54 */         FileOperations.WriteFile(paramString + "/" + Core.CheckName(localJagFile.filename[j]), arrayOfByte3);
				System.out.println("Extracted :"+ Core.CheckName(localJagFile.filename[j]));
/*    */       }
/*    */     }
/* 57 */     return paramByteStream.Buffer;
/*    */   }
/*    */ 
/*    */   public static byte[] Unpack2(ByteStream paramByteStream)
/*    */   {
/* 62 */     int i = paramByteStream.ReadUnsignedByte();
/* 63 */     int j = paramByteStream.ReadDWord();
/* 64 */     int k = paramByteStream.ReadDWord();
/* 65 */     if ((k > 0) && (k < 10000000))
/*    */     {
/* 67 */       byte[] arrayOfByte = new byte[k];
/* 68 */       switch (i)
/*    */       {
/*    */       case 1:
/* 71 */         PackJAG.method73(arrayOfByte, k, paramByteStream.Buffer, j, 9);
/* 72 */         break;
/*    */       case 2:
/*    */         try
/*    */         {
/* 77 */           DataInputStream localDataInputStream = new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(paramByteStream.Buffer, 9, k)));
/* 78 */           localDataInputStream.readFully(arrayOfByte);
/* 79 */           localDataInputStream.close();
/*    */         }
/*    */         catch (IOException localIOException)
/*    */         {
/*    */         }
/*    */       default:
/* 85 */         arrayOfByte = paramByteStream.Buffer;
/*    */       }
/*    */ 
/* 88 */       return arrayOfByte;
/*    */     }
/*    */ 
/* 91 */     return paramByteStream.Buffer;
/*    */   }
/*    */ }

/* Location:           C:\Users\HaiderPC\Desktop\Cache Extractor\data\CacheExport2.jar
 * Qualified Name:     cacheexport2.Unpack
 * JD-Core Version:    0.6.2
 */