package com.hj.leetcode.kotlin.problem2220

/**
 * LeetCode page: [2220. Minimum Bit Flips to Convert Number](https://leetcode.com/problems/minimum-bit-flips-to-convert-number/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N is the max between start and goal;
     */
    fun minBitFlips(start: Int, goal: Int): Int {
        val sumOfDiffBits = start xor goal
        return sumOfDiffBits.countOneBits()
    }

    private fun Int.countOneBits(): Int {
        var count = 0
        var currValue = this
        while (currValue != 0) {
            currValue = currValue.subtractLastBitOne()
            count++
        }
        return count
    }

    private fun Int.subtractLastBitOne(): Int {
        require(this >= 0)
        return if (this == 0) 0 else this and (this - 1)
    }
}