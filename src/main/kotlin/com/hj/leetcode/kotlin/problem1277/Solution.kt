package com.hj.leetcode.kotlin.problem1277

/**
 * LeetCode page: [1277. Count Square Submatrices with All Ones](https://leetcode.com/problems/count-square-submatrices-with-all-ones/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(N) where M and N are the number
    // of rows and columns in matrix, respectively.
    fun countSquares(matrix: Array<IntArray>): Int {
        var result = 0
        // dp[c]@r:= the size of the largest one-square whose
        // top-left corner is cell(r, c).
        val dp = IntArray(matrix[0].size + 1) // base case: r=matrix.size

        for (r in matrix.indices.reversed()) {
            var tmp = 0 // dp[c+1]@r+1
            for (c in matrix[r].indices.reversed()) {
                val subResult =
                    if (matrix[r][c] == 0) {
                        0
                    } else {
                        1 + minOf(dp[c], dp[c + 1], tmp)
                    }
                result += subResult
                dp[c] = subResult.also { tmp = dp[c] }
            }
        }
        return result
    }
}
