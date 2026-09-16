package com.hj.leetcode.kotlin.problem1621

/**
 * LeetCode page: [1621. Number of Sets of K Non-Overlapping Line Segments](https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/);
 */
class Solution {
    // Complexity;
    // Time O(kn) and Space O(n).
    fun numberOfSets(
        n: Int,
        k: Int,
    ): Int {
        val modulo = 1_000_000_007
        // dp[i]@k:= numberOfSets(i, k)
        val dp = IntArray(n) { 1 } // base case k = 0
        for (i in 0..<k) {
            var prefixSum = dp[i]
            dp[i] = 0
            for (j in (i + 1)..<n) {
                val tmp = dp[j]
                dp[j] = (dp[j - 1] + prefixSum) % modulo
                prefixSum = (prefixSum + tmp) % modulo
            }
        }
        return dp[n - 1]
    }
}
