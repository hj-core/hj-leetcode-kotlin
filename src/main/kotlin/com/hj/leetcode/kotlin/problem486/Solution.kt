package com.hj.leetcode.kotlin.problem486

/**
 * LeetCode page: [486. Predict the Winner](https://leetcode.com/problems/predict-the-winner/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of nums.
    fun predictTheWinner(nums: IntArray): Boolean {
        val n = nums.size
        if (n % 2 == 0) {
            return true // player 1 can pick all odd indices or all even indices
        }

        // dp[i]@len:= the maximum score lead of first player in a game of nums[i..<i+len]
        val dp = nums.clone() // base case: len = 1
        for (len in 2..n) {
            for (i in 0..(n - len)) {
                dp[i] = maxOf(nums[i] - dp[i + 1], nums[i + len - 1] - dp[i])
            }
        }

        return dp[0] >= 0
    }
}
