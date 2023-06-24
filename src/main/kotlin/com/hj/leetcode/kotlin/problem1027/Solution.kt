package com.hj.leetcode.kotlin.problem1027

/**
 * LeetCode page: [1027. Longest Arithmetic Subsequence](https://leetcode.com/problems/longest-arithmetic-subsequence/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N^2) where N is the size of nums;
     */
    fun longestArithSeqLength(nums: IntArray): Int {
        /* dp[endValue][commonDifference] ::= the longest length of arithmetic subsequence
         * having endValue and commonDifference;
         */
        val dp = mutableMapOf<Int, MutableMap<Int, Int>>()
        var result = 0

        for (endValue in nums) {
            val subsequences = hashMapOf<Int, Int>() // entry = (commonDifference, length)

            for (existingEndValue in dp.keys) {
                val commonDifference = endValue - existingEndValue
                val length = 1 + (dp[existingEndValue]?.get(commonDifference) ?: 1)
                subsequences[commonDifference] = length
                result = maxOf(result, length)
            }
            dp[endValue] = subsequences
        }
        return result
    }
}