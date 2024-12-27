package com.hj.leetcode.kotlin.problem494

import kotlin.math.abs

/**
 * LeetCode page: [494. Target Sum](https://leetcode.com/problems/target-sum/);
 */
class Solution2 {
    /* Complexity:
     * Time O(NK) and Space O(K)
     * where N is the size of nums and K is the difference between sum(nums) and abs(target).
     */
    fun findTargetSumWays(
        nums: IntArray,
        target: Int,
    ): Int {
        // Interpret the problem in another way:
        // Case target >= 0:
        //   If all numbers are assigned with a plus sign, and we can alter some to minus signs,
        //   how many ways can we make the difference between target and sum(nums) zero?
        // Case target < 0 is similar.
        val sum = nums.sum()
        if (target !in -sum..sum) {
            return 0
        }
        val targetDiff = sum - abs(target)
        if (targetDiff % 2 != 0) {
            // Changing signs causes an even amount of total difference
            return 0
        }
        val halfDiff = targetDiff / 2

        // dp_i[j]::=
        // Number of ways to reduce 2 * j by altering some of the signs in range 0..=i.
        val dp = IntArray(1 + halfDiff)
        dp[0] = 1 // dp_(i=-1)
        for (num in nums) {
            for (j in dp.lastIndex - num downTo 0) {
                dp[j + num] += dp[j]
            }
        }
        return dp[halfDiff]
    }
}
