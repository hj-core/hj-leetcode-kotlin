package com.hj.leetcode.kotlin.problem1380

/**
 * LeetCode page: [1380. Lucky Numbers in a Matrix](https://leetcode.com/problems/lucky-numbers-in-a-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(1) where M is the number of rows
     * and N is the number of columns in matrix;
     */
    fun luckyNumbers (matrix: Array<IntArray>): List<Int> {
        for (row in matrix.indices) {
            val minColumn = columnOfMin(matrix, row)
            val maxRow = rowOfMax(matrix, minColumn)
            if (row == maxRow) {
                return listOf(matrix[row][minColumn])
            }
        }
        return emptyList()
    }

    private fun columnOfMin(matrix: Array<IntArray>, row: Int): Int {
        return matrix[row].indices.minBy { matrix[row][it] }
    }

    private fun rowOfMax(matrix: Array<IntArray>, column: Int): Int {
        return matrix.indices.maxBy { matrix[it][column] }
    }
}