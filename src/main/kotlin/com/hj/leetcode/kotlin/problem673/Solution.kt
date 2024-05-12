package com.hj.leetcode.kotlin.problem673

/**
 * LeetCode page: [673. Number of Longest Increasing Subsequence](https://leetcode.com/problems/number-of-longest-increasing-subsequence/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the size of nums;
     */
    fun findNumberOfLIS(nums: IntArray): Int {
        /* dp[i]::= the (length, count) pair of the longest increasing
         * subsequences which start at index i;
         */
        val dp = Array(nums.size) { Pair(1, 1) }

        for (start in nums.indices.reversed()) {
            var maxLength = 1
            var countMaxLength = 1

            for (next in start + 1..<nums.size) {
                if (nums[next] <= nums[start]) {
                    continue
                }
                val length = 1 + dp[next].first
                when {
                    length == maxLength -> countMaxLength += dp[next].second
                    length > maxLength -> {
                        maxLength = length
                        countMaxLength = dp[next].second
                    }
                }
            }
            dp[start] = Pair(maxLength, countMaxLength)
        }

        val maxLength = dp.maxOf { (length, _) -> length }
        return dp.sumOf { (length, count) -> if (length == maxLength) count else 0 }
    }
}