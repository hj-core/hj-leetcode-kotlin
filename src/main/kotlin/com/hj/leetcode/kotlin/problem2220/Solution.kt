package com.hj.leetcode.kotlin.problem2220

/**
 * LeetCode page: [2220. Minimum Bit Flips to Convert Number](https://leetcode.com/problems/minimum-bit-flips-to-convert-number/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N is the max between start and goal;
     */
    fun minBitFlips(
        start: Int,
        goal: Int,
    ): Int = (start xor goal).countOneBits()
}
