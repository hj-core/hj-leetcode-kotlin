package com.hj.leetcode.kotlin.problem221

/**
 * LeetCode page: [221. Maximal Square](https://leetcode.com/problems/maximal-square/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(N) where M and N are the number of rows and columns of matrix;
     */
    fun maximalSquare(matrix: Array<CharArray>): Int {
        /* Here the dp is collapsed into one row whose full size will be the same as the matrix size.
         * For full size, the state dp[row][column] is the maxSize of square with cell(row, column) in
         * its lower right corner. The value of state is related to matrix[row][column] and the three
         * neighbour states(top, left, top left).
         */
        val dp = IntArray(matrix[0].size) { column -> matrix[0][column] - '0' }
        val hasOne = dp.any { it == 1 }
        var maxSize = if (hasOne) 1 else 0

        for (row in 1..matrix.lastIndex) {
            var dpTopLeft = dp[0]
            dp[0] = matrix[row][0] - '0'

            for (column in 1..matrix[0].lastIndex) {
                val cannotFormSquare = matrix[row][column] == '0'
                val newValue = if (cannotFormSquare) 0 else 1 + minOf(dp[column], dp[column - 1], dpTopLeft)
                dpTopLeft = dp[column]
                dp[column] = newValue
            }

            maxSize = maxOf(maxSize, dp.max()!!)
        }

        return maxSize * maxSize
    }
}