/* 
 * CS:APP Data Lab 
 * 
 * <Please put your name and userid here>
 * 
 * bits.c - Source file with your solutions to the Lab.
 *          This is the file you will hand in to your instructor.
 *
 * WARNING: Do not include the <stdio.h> header; it confuses the dlc
 * compiler. You can still use printf for debugging without including
 * <stdio.h>, although you might get a compiler warning. In general,
 * it's not good practice to ignore compiler warnings, but in this
 * case it's OK.  
 */

#if 0
/*
 * Instructions to Students:
 *
 * STEP 1: Read the following instructions carefully.
 */

You will provide your solution to the Data Lab by
editing the collection of functions in this source file.

INTEGER CODING RULES:
 
  Replace the "return" statement in each function with one
  or more lines of C code that implements the function. Your code 
  must conform to the following style:
 
  int Funct(arg1, arg2, ...) {
      /* brief description of how your implementation works */
      int var1 = Expr1;
      ...
      int varM = ExprM;

      varJ = ExprJ;
      ...
      varN = ExprN;
      return ExprR;
  }

  Each "Expr" is an expression using ONLY the following:
  1. Integer constants 0 through 255 (0xFF), inclusive. You are
      not allowed to use big constants such as 0xffffffff.
  2. Function arguments and local variables (no global variables).
  3. Unary integer operations ! ~
  4. Binary integer operations & ^ | + << >>
    
  Some of the problems restrict the set of allowed operators even further.
  Each "Expr" may consist of multiple operators. You are not restricted to
  one operator per line.

  You are expressly forbidden to:
  1. Use any control constructs such as if, do, while, for, switch, etc.
  2. Define or use any macros.
  3. Define any additional functions in this file.
  4. Call any functions.
  5. Use any other operations, such as &&, ||, -, or ?:
  6. Use any form of casting.
  7. Use any data type other than int.  This implies that you
     cannot use arrays, structs, or unions.

 
  You may assume that your machine:
  1. Uses 2s complement, 32-bit representations of integers.
  2. Performs right shifts arithmetically.
  3. Has unpredictable behavior when shifting an integer by more
     than the word size.

EXAMPLES OF ACCEPTABLE CODING STYLE:
  /*
   * pow2plus1 - returns 2^x + 1, where 0 <= x <= 31
   */
  int pow2plus1(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     return (1 << x) + 1;
  }

  /*
   * pow2plus4 - returns 2^x + 4, where 0 <= x <= 31
   */
  int pow2plus4(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     int result = (1 << x);
     result += 4;
     return result;
  }

FLOATING POINT CODING RULES

For the problems that require you to implent floating-point operations,
the coding rules are less strict.  You are allowed to use looping and
conditional control.  You are allowed to use both ints and unsigneds.
You can use arbitrary integer and unsigned constants.

You are expressly forbidden to:
  1. Define or use any macros.
  2. Define any additional functions in this file.
  3. Call any functions.
  4. Use any form of casting.
  5. Use any data type other than int or unsigned.  This means that you
     cannot use arrays, structs, or unions.
  6. Use any floating point data types, operations, or constants.


NOTES:
  1. Use the dlc (data lab checker) compiler (described in the handout) to 
     check the legality of your solutions.
  2. Each function has a maximum number of operators (! ~ & ^ | + << >>)
     that you are allowed to use for your implementation of the function. 
     The max operator count is checked by dlc. Note that '=' is not 
     counted; you may use as many of these as you want without penalty.
  3. Use the btest test harness to check your functions for correctness.
  4. Use the BDD checker to formally verify your functions
  5. The maximum number of ops for each function is given in the
     header comment for each function. If there are any inconsistencies 
     between the maximum ops in the writeup and in this file, consider
     this file the authoritative source.

/*
 * STEP 2: Modify the following functions according the coding rules.
 * 
 *   IMPORTANT. TO AVOID GRADING SURPRISES:
 *   1. Use the dlc compiler to check that your solutions conform
 *      to the coding rules.
 *   2. Use the BDD checker to formally verify that your solutions produce 
 *      the correct answers.
 */


#endif
/* Copyright (C) 1991-2016 Free Software Foundation, Inc.
   This file is part of the GNU C Library.

   The GNU C Library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.

   The GNU C Library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.

   You should have received a copy of the GNU Lesser General Public
   License along with the GNU C Library; if not, see
   <http://www.gnu.org/licenses/>.  */
