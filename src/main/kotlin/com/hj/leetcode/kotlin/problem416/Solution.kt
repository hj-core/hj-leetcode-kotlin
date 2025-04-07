package com.hj.leetcode.kotlin.problem416

/**
 * LeetCode page: [416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN+NM) and Space O(N+M) where N and M are the length and the sum of nums, respectively.
    fun canPartition(nums: IntArray): Boolean {
        val total = nums.sum()
        if (total and 1 != 0) {
            return false
        }

        val targetSum = total / 2
        val sortedNums = nums.clone().apply { sort() }

        // dp[sum]@i::= whether there is a subset of sortedNums[0..=i] that sums to sum
        val dp = BooleanArray(targetSum + 1)

        dp[0] = true
        var prefixSum = 0
        for (num in sortedNums) {
            for (sum in minOf(prefixSum, (targetSum - num)) downTo 0) {
                if (dp[sum]) {
                    dp[sum + num] = true
                }
            }

            prefixSum += num
            if (dp[targetSum]) {
                return true
            }
        }
        return false
    }
}
