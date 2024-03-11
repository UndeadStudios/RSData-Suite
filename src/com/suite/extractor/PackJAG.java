/*     */ package com.suite.extractor;
/*     */ 
/*     */ public final class PackJAG
/*     */ {
/* 614 */   private static PackHeadJAG aClass5_43 = new PackHeadJAG();
/*     */ 
/*     */   private static byte method67(PackHeadJAG class5)
/*     */   {
/*  33 */     return (byte)method74(8, class5);
/*     */   }
/*     */ 
/*     */   private static void method68(PackHeadJAG class5)
/*     */   {
/*  38 */     boolean flag = false;
/*  39 */     boolean flag1 = false;
/*  40 */     boolean flag2 = false;
/*  41 */     boolean flag3 = false;
/*  42 */     boolean flag4 = false;
/*  43 */     boolean flag5 = false;
/*  44 */     boolean flag6 = false;
/*  45 */     boolean flag7 = false;
/*  46 */     boolean flag8 = false;
/*  47 */     boolean flag9 = false;
/*  48 */     boolean flag10 = false;
/*  49 */     boolean flag11 = false;
/*  50 */     boolean flag12 = false;
/*  51 */     boolean flag13 = false;
/*  52 */     boolean flag14 = false;
/*  53 */     boolean flag15 = false;
/*  54 */     boolean flag16 = false;
/*  55 */     boolean flag17 = false;
/*  56 */     boolean flag18 = false;
/*  57 */     boolean flag19 = false;
/*  58 */     int k8 = 0;
/*  59 */     int[] ai = null;
/*  60 */     int[] ai1 = null;
/*  61 */     int[] ai2 = null;
/*  62 */     class5.anInt69 = 1;
/*  63 */     if (PackHeadJAG.anIntArray78 == null)
/*  64 */       PackHeadJAG.anIntArray78 = new int[class5.anInt69 * 100000];
/*  65 */     boolean flag20 = true;
/*  66 */     while (flag20)
/*     */     {
/*  68 */       byte byte0 = method67(class5);
/*  69 */       if (byte0 == 23)
/*  70 */         return;
/*  71 */       byte0 = method67(class5);
/*  72 */       byte0 = method67(class5);
/*  73 */       byte0 = method67(class5);
/*  74 */       byte0 = method67(class5);
/*  75 */       byte0 = method67(class5);
/*  76 */       class5.anInt70 += 1;
/*  77 */       byte0 = method67(class5);
/*  78 */       byte0 = method67(class5);
/*  79 */       byte0 = method67(class5);
/*  80 */       byte0 = method67(class5);
/*  81 */       byte0 = method71(class5);
/*  82 */       if (byte0 != 0)
/*  83 */         class5.aBoolean66 = true;
/*     */       else
/*  85 */         class5.aBoolean66 = false;
/*  86 */       if (class5.aBoolean66)
/*  87 */         System.out.println("PANIC! RANDOMISED BLOCK!");
/*  88 */       class5.anInt71 = 0;
/*  89 */       byte0 = method67(class5);
/*  90 */       class5.anInt71 = (class5.anInt71 << 8 | byte0 & 0xFF);
/*  91 */       byte0 = method67(class5);
/*  92 */       class5.anInt71 = (class5.anInt71 << 8 | byte0 & 0xFF);
/*  93 */       byte0 = method67(class5);
/*  94 */       class5.anInt71 = (class5.anInt71 << 8 | byte0 & 0xFF);
/*  95 */       for (int j = 0; j < 16; j++)
/*     */       {
/*  97 */         byte byte1 = method71(class5);
/*  98 */         if (byte1 == 1)
/*  99 */           class5.aBooleanArray81[j] = true;
/*     */         else {
/* 101 */           class5.aBooleanArray81[j] = false;
/*     */         }
/*     */       }
/* 104 */       for (int k = 0; k < 256; k++) {
/* 105 */         class5.aBooleanArray80[k] = false;
/*     */       }
/* 107 */       for (int l = 0; l < 16; l++) {
/* 108 */         if (class5.aBooleanArray81[l] != false)
/*     */         {
/* 110 */           for (int i3 = 0; i3 < 16; i3++)
/*     */           {
/* 112 */             byte byte2 = method71(class5);
/* 113 */             if (byte2 == 1) {
/* 114 */               class5.aBooleanArray80[(l * 16 + i3)] = true;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 119 */       method70(class5);
/* 120 */       int i4 = class5.anInt79 + 2;
/* 121 */       int j4 = method74(3, class5);
/* 122 */       int k4 = method74(15, class5);
/* 123 */       for (int i1 = 0; i1 < k4; i1++)
/*     */       {
/* 125 */         int j3 = 0;
/*     */         while (true)
/*     */         {
/* 128 */           byte byte3 = method71(class5);
/* 129 */           if (byte3 == 0)
/*     */             break;
/* 131 */           j3++;
/*     */         }
/* 133 */         class5.aByteArray86[i1] = ((byte)j3);
/*     */       }
/*     */ 
/* 136 */       byte[] abyte0 = new byte[6];
/* 137 */       for (byte byte16 = 0; byte16 < j4; byte16 = (byte)(byte16 + 1)) {
/* 138 */         abyte0[byte16] = byte16;
/*     */       }
/* 140 */       for (int j1 = 0; j1 < k4; j1++)
/*     */       {
/* 142 */         byte byte17 = class5.aByteArray86[j1];
/* 143 */         byte byte15 = abyte0[byte17];
/* 144 */         for (; byte17 > 0; byte17 = (byte)(byte17 - 1)) {
/* 145 */           abyte0[byte17] = abyte0[(byte17 - 1)];
/*     */         }
/* 147 */         abyte0[0] = byte15;
/* 148 */         class5.aByteArray85[j1] = byte15;
/*     */       }
/*     */ 
/* 151 */       for (int k3 = 0; k3 < j4; k3++)
/*     */       {
/* 153 */         int l6 = method74(5, class5);
/* 154 */         int k1 = 0;
/*     */         do
/*     */         {
/*     */           while (true)
/*     */           {
/* 159 */             byte byte4 = method71(class5);
/* 160 */             if (byte4 == 0)
/*     */               break;
/* 162 */             byte4 = method71(class5);
/* 163 */             if (byte4 == 0)
/* 164 */               l6++;
/*     */             else
/* 166 */               l6--;
/*     */           }
/* 168 */           class5.aByteArrayArray87[k3][k1] = ((byte)l6);
/* 169 */           k1++; } while (k1 < i4);
/*     */       }
/*     */ 
/* 172 */       for (int l3 = 0; l3 < j4; l3++)
/*     */       {
/* 174 */         byte byte8 = 32;
/* 175 */         int i = 0;
/* 176 */         for (int l1 = 0; l1 < i4; l1++)
/*     */         {
/* 178 */           if (class5.aByteArrayArray87[l3][l1] > i)
/* 179 */             i = class5.aByteArrayArray87[l3][l1];
/* 180 */           if (class5.aByteArrayArray87[l3][l1] < byte8) {
/* 181 */             byte8 = class5.aByteArrayArray87[l3][l1];
/*     */           }
/*     */         }
/* 184 */         method72(class5.anIntArrayArray88[l3], class5.anIntArrayArray89[l3], class5.anIntArrayArray90[l3], class5.aByteArrayArray87[l3], byte8, i, i4);
/* 185 */         class5.anIntArray91[l3] = byte8;
/*     */       }
/*     */ 
/* 188 */       int l4 = class5.anInt79 + 1;
/* 189 */       int l5 = 100000 * class5.anInt69;
/* 190 */       int i5 = -1;
/* 191 */       int j5 = 0;
/* 192 */       for (int i2 = 0; i2 <= 255; i2++) {
/* 193 */         class5.anIntArray74[i2] = 0;
/*     */       }
/* 195 */       int l9 = 4095;
/* 196 */       for (int l8 = 15; l8 >= 0; l8--)
/*     */       {
/* 198 */         for (int j9 = 15; j9 >= 0; j9--)
/*     */         {
/* 200 */           class5.aByteArray83[l9] = ((byte)(l8 * 16 + j9));
/* 201 */           l9--;
/*     */         }
/*     */ 
/* 204 */         class5.anIntArray84[l8] = (l9 + 1);
/*     */       }
/*     */ 
/* 207 */       int i6 = 0;
/* 208 */       if (j5 == 0)
/*     */       {
/* 210 */         i5++;
/* 211 */         j5 = 50;
/* 212 */         byte byte12 = class5.aByteArray85[i5];
/* 213 */         k8 = class5.anIntArray91[byte12];
/* 214 */         ai = class5.anIntArrayArray88[byte12];
/* 215 */         ai2 = class5.anIntArrayArray90[byte12];
/* 216 */         ai1 = class5.anIntArrayArray89[byte12];
/*     */       }
/* 218 */       j5--;
/* 219 */       int i7 = k8;
/*     */       byte byte9;
int l7;
/* 222 */       for (l7 = method74(i7, class5); l7 > ai[i7]; l7 = l7 << 1 | byte9)
/*     */       {
/* 224 */         i7++;
/* 225 */         byte9 = method71(class5);
/*     */       }
/*     */ 
/* 228 */       for (int k5 = ai2[(l7 - ai1[i7])]; k5 != l4; ) {
/* 229 */         if ((k5 == 0) || (k5 == 1))
/*     */         {
/* 231 */           int j6 = -1;
/* 232 */           int k6 = 1;
/*     */           do
/*     */           {
/* 235 */             if (k5 == 0) {
/* 236 */               j6 += 1 * k6;
/*     */             }
/* 238 */             else if (k5 == 1)
/* 239 */               j6 += 2 * k6;
/* 240 */             k6 *= 2;
/* 241 */             if (j5 == 0)
/*     */             {
/* 243 */               i5++;
/* 244 */               j5 = 50;
/* 245 */               byte byte13 = class5.aByteArray85[i5];
/* 246 */               k8 = class5.anIntArray91[byte13];
/* 247 */               ai = class5.anIntArrayArray88[byte13];
/* 248 */               ai2 = class5.anIntArrayArray90[byte13];
/* 249 */               ai1 = class5.anIntArrayArray89[byte13];
/*     */             }
/* 251 */             j5--;
/* 252 */             int j7 = k8;
/*     */             byte byte10;
						int i8;
/* 255 */             for (i8 = method74(j7, class5); i8 > ai[j7]; i8 = i8 << 1 | byte10)
/*     */             {
/* 257 */               j7++;
/* 258 */               byte10 = method71(class5);
/*     */             }
/*     */ 
/* 261 */             k5 = ai2[(i8 - ai1[j7])];
/* 262 */           }while ((k5 == 0) || (k5 == 1));
/* 263 */           j6++;
/* 264 */           byte byte5 = class5.aByteArray82[(class5.aByteArray83[class5.anIntArray84[0]] & 0xFF)];
/* 265 */           class5.anIntArray74[(byte5 & 0xFF)] += j6;
/* 266 */           for (; j6 > 0; j6--)
/*     */           {
/* 268 */             PackHeadJAG.anIntArray78[i6] = (byte5 & 0xFF);
/* 269 */             i6++;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 274 */           int j11 = k5 - 1;
/*     */           byte byte6;
/* 276 */           if (j11 < 16)
/*     */           {
/* 278 */             int j10 = class5.anIntArray84[0];
/* 279 */             byte6 = class5.aByteArray83[(j10 + j11)];
/* 280 */             for (; j11 > 3; j11 -= 4)
/*     */             {
/* 282 */               int k11 = j10 + j11;
/* 283 */               class5.aByteArray83[k11] = class5.aByteArray83[(k11 - 1)];
/* 284 */               class5.aByteArray83[(k11 - 1)] = class5.aByteArray83[(k11 - 2)];
/* 285 */               class5.aByteArray83[(k11 - 2)] = class5.aByteArray83[(k11 - 3)];
/* 286 */               class5.aByteArray83[(k11 - 3)] = class5.aByteArray83[(k11 - 4)];
/*     */             }
/*     */ 
/* 289 */             for (; j11 > 0; j11--) {
/* 290 */               class5.aByteArray83[(j10 + j11)] = class5.aByteArray83[(j10 + j11 - 1)];
/*     */             }
/* 292 */             class5.aByteArray83[j10] = byte6;
/*     */           }
/*     */           else {
/* 295 */             int l10 = j11 / 16;
/* 296 */             int i11 = j11 % 16;
/* 297 */             int k10 = class5.anIntArray84[l10] + i11;
/* 298 */             byte6 = class5.aByteArray83[k10];
/* 299 */             for (; k10 > class5.anIntArray84[l10]; k10--) {
/* 300 */               class5.aByteArray83[k10] = class5.aByteArray83[(k10 - 1)];
/*     */             }
/* 302 */             class5.anIntArray84[l10] += 1;
/* 303 */             for (; l10 > 0; l10--)
/*     */             {
/* 305 */               class5.anIntArray84[l10] -= 1;
/* 306 */               class5.aByteArray83[class5.anIntArray84[l10]] = class5.aByteArray83[(class5.anIntArray84[(l10 - 1)] + 16 - 1)];
/*     */             }
/*     */ 
/* 309 */             class5.anIntArray84[0] -= 1;
/* 310 */             class5.aByteArray83[class5.anIntArray84[0]] = byte6;
/* 311 */             if (class5.anIntArray84[0] == 0)
/*     */             {
/* 313 */               int i10 = 4095;
/* 314 */               for (int i9 = 15; i9 >= 0; i9--)
/*     */               {
/* 316 */                 for (int k9 = 15; k9 >= 0; k9--)
/*     */                 {
/* 318 */                   class5.aByteArray83[i10] = class5.aByteArray83[(class5.anIntArray84[i9] + k9)];
/* 319 */                   i10--;
/*     */                 }
/*     */ 
/* 322 */                 class5.anIntArray84[i9] = (i10 + 1);
/*     */               }
/*     */             }
/*     */           }
/*     */ 
/* 327 */           class5.anIntArray74[(class5.aByteArray82[(byte6 & 0xFF)] & 0xFF)] += 1;
/* 328 */           PackHeadJAG.anIntArray78[i6] = (class5.aByteArray82[(byte6 & 0xFF)] & 0xFF);
/* 329 */           i6++;
/* 330 */           if (j5 == 0)
/*     */           {
/* 332 */             i5++;
/* 333 */             j5 = 50;
/* 334 */             byte byte14 = class5.aByteArray85[i5];
/* 335 */             k8 = class5.anIntArray91[byte14];
/* 336 */             ai = class5.anIntArrayArray88[byte14];
/* 337 */             ai2 = class5.anIntArrayArray90[byte14];
/* 338 */             ai1 = class5.anIntArrayArray89[byte14];
/*     */           }
/* 340 */           j5--;
/* 341 */           int k7 = k8;
/*     */           byte byte11;
					int j8;
/* 344 */           for (j8 = method74(k7, class5); j8 > ai[k7]; j8 = j8 << 1 | byte11)
/*     */           {
/* 346 */             k7++;
/* 347 */             byte11 = method71(class5);
/*     */           }
/*     */ 
/* 350 */           k5 = ai2[(j8 - ai1[k7])];
/*     */         }
/*     */       }
/* 353 */       class5.anInt65 = 0;
/* 354 */       class5.aByte64 = 0;
/* 355 */       class5.anIntArray76[0] = 0;
/* 356 */       for (int j2 = 1; j2 <= 256; j2++) {
/* 357 */         class5.anIntArray76[j2] = class5.anIntArray74[(j2 - 1)];
/*     */       }
/* 359 */       for (int k2 = 1; k2 <= 256; k2++) {
/* 360 */         class5.anIntArray76[k2] += class5.anIntArray76[(k2 - 1)];
/*     */       }
/* 362 */       for (int l2 = 0; l2 < i6; l2++)
/*     */       {
/* 364 */         byte byte7 = (byte)(PackHeadJAG.anIntArray78[l2] & 0xFF);
/* 365 */         PackHeadJAG.anIntArray78[class5.anIntArray76[(byte7 & 0xFF)]] |= l2 << 8;
/* 366 */         class5.anIntArray76[(byte7 & 0xFF)] += 1;
/*     */       }
/*     */ 
/* 369 */       class5.anInt72 = (PackHeadJAG.anIntArray78[class5.anInt71] >> 8);
/* 370 */       class5.anInt75 = 0;
/* 371 */       class5.anInt72 = PackHeadJAG.anIntArray78[class5.anInt72];
/* 372 */       class5.anInt73 = ((byte)(class5.anInt72 & 0xFF));
/* 373 */       class5.anInt72 >>= 8;
/* 374 */       class5.anInt75 += 1;
/* 375 */       class5.anInt92 = i6;
/* 376 */       method69(class5);
/* 377 */       if ((class5.anInt75 == class5.anInt92 + 1) && (class5.anInt65 == 0))
/* 378 */         flag20 = true;
/*     */       else
/* 380 */         flag20 = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void method69(PackHeadJAG class5)
/*     */   {
/* 386 */     byte byte4 = class5.aByte64;
/* 387 */     int i = class5.anInt65;
/* 388 */     int j = class5.anInt75;
/* 389 */     int k = class5.anInt73;
/* 390 */     int[] ai = PackHeadJAG.anIntArray78;
/* 391 */     int l = class5.anInt72;
/* 392 */     byte[] abyte0 = class5.aByteArray59;
/* 393 */     int i1 = class5.anInt60;
/* 394 */     int j1 = class5.anInt61;
/* 395 */     int k1 = j1;
/* 396 */     int l1 = class5.anInt92 + 1;
/*     */     while (true)
/*     */     {
/* 400 */       if (i > 0)
/*     */       {
/*     */         while (true)
/*     */         {
/* 404 */           if (j1 == 0)
/*     */             break;
/* 406 */           if (i == 1)
/*     */             break;
/* 408 */           abyte0[i1] = byte4;
/* 409 */           i--;
/* 410 */           i1++;
/* 411 */           j1--;
/*     */         }
/* 413 */         if (j1 == 0)
/*     */         {
/* 415 */           i = 1;
/* 416 */           break;
/*     */         }
/* 418 */         abyte0[i1] = byte4;
/* 419 */         i1++;
/* 420 */         j1--;
/*     */       }
/* 422 */       boolean flag = true;
/* 423 */       while (flag)
/*     */       {
/* 425 */         flag = false;
/* 426 */         if (j == l1)
/*     */         {
/* 428 */           i = 0;
/* 429 */           break;
/*     */         }
/* 431 */         byte4 = (byte)k;
/* 432 */         l = ai[l];
/* 433 */         byte byte0 = (byte)(l & 0xFF);
/* 434 */         l >>= 8;
/* 435 */         j++;
/* 436 */         if (byte0 != k)
/*     */         {
/* 438 */           k = byte0;
/* 439 */           if (j1 == 0)
/*     */           {
/* 441 */             i = 1;
/* 442 */             break;
/*     */           }
/* 444 */           abyte0[i1] = byte4;
/* 445 */           i1++;
/* 446 */           j1--;
/* 447 */           flag = true;
/*     */         }
/* 452 */         else if (j == l1)
/*     */         {
/* 454 */           if (j1 == 0)
/*     */           {
/* 456 */             i = 1;
/* 457 */             break;
/*     */           }
/* 459 */           abyte0[i1] = byte4;
/* 460 */           i1++;
/* 461 */           j1--;
/* 462 */           flag = true;
/*     */         }
/*     */       }
/* 464 */       i = 2;
/* 465 */       l = ai[l];
/* 466 */       byte byte1 = (byte)(l & 0xFF);
/* 467 */       l >>= 8;
/* 468 */       j++; if (j != l1)
/* 469 */         if (byte1 != k)
/*     */         {
/* 471 */           k = byte1;
/*     */         }
/*     */         else {
/* 474 */           i = 3;
/* 475 */           l = ai[l];
/* 476 */           byte byte2 = (byte)(l & 0xFF);
/* 477 */           l >>= 8;
/* 478 */           j++; if (j != l1)
/* 479 */             if (byte2 != k)
/*     */             {
/* 481 */               k = byte2;
/*     */             }
/*     */             else {
/* 484 */               l = ai[l];
/* 485 */               byte byte3 = (byte)(l & 0xFF);
/* 486 */               l >>= 8;
/* 487 */               j++;
/* 488 */               i = (byte3 & 0xFF) + 4;
/* 489 */               l = ai[l];
/* 490 */               k = (byte)(l & 0xFF);
/* 491 */               l >>= 8;
/* 492 */               j++;
/*     */             }
/*     */         }
/*     */     }
			  int i2 = class5.anInt62;
/* 497 */     class5.anInt62 += k1 - j1;
/* 498 */     if (class5.anInt62 < i2)
/* 499 */       class5.anInt63 += 1;
/* 500 */     class5.aByte64 = byte4;
/* 501 */     class5.anInt65 = i;
/* 502 */     class5.anInt75 = j;
/* 503 */     class5.anInt73 = k;
/* 504 */     PackHeadJAG.anIntArray78 = ai;
/* 505 */     class5.anInt72 = l;
/* 506 */     class5.aByteArray59 = abyte0;
/* 507 */     class5.anInt60 = i1;
/* 508 */     class5.anInt61 = j1;
/*     */   }
/*     */ 
/*     */   private static void method70(PackHeadJAG class5)
/*     */   {
/* 513 */     class5.anInt79 = 0;
/* 514 */     for (int i = 0; i < 256; i++)
/* 515 */       if (class5.aBooleanArray80[i] != false)
/*     */       {
/* 517 */         class5.aByteArray82[class5.anInt79] = ((byte)i);
/* 518 */         class5.anInt79 += 1;
/*     */       }
/*     */   }
/*     */ 
/*     */   private static byte method71(PackHeadJAG class5)
/*     */   {
/* 525 */     return (byte)method74(1, class5);
/*     */   }
/*     */ 
/*     */   private static void method72(int[] ai, int[] ai1, int[] ai2, byte[] abyte0, int i, int j, int k)
/*     */   {
/* 530 */     int l = 0;
/* 531 */     for (int i1 = i; i1 <= j; i1++)
/*     */     {
/* 533 */       for (int l2 = 0; l2 < k; l2++) {
/* 534 */         if (abyte0[l2] == i1)
/*     */         {
/* 536 */           ai2[l] = l2;
/* 537 */           l++;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 542 */     for (int j1 = 0; j1 < 23; j1++) {
/* 543 */       ai1[j1] = 0;
/*     */     }
/* 545 */     for (int k1 = 0; k1 < k; k1++) {
/* 546 */       ai1[(abyte0[k1] + 1)] += 1;
/*     */     }
/* 548 */     for (int l1 = 1; l1 < 23; l1++) {
/* 549 */       ai1[l1] += ai1[(l1 - 1)];
/*     */     }
/* 551 */     for (int i2 = 0; i2 < 23; i2++) {
/* 552 */       ai[i2] = 0;
/*     */     }
/* 554 */     int i3 = 0;
/* 555 */     for (int j2 = i; j2 <= j; j2++)
/*     */     {
/* 557 */       i3 += ai1[(j2 + 1)] - ai1[j2];
/* 558 */       ai[j2] = (i3 - 1);
/* 559 */       i3 <<= 1;
/*     */     }
/*     */ 
/* 562 */     for (int k2 = i + 1; k2 <= j; k2++)
/* 563 */       ai1[k2] = ((ai[(k2 - 1)] + 1 << 1) - ai1[k2]);
/*     */   }
/*     */ 
/*     */   public static int method73(byte[] abyte0, int i, byte[] abyte1, int j, int k)
/*     */   {
/* 569 */     synchronized (aClass5_43)
/*     */     {
/* 571 */       aClass5_43.aByteArray54 = abyte1;
/* 572 */       aClass5_43.anInt55 = k;
/* 573 */       aClass5_43.aByteArray59 = abyte0;
/* 574 */       aClass5_43.anInt60 = 0;
/* 575 */       aClass5_43.anInt56 = j;
/* 576 */       aClass5_43.anInt61 = i;
/* 577 */       aClass5_43.anInt68 = 0;
/* 578 */       aClass5_43.anInt67 = 0;
/* 579 */       aClass5_43.anInt57 = 0;
/* 580 */       aClass5_43.anInt58 = 0;
/* 581 */       aClass5_43.anInt62 = 0;
/* 582 */       aClass5_43.anInt63 = 0;
/* 583 */       aClass5_43.anInt70 = 0;
/* 584 */       method68(aClass5_43);
/* 585 */       i -= aClass5_43.anInt61;
/* 586 */       int l = i;
/* 587 */       return l;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static int method74(int i, PackHeadJAG class5)
/*     */   {
	int j;
/*     */     while (true)
/*     */     {
/* 596 */       if (class5.anInt68 >= i)
/*     */       {
/* 598 */         int k = class5.anInt67 >> class5.anInt68 - i & (1 << i) - 1;
/* 599 */         class5.anInt68 -= i;
/* 600 */         j = k;
/* 601 */         break;
/*     */       }
/* 603 */       class5.anInt67 = (class5.anInt67 << 8 | class5.aByteArray54[class5.anInt55] & 0xFF);
/* 604 */       class5.anInt68 += 8;
/* 605 */       class5.anInt55 += 1;
/* 606 */       class5.anInt56 -= 1;
/* 607 */       class5.anInt57 += 1;
/* 608 */       if (class5.anInt57 == 0)
/* 609 */         class5.anInt58 += 1;
/*     */     }
/* 611 */     return j;
/*     */   }
/*     */ }

/* Location:           C:\Users\HaiderPC\Desktop\Cache Extractor\data\CacheExport2.jar
 * Qualified Name:     cacheexport2.PackJAG
 * JD-Core Version:    0.6.2
 */