/* This header is separate from features.h so that the compiler can
   include it implicitly at the start of every compilation.  It must
   not itself include <features.h> or any other header that includes
   <features.h> because the implicit include comes before any feature
   test macros that may be defined in a source file before it first
   explicitly includes a system header.  GCC knows the name of this
   header in order to preinclude it.  */
/* glibc's intent is to support the IEC 559 math functionality, real
   and complex.  If the GCC (4.9 and later) predefined macros
   specifying compiler intent are available, use them to determine
   whether the overall intent is to support these features; otherwise,
   presume an older compiler has intent to support these features and
   define these macros by default.  */
/* wchar_t uses Unicode 8.0.0.  Version 8.0 of the Unicode Standard is
   synchronized with ISO/IEC 10646:2014, plus Amendment 1 (published
   2015-05-15).  */
/* We do not support C11 <threads.h>.  */
/* 
 *
 *
 * 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 * int test_dl10(int parameter1, int parameter2)
 * {
 *   int result = 0;
 *   int i;
 *   for (i = parameter2; i <= parameter1; i++)
 *      result |= 1 << i;
 *   return result;
 *  } 
 *
 *   Assume 0 <= parameter2 <= 31, and 0 <= parameter1 <= 31
 *   If parameter2 > parameter1, then return 0
 *   Legal ops: ~ & + << 
 *   Max ops: 12
 *   Rating: 3
 */
int dl10(int highbit, int lowbit) {
         int lmask = ~0<<lowbit;
         int hmask = ~0<<highbit;
         hmask = ~(hmask<<1);
         return hmask&lmask;
         //return bits number of bits in between 2 and 1;
  return 2;
}
/* 
 *
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl11(int x, int y)
 *   {
 *       return ~(x|y);
 *   }
 *
 *
 *   Legal ops: ~ &
 *   Max ops: 5
 *   Rating: 1
 */
int dl11(int x, int y) {
    return (~x & ~y);
  return 2;//related to other problem that involves & using |
}
/* 
 * int test_dl12(int x, int y)
 * {
 *    return x|y;
 * }
 *
 *
 *   Legal ops: ~ &
 *   Max ops: 8
 *   Rating: 1
 */
int dl12(int x, int y) {
    return ~(~x & ~y);
  return 2;// same as 11
}
/*
 *
 *  int test_dl13(int x) {
 *  int result = 0;
 *  int i;
 *  for (i = 0; i < 32; i++)
 *  result ^=  (x >> i) & 0x1;
 *  return result; }
 *
 *   Legal ops: ! & ^  >>
 *   Max ops: 20
 *   Rating: 4
 */
int dl13(int x) {
    x = x ^(x>>16);
    x = x ^(x>>8);
    x = x ^(x>>4);
    x = x ^(x>>2);
    x = x ^(x>>1);
    return x & 0x01;
  return 2; //get parody
}
/* 
 *
 * 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *
 * int test_dl14(int x, int y)
 * {
 *   return x^y; }
 *
 *
 *
 *   Legal ops: ~ &
 *   Max ops: 14
 *   Rating: 1
 */
int dl14(int x, int y) {
    return ~(~(x&~y) & ~(~x & y));
  return 2;
}
/* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 * 
 * asumming a little endiamachine 
 * least significant byte stored first 
 *
 * int test_dl15(int x, int n, int m)
 * {
 *
 *   unsigned int nmask, mmask;
 *
 *   switch(n) {
 *   case 0:
 *     nmask = x & 0xFF;
 *     x &= 0xFFFFFF00;
 *     break;
 *   case 1:
 *     nmask = (x & 0xFF00) >> 8;
 *     x &= 0xFFFF00FF;
 *     break;
 *   case 2:
 *     nmask = (x & 0xFF0000) >> 16;
 *     x &= 0xFF00FFFF;      
 *     break;
 *   default:
 *     nmask = ((unsigned int)(x & 0xFF000000)) >> 24;
 *     x &= 0x00FFFFFF;
 *     break;
 *    }
 *
 *   switch(m) {
 *   case 0:
 *     mmask = x & 0xFF;
 *     x &= 0xFFFFFF00;
 *     break;
 *   case 1:
 *     mmask = (x & 0xFF00) >> 8;
 *     x &= 0xFFFF00FF;
 *     break;
 *   case 2:
 *     mmask = (x & 0xFF0000) >> 16;
 *     x &= 0xFF00FFFF;      
 *     break;
 *   default:
 *     mmask = ((unsigned int)(x & 0xFF000000)) >> 24;
 *     x &= 0x00FFFFFF;
 *     break;
 *   }
 *
 *   nmask <<= 8*m;
 *   mmask <<= 8*n;
 *
 *   return x | nmask | mmask;
 * }
 *
 *
 *  You may assume that 0 <= n <= 3, 0 <= m <= 3
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 25
 *  Rating: 2
 */
