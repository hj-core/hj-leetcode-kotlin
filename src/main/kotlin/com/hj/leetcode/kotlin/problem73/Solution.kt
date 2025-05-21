package com.hj.leetcode.kotlin.problem73

/**
 * LeetCode page: [73. Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(1) where M and N are the number of rows and
    // columns of matrix.
    fun setZeroes(matrix: Array<IntArray>) {
        val isFirstRowZeros = matrix[0].contains(0)

        // We use the first row to indicate whether a column should be set to zeros,
        // and the first column to indicate whether a row (except for the first row)
        // should be set to zeros.
        for (row in 1..<matrix.size) {
            for (col in matrix[row].indices) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0
                    matrix[0][col] = 0
                }
            }
        }

        for (row in 1..<matrix.size) {
            for (col in matrix[row].indices.reversed()) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0
                }
            }
        }

        if (isFirstRowZeros) {
            matrix[0].fill(0)
        }
    }
}
