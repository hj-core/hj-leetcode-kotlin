package com.hj.leetcode.kotlin.problem2929

/**
 * LeetCode page: [2929. Distribute Candies Among Children II](https://leetcode.com/problems/distribute-candies-among-children-ii/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun distributeCandies(
        n: Int,
        limit: Int,
    ): Long {
        val minSumAB = maxOf(0, n - limit)
        val maxSumAB = minOf(n, limit * 2)
        if (minSumAB > maxSumAB) {
            return 0L
        }
        return distributeAB(maxSumAB, limit) - distributeAB(minSumAB - 1, limit)
    }

    // `distributeAB` returns the number of different (a, b) pairs so that both
    // a and b are non-negative, and their sum is not greater than maxSum.
    private fun distributeAB(
        maxSum: Int,
        limit: Int,
    ): Long =
        if (maxSum <= limit) {
            maxOf(0L, (1L + maxSum) * (2L + maxSum) / 2)
        } else {
            (1L + limit) * (1L + limit) - distributeAB(limit * 2 - maxSum - 1, limit)
        }
}
