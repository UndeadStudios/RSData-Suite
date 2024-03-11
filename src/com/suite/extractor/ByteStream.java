/*    */ package com.suite.extractor;
/*    */ 
/*    */ public final class ByteStream
/*    */ {
/*    */   public byte[] Buffer;
/*    */   public int Offset;
/*    */ 
/*    */   public int ReadDWord()
/*    */   {
/* 14 */     return ((this.Buffer[(this.Offset++)] & 0xFF) << 24) + ((this.Buffer[(this.Offset++)] & 0xFF) << 16) + ((this.Buffer[(this.Offset++)] & 0xFF) << 8) + (this.Buffer[(this.Offset++)] & 0xFF);
/*    */   }
/*    */ 
/*    */   public int ReadUnsignedByte()
/*    */   {
/* 19 */     return this.Buffer[(this.Offset++)] & 0xFF;
/*    */   }
/*    */ 
/*    */   public byte ReadSignedByte()
/*    */   {
/* 24 */     return this.Buffer[(this.Offset++)];
/*    */   }
/*    */ 
/*    */   public int ReadUnsignedWord()
/*    */   {
/* 29 */     if (this.Offset + 1 < this.Buffer.length) {
/* 30 */       return ((this.Buffer[(this.Offset++)] & 0xFF) << 8) + (this.Buffer[(this.Offset++)] & 0xFF);
/*    */     }
/* 32 */     return 0;
/*    */   }
/*    */ 
/*    */   public int ReadSignedWord()
/*    */   {
/* 37 */     int i = ((this.Buffer[(this.Offset++)] & 0xFF) << 8) + (this.Buffer[(this.Offset++)] & 0xFF);
/* 38 */     if (i > 32767)
/* 39 */       i -= 65536;
/* 40 */     return i;
/*    */   }
/*    */ 
/*    */   public long ReadQWord(int paramInt)
/*    */   {
/* 45 */     long l1 = ReadDWord() & 0xFFFFFFFF;
/* 46 */     if (paramInt >= 0)
/*    */     {
/* 48 */       throw new NullPointerException();
/*    */     }
/*    */ 
/* 51 */     long l2 = ReadDWord() & 0xFFFFFFFF;
/* 52 */     return (l1 << 32) + l2;
/*    */   }
/*    */ 
/*    */   public String ReadString()
/*    */   {
/* 58 */     int i = this.Offset;
/* 59 */     while (this.Buffer[(this.Offset++)] != 10);
/* 60 */     return new String(this.Buffer, i, this.Offset - i - 1);
/*    */   }
/*    */ 
/*    */   public int Read3Bytes()
/*    */   {
/* 65 */     return ((this.Buffer[(this.Offset++)] & 0xFF) << 16) + ((this.Buffer[(this.Offset++)] & 0xFF) << 8) + (this.Buffer[(this.Offset++)] & 0xFF);
/*    */   }
/*    */ 
/*    */   public byte[] ReadBytes(int paramInt)
/*    */   {
/* 70 */     byte[] arrayOfByte = new byte[paramInt];
/* 71 */     for (int i = 0; i < paramInt; i++) {
/* 72 */       arrayOfByte[i] = this.Buffer[(this.Offset++)];
/*    */     }
/* 74 */     return arrayOfByte;
/*    */   }
/*    */ 
/*    */   public void ReadBytes(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte)
/*    */   {
/* 79 */     for (int i = paramInt2; i < paramInt2 + paramInt1; i++)
/* 80 */       paramArrayOfByte[i] = this.Buffer[(this.Offset++)];
/*    */   }
/*    */ 
/*    */   public ByteStream(byte[] paramArrayOfByte)
/*    */   {
/* 86 */     this.Buffer = paramArrayOfByte;
/* 87 */     this.Offset = 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\HaiderPC\Desktop\Cache Extractor\data\CacheExport2.jar
 * Qualified Name:     cacheexport2.ByteStream
 * JD-Core Version:    0.6.2
 */