int dl15(int x, int n, int m) {
    unsigned int nmask,mmask; 
    nmask = (x & (0xFF<<((((!n^0) + ~0)  & (2<<n)))));
    printf("nmask test = %i\n",((!n^0) + ~0) & (2<<n));
    mmask = (x & (0xFF<<((((!m^0) + ~0)  & (2<<m)))));
    printf("mmask test = %i\n",((!m^0) + ~0) & (2<<m));
    printf("Testing %i\tN = %i and M = %i\tmask = %u and mmask = %u \n",x,n,m,nmask,mmask);
    x = x ^ nmask;
    x = x ^ mmask;
    nmask = nmask;
    return x | nmask | mmask;
    
    /*
    switch(n) {
 *   case 0:
 *     nmask = x & 0xFF;
 *     x &= 0xFFFFFF00;
 *     break;
 *   case 1:
 *     nmask = (x & 0xFF00) >> 8;
 *     x &= 0xFFFF00FF;
 *     break;
 *   case 2:
 *     nmask = (x & 0xFF0000) >> 16;
 *     x &= 0xFF00FFFF;      
 *     break;
 *   default:
 *     nmask = ((unsigned int)(x & 0xFF000000)) >> 24;
 *     x &= 0x00FFFFFF;
 *     break;
 *    }
     */
    return 2;
}
/* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *
 *
 *
 * int test_dl16(int x, int y, int z)
 * {
 *   return x?y:z;
 *  }
 *
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 16
 *   Rating: 3
 */
int dl16(int x, int y, int z) {
    int temp = !!x;
    int var = (~(temp & 1) + 1);
    return ((var & y) + ((~var) & z));
  return 2;
}
/* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *
 *
 *  int test_dl17(int x)
 *  {
 *   return (x & 0x1) ? -1 : 0;
 *  }
 *
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 5
 *   Rating: 2
 */
int dl17(int x) {
    return (x<<31)>>31;
  //return 2;
}
/* 
 *
 * 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *
 *
 * int test_dl18(int x, int n)
 * {
 *    int p2n = 1<<n;
 *    return x/p2n;
 *		
 * }
 *
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 15
 *   Rating: 2
 */
int dl18(int x, int n) {
    int bias = (x>>31) & ((1 << n) + ~0);
    return (x+bias) >> n;
    return 2;
}
/*   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *
 *
 *
 *
 *  int test_dl19(void) {
 *  int result = 0;
 *  int i;
 *  for (i = 0; i < 32; i+=2)
 *    result |= 1<<i;
 *  return result; }
 *
 *   Legal ops: |  << 
 *   Max ops: 6
 *   Rating: 1
 */
int dl19(void) {
    return  (0x55 << 24) | (0x55 << 16) | (0x55<<8)| 0x55; 
  return 2;
}
/* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int dl1(int x) {return (x < 0) ? -x : x; }
 *
 *   Example: dl1(-1) = 1.
 *   You may assume -TMax <= x <= TMax
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 10
 *   Rating: 4
 */
int dl1(int x) {
    return (((x >>31) ^ x) + (1 + ~(x>>31))); // make the sign bit always = 0
  return 2;
}
/* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *
 *   int test_dl20(int x)
 *   {
 *      return (x*3)/4;
 *   }
 *
 *   Legal ops: ! ~ & + << >>
 *   Max ops: 10
 *   Rating: 3
 */
