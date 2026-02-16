package com.hj.leetcode.kotlin.problem190

/**
 * LeetCode page: [190. Reverse Bits](https://leetcode.com/problems/reverse-bits/);
 */
class Solution {
    // Complexity:
    // Time O(Log n) and Space O(1).
    fun reverseBits(n: Int): Int {
        val m0 = 0x5555_5555 // 0101_0101_0101_0101_...
        val m1 = 0x3333_3333 // 0011_0011_0011_0011_...
        val m2 = 0x0f0f_0f0f // 0000_1111_0000_1111_...
        val m3 = 0x00ff_00ff // ...

        // Swap adjacent 0s and 1s
        var x = n
        x = (x ushr 1 and m0) or (x and m0 shl 1)
        x = (x ushr 2 and m1) or (x and m1 shl 2)
        x = (x ushr 4 and m2) or (x and m2 shl 4)
        x = (x ushr 8 and m3) or (x and m3 shl 8)
        return (x ushr 16) or (x shl 16)
    }
}
