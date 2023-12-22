package com.hj.leetcode.kotlin.problem1318

/**
 * LeetCode page: [1318. Minimum Flips to Make a OR b Equal to c](https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N is the max of a, b and c;
     */
    fun minFlips(a: Int, b: Int, c: Int): Int {
        // flipQueryTable[bit_a][bit_b][bit_c] ::= minimum required flips for that bit
        val flipQueryTable = Array(2) { Array(2) { IntArray(2) } }.apply {
            this[0][0][1] = 1
            this[0][1][0] = 1
            this[1][0][0] = 1
            this[1][1][0] = 2
        }

        var aWorking = a
        var bWorking = b
        var cWorking = c
        var maxWorking = maxOf(a, b, c)
        var result = 0

        while (maxWorking != 0) {
            val aBit = aWorking and 1
            val bBit = bWorking and 1
            val cBit = cWorking and 1
            result += flipQueryTable[aBit][bBit][cBit]

            aWorking = aWorking shr 1
            bWorking = bWorking shr 1
            cWorking = cWorking shr 1
            maxWorking = maxWorking shr 1
        }
        return result
    }
}