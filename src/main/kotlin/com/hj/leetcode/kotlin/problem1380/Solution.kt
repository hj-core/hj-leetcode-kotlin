package com.hj.leetcode.kotlin.problem1380

/**
 * LeetCode page: [1380. Lucky Numbers in a Matrix](https://leetcode.com/problems/lucky-numbers-in-a-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(1) where M and N are mat.length and mat[0].length;
     */
    fun luckyNumbers(matrix: Array<IntArray>): List<Int> {
        val maxRowMin = getMaxOfRowMinimums(matrix)
        val minColumnMax = getMinOfColumnMaximums(matrix)
        return if (maxRowMin == minColumnMax) listOf(maxRowMin) else listOf()
    }

    private fun getMaxOfRowMinimums(matrix: Array<IntArray>): Int {
        var maxRowMin = checkNotNull(matrix[0].min())
        for (row in 1..matrix.lastIndex) {
            val rowMin = checkNotNull(matrix[row].min())
            if (maxRowMin < rowMin) maxRowMin = rowMin
        }
        return maxRowMin
    }

    private fun getMinOfColumnMaximums(matrix: Array<IntArray>): Int {
        var minColumnMax = getMaximumOfColumn(0, matrix)
        for (column in 1..matrix[0].lastIndex) {
            val columnMax = getMaximumOfColumn(column, matrix)
            if (minColumnMax > columnMax) minColumnMax = columnMax
        }
        return minColumnMax
    }

    private fun getMaximumOfColumn(column: Int, matrix: Array<IntArray>): Int {
        var columnMax = matrix[0][column]
        for (row in 1..matrix.lastIndex) {
            val num = matrix[row][column]
            if (columnMax < num) columnMax = num
        }
        return columnMax
    }
}