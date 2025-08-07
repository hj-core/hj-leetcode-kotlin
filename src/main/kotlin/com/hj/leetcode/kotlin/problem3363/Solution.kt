package com.hj.leetcode.kotlin.problem3363

/**
 * LeetCode page: [3363. Find the Maximum Number of Fruits Collected](https://leetcode.com/problems/find-the-maximum-number-of-fruits-collected/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the height and
    // width of fruits.
    fun maxCollectedFruits(fruits: Array<IntArray>): Int = solveChild1(fruits) + solveChild2(fruits) + solveChild3(fruits)

    // Child 1 starts from (0, 0). He can only move along the
    // diagonal.
    private fun solveChild1(fruits: Array<IntArray>): Int = fruits.indices.sumOf { fruits[it][it] }

    // Child 2 starts from (0, n-1). He can move along or above
    // the diagonal. Since Child 1 has already collected all the
    // fruits in the diagonal, we only consider Child 2 moving
    // above the diagonal, excluding the goal cell as well.
    private fun solveChild2(fruits: Array<IntArray>): Int {
        val n = fruits.size
        // dp[j]@r:= the maximum fruits child 2 can collect
        // when he reach cell(r, n-1-j).
        val dp = IntArray(n / 2 + 1)

        dp[0] = fruits[0][n - 1]
        for (r in 1..<(n - 1)) {
            val maxJ = minOf(r, n - 2 - r)
            var tmp = dp[0]
            for (j in 0..maxJ) {
                dp[j] = fruits[r][n - 1 - j] + maxOf(tmp, dp[j], dp[j + 1]).also { tmp = dp[j] }
            }
        }
        return dp[0]
    }

    // Child 3 starts from (n-1, 0). He can move along or below
    // the diagonal. Since Child 1 has already collected all the
    // fruits in the diagonal, we only consider Child 3 moving
    // below the diagonal, excluding the goal cell as well.
    private fun solveChild3(fruits: Array<IntArray>): Int {
        val n = fruits.size
        // dp[j]@c:= the maximum fruits child 3 can collect
        // when he reach cell(n-1-j, c).
        val dp = IntArray(n / 2 + 1)

        dp[0] = fruits[n - 1][0]
        for (c in 1..<(n - 1)) {
            val maxJ = minOf(c, n - 2 - c)
            var tmp = dp[0]
            for (j in 0..maxJ) {
                dp[j] = fruits[n - 1 - j][c] + maxOf(tmp, dp[j], dp[j + 1]).also { tmp = dp[j] }
            }
        }
        return dp[0]
    }
}
