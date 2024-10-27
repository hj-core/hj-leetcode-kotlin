package com.hj.leetcode.kotlin.problem1277

/**
 * LeetCode page: [1277. Count Square Submatrices with All Ones](https://leetcode.com/problems/count-square-submatrices-with-all-ones/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(N) where M is the number of rows in matrix
     * and N is the number of columns in matrix.
     */
    fun countSquares(matrix: Array<IntArray>): Int {
        // dp[row][col] ::=
        // size of the largest one-square with (row, col) as its top-left corner,
        // i.e. the number of one-squares with (row, col) as its top-left corner.
        val dp = IntArray(matrix[0].size + 1) // base case: row=matrix.size
        var result = 0

        for (row in matrix.indices.reversed()) {
            var temp = 0 // dp[row+1][col+1]
            for (col in matrix[row].indices.reversed()) {
                val subResult =
                    if (matrix[row][col] == 0) {
                        0
                    } else { // 1 + minOf(dp[row-1][col], dp[row][col+1], dp[row+1][col+1])
                        1 + minOf(dp[col], dp[col + 1], temp)
                    }
                result += subResult
                dp[col] = subResult.also { temp = dp[col] }
            }
        }
        return result
    }
}
