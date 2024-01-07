package com.hj.leetcode.kotlin.problem446

/**
 * LeetCode page: [446. Arithmetic Slices II - Subsequence](https://leetcode.com/problems/arithmetic-slices-ii-subsequence/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N^2) and Space O(N^2) where N is the size of nums;
     */
    fun numberOfArithmeticSlices(nums: IntArray): Int {
        /* dp[i][d]::= the number of arithmetic subsequences that start
         * with nums[i], have a step of d, and a length of at least 2;
         */
        val dp = hashMapOf<Int, MutableMap<Long, Int>>()
        var result = 0
        for (i in nums.indices.reversed()) {
            for (j in i + 1..< nums.size) {
                val d = nums[i].toLong() - nums[j]
                val count1 = dp[i]?.get(d) ?: 0
                val count2 = dp[j]?.get(d) ?: 0
                result += count2
                dp.computeIfAbsent(i) { hashMapOf() }[d] =
                    1 + count1 + count2
            }
        }
        return result
    }
}