int dl20(int x) {
    int temp = x + x + x;
    int bias = 3 & (temp>>31);
    return (temp + bias)>>2;
    //return b & ~(0x3<<30);
    
  return 2;
}
/* 
 * Reproduce the functionality of the following C function
 * unsigned test_dl21(unsigned uf) {
 * float f = u2f(uf);
 * float hf = 0.5*f;
 * if (isnan(f))
 *   return uf;
 * else
 *   return f2u(hf);
 * }
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned dl21(unsigned uf) {
    unsigned e = uf & (0xFF<<23); 
    unsigned s = uf & (0x1<<31);
    unsigned m = uf & ~(~0<<23); 
    if (e == (0xFF <<23)) 
          return uf; // NaN or Inifity
    if ((!e) || (e == (0x1<<23)))  {
       m = m | e;
       m = (uf & (~(~0<<24)))>>1;
       m += (((uf & 3) >> 1) & (uf & 1));
       return s | m;
       }
    return s | ((e-1) & (0xFF<<23)) | m;

    return 2;
}
/* 
 * reproduce the functionality of the following C function
 * unsigned test_dl22(int x) {
 * float f = (float) x;
 * return f2u(f);
 * }
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned dl22(int x) {
    //returns float version of x
    unsigned e = x & (0xFF<<23); 
    unsigned s = x & (0x1<<31);
    unsigned m = x & ~(~0<<23);
    printf("test1 %u , %u , %u\n",s,e,m);
    if (e == (0xFF <<23)) 
          return x; // NaN or Inifity
    if ((!e) || (e == (0x1<<23)))  {
       printf("test2 %u , %u , %u\n",s,e,m);
       m = m | e;
       m = (x & (~(~0<<24)))>>1;
       m += (((x & 3) >> 1) & (x & 1));
       printf("test3 %u , %u , %u\n",s,e,m);
       return s | m;
       }
    printf("test4 %u , %u , %u\n\n",s,e,m);
    return s | ((e-1) & (0xFF<<23)) | m;
  return 2;
}
/* 
 * reproduce the functionality of the following C function
 * unsigned test_dl23(unsigned uf) {
 *  float f = u2f(uf);
 *  float tf = 2*f;
 *  if (isnan(f))
 *    return uf;
 *  else
 *    return f2u(tf);
 * }
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned dl23(unsigned uf) {
    unsigned e = uf & (0xFF<<23);
    unsigned m = uf & ~(~0<<23);	
    if (uf == (0xFF <<23)) 
	return uf;
    else 
        if (!e)
            return (uf & (~0<<23)) + m + m;
	else 
            if (e ^ (0xFF<<23))
		return (e + (0x1<<23)) + (uf & ~(0xFF<<23));
            else
        	return uf; 

    
  return 2;
}
/*dl24 - return the minimum number of bits required to represent x in
 *             two's complement
 *  Examples:dl24(12) = 5
 *           dl24(298) = 10
 *           dl24(-5) = 4
 *           dl24(0)  = 1
 *           dl24(-1) = 1
 *           dl24(0x80000000) = 32
 * Here is a C function that accomplishes this. Reproduce the functionality
 * of this function using only the legal operations described below. 
 * int test_dl24(int x) {
 *   unsigned int a, cnt;
 *   
 *   x = x<0 ? -x-1 : x;
 *   a = (unsigned int)x;
 *   for (cnt=0; a; a>>=1, cnt++)
 *       ;
 *   return (int)(cnt + 1);
 * }
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 90
 *  Rating: 4
 */
int dl24(int x) {
    int y, result, mask16, mask8, mask4, mask2, mask1, bitnum;
 /*
 * The idea here is to apply binary search in order to get log number of operations
 */
  mask1 = 0x2;  		// 0x1 << 1
  mask2 = 0xC;  		// 0x3 << 2
  mask4 = 0xF0;			// 0x000000F0
  mask8 = 0xFF<<8;		// 0x0000FF00
  mask16 = (mask8 | 0xFF) << 16;// 0xFFFF0000
  
  result = 1;
  y = x^(x>>31); //cast the number to positive with the same howManyBits result

  // Check first 16 bits, if they have at least one bit - result > 16
  bitnum = (!!(y & mask16)) << 4; // 16 OR zero
  result += bitnum; 
  y = y >> bitnum;
  
  bitnum = (!!(y & mask8)) << 3;  // 8 OR zero
  result += bitnum;
  y = y >> bitnum;
  
  bitnum = (!!(y & mask4)) << 2;  // 4 OR zero
  result += bitnum;
  y = y >> bitnum;

  bitnum = (!!(y & mask2)) << 1;  // 2 OR zero
  result += bitnum;  
  y = y >> bitnum;

  bitnum = !!(y & mask1);  // 1 OR zero
  result += bitnum;  
  y = y >> bitnum;

return result + (y&1);
  return 0;
}
/* 
 *
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl2(int x, int y)
 *   { long long lsum = (long long) x + y;
 *    return lsum == (int) lsum;}
 *
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 3
 */
