package com.hj.leetcode.kotlin.problem64

/**
 * LeetCode page: [64. Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(N) where M and N are the number of rows and columns of grid;
     */
    fun minPathSum(grid: Array<IntArray>): Int {
        val columnMinPathSum = columnMinPathSumOfFirstRow(grid)
        val numRows = grid.size
        repeat(numRows - 1) { currentRow ->
            updateColumnMinPathSumToNextRow(currentRow, grid, columnMinPathSum)
        }
        return columnMinPathSum.last()
    }

    private fun columnMinPathSumOfFirstRow(grid: Array<IntArray>): IntArray {
        val minPathSum = grid.first().clone()
        for (column in 1 until minPathSum.size) {
            minPathSum[column] += minPathSum[column - 1]
        }
        return minPathSum
    }

    private fun updateColumnMinPathSumToNextRow(currentRow: Int, grid: Array<IntArray>, columnMinPathSum: IntArray) {
        val nextRow = currentRow + 1
        val numColumns = grid[currentRow].size
        columnMinPathSum[0] = grid[nextRow][0] + columnMinPathSum[0]
        for (column in 1 until numColumns) {
            columnMinPathSum[column] = grid[nextRow][column] +
                    minOf(columnMinPathSum[column - 1], columnMinPathSum[column])
        }
    }
}