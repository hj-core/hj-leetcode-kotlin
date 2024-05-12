package com.hj.leetcode.kotlin.problem201

/**
 * LeetCode page: [201. Bitwise AND of Numbers Range](https://leetcode.com/problems/bitwise-and-of-numbers-range/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun rangeBitwiseAnd(left: Int, right: Int): Int {
        return longestCommonPrefixBits(left, right)
    }

    private fun longestCommonPrefixBits(a: Int, b: Int): Int {
        require(a >= 0 && b >= 0)
        var result = 0
        var probe = 1 shl 30
        while (probe > 0) {
            val aBit = a and probe
            val bBit = b and probe

            if (aBit != bBit) {
                break
            }
            result += aBit
            probe = probe shr 1
        }
        return result
    }
}