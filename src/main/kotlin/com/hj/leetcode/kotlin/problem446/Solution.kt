package com.hj.leetcode.kotlin.problem446

/**
 * LeetCode page: [446. Arithmetic Slices II - Subsequence](https://leetcode.com/problems/arithmetic-slices-ii-subsequence/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N^2) where N is the size of nums;
     */
    fun numberOfArithmeticSlices(nums: IntArray): Int {
        /* dp[i][d]::= the number of (arithmetic) subsequences that start
         * with nums[i], have a step of d, and a length of at least 2;
         */
        val dp = hashMapOf<Int, MutableMap<Long, Int>>()
        var result = 0
        for (i in nums.indices.reversed()) {
            for (j in i + 1..<nums.size) {
                val d = nums[i].toLong() - nums[j]
                val currentCount = dp
                    .computeIfAbsent(i) { hashMapOf() }
                    .computeIfAbsent(d) { 0 }

                val addedPair = 1
                val addedAS = dp[j]?.get(d) ?: 0
                result += addedAS
                checkNotNull(dp[i])[d] = currentCount + addedPair + addedAS
            }
        }
        return result
    }
}