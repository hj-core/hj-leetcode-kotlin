package com.hj.leetcode.kotlin.problem2770

import kotlin.math.abs

/**
 * LeetCode page: [2770. Maximum Number of Jumps to Reach the Last Index](https://leetcode.com/problems/maximum-number-of-jumps-to-reach-the-last-index/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of nums.
    fun maximumJumps(
        nums: IntArray,
        target: Int,
    ): Int {
        // dp[i]:= the maximum jumps starting from index i
        val dp = IntArray(nums.size) { -1 }
        dp[nums.lastIndex] = 0
        for (i in nums.size - 2 downTo 0) {
            for (j in i + 1..<nums.size) {
                if (abs(nums[i] - nums[j]) <= target && 0 <= dp[j]) {
                    dp[i] = maxOf(dp[i], 1 + dp[j])
                }
            }
        }

        return dp[0]
    }
}
