package com.hj.leetcode.kotlin.problem191

/**
 * LeetCode page: [191. Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/);
 */
class Solution2 {
    // you need treat n as an unsigned value

    /* Complexity:
     * Time O(NLogN) and Space O(1) where N is the number of bits in n's
     * binary representation;
     */
    fun hammingWeight(n: Int): Int {
        /* Hamming weight
         * https://en.wikipedia.org/wiki/Hamming_weight#Efficient_implementation
         */
        val m1 = 0x55555555 //binary: 0101...
        val m2 = 0x33333333 //binary: 00110011..
        val m4 = 0x0f0f0f0f //binary: 4 zeros,  4 ones ...

        var num = n
        num -= (num ushr 1) and m1
        num = (num and m2) + ((num ushr 2) and m2)
        num = (num + (num ushr 4)) and m4
        num += (num ushr 8)
        num += (num ushr 16)
        return num and 0x3f
    }
}