int dl2(int x, int y) {
  int z=x+y;
  int a=x>>31;
  int b=y>>31;
  int c=z>>31;
  return !!(a^b)|(!(a^c)&!(b^c));
  return 2;
}
/* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl3(int x) {
 *    int i;
 *    for (i = 0; i < 32; i+=2)
 *       if ((x & (1<<i)) == 0)
 *	  return 0;
 *    return 1; }
 *
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 2
 */
int dl3(int x) {
    //return (x&0x4);//uses 7 ops
    int allEven = (0x55<<24)|(0x55<<16)|(0x55<<8)|0x55;
    return !(((x & allEven) | ~allEven) + 1);
  return 2;
}
/* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl4(int x) {
 *   int i;
 *   for (i = 1; i < 32; i+=2)
 *     if ((x & (1<<i)) == 0)
 *	  return 0;
 *    return 1; }
 *
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 2
 */
int dl4(int x) {
    int allOdds = (0xAA<<24)|(0xAA<<16)|(0xAA<<8)|0xAA;
    return !(((x & allOdds) | ~allOdds) + 1);
  return 2; //x needs to equal ...1010101010(32 bits) to return 1
}
/* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl5(int x) {
 *   int i;
 *   for (i = 0; i < 32; i+=2)
 *     if (x & (1<<i))
 *	  return 1;
 *   return 0; }
 *
 *   Legal ops: ! ~ &  | + << >>
 *   Max ops: 10
 *   Rating: 2
 */
int dl5(int x) {
    return !!((x & 0x55) | (x & (0x55<<8)) | (x & (0x55<<16)) | (x & (0x55<<24)));
  return 2; // return x & ...10101(32bits)
}
/* 
 * 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *  int test_dl6(int x) {
 *   int i;
 *   for (i = 1; i < 32; i+=2)
 *       if (x & (1<<i))
 *	     return 1;
 *   return 0; }
 *
 *   Legal ops: ! &  | << 
 *   Max ops: 10
 *   Rating: 2
 */
int dl6(int x) {
    return !!((x & 0xAA) | (x & (0xAA<<8)) | (x & (0xAA<<16)) | (x & (0xAA<<24)));
    return 2;// return x & ...0101010(32bits)
}
/* 
* 
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl7(int x)
 *   {
 *      return !x;
 *   }
 *
 *   Legal ops: ~ & ^ | + >>
 *   Max ops: 10
 *   Rating: 4 
 */
int dl7(int x) {
    return((((x>>31) & 0x01) | ((~x + 1 >> 31) & 0x01)) ^ 0x01);
  return 2;//return x XOR 111..1 (max int)
}
/* 
 *
 *  
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl8(int x, int y)
 *   {
 *      return x&y;
 *  
 *  }
 *
 *   Legal ops: ~ |
 *   Max ops: 6
 *   Rating: 1
 */
int dl8(int x, int y) {
  return ~(~x | ~y);  
  return 2;
}
/*
 *   
 *   reproduce the functionality of the following C function
 *   with the given restrictions
 *
 *   int test_dl9(int x) {
 *   int result = 0;
 *   int i;
 *   for (i = 0; i < 32; i++)
 *   result +=  (x >> i) & 0x1;
 *       return result;
 *   }
 *
 *   Legal ops: & | + << >>
 *   Max ops: 38
 *   Rating: 4
 */
int dl9(int x) {
    int alt1 = (0x55) | (0x55<<8) | (0x55<<16) | (0x55<<24);
    int alt2 = (0x33) | (0x33<<8) | (0x33<<16) | (0x33<<24);
    int alt3 = (0x0f) | (0x0f<<8) | (0x0f<<16) | (0x0f<<24);
    int alt4 = (0xff) | (0xff<<16);
    x = (x>>1 & alt1) + (x & alt1);
    x = (x>>2 & alt2) + (x & alt2);
    x = (x>>4 & alt3) + (x & alt3);
    x = (x>>8 & alt4) + (x & alt4);
    return (x>>16) + (x & (0xff | (0xff<<8)));
    
  return 2;//counts the bits in x
}
