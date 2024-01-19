package com.hj.leetcode.kotlin.problem931

/**
 * LeetCode page: [931. Minimum Falling Path Sum](https://leetcode.com/problems/minimum-falling-path-sum/description/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(N) where M and N are the number of rows and columns of matrix;
     */
    fun minFallingPathSum(matrix: Array<IntArray>): Int {
        // dp[c]@r::= the minimum falling path sum to matrix[r][c];
        val dp = matrix[0].clone()
        val minSumsReachTarget = ArrayDeque<Int>()
        for (r in 1..<matrix.size) {
            minSumsReachTarget.apply {
                addLast(dp[0])
                addLast(dp[0])
            }
            for (c in matrix[r].indices) {
                dp
                    .getOrNull(c + 1)
                    ?.let { minSumsReachTarget.addLast(it) }
                dp[c] = matrix[r][c] + minSumsReachTarget.min()!!
                minSumsReachTarget.removeFirst()
            }
            minSumsReachTarget.removeLast()
        }
        return dp.min()!!
    }
}