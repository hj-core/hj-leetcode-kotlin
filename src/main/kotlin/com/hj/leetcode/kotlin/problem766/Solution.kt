package com.hj.leetcode.kotlin.problem766

/**
 * LeetCode page: [766. Toeplitz Matrix](https://leetcode.com/problems/toeplitz-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(1) where M and N are the rows and columns of matrix;
     */
    fun isToeplitzMatrix(matrix: Array<IntArray>): Boolean {
        for (row in 1..matrix.lastIndex) {
            for (column in 1..matrix[row].lastIndex) {
                if (matrix[row][column] != matrix[row - 1][column - 1]) return false
            }
        }
        return true
    }
}