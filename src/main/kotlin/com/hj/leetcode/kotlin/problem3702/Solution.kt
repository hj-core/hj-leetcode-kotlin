package com.hj.leetcode.kotlin.problem3702

/**
 * LeetCode page: [3702. Longest Subsequence With Non-Zero Bitwise XOR](https://leetcode.com/problems/longest-subsequence-with-non-zero-bitwise-xor/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun longestSubsequence(nums: IntArray): Int {
        var xorNums = 0
        var allZero = true
        for (num in nums) {
            xorNums = xorNums xor num
            allZero = allZero && num == 0
        }

        return when {
            xorNums != 0 -> nums.size
            allZero -> 0
            else -> nums.size - 1
        }
    }
}
