package com.hj.leetcode.kotlin.problem64

/**
 * LeetCode page: [64. Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(N) where M and N are the number of rows and columns of grid;
     */
    fun minPathSum(grid: Array<IntArray>): Int {
        // rowMinPathSum[i] ::= min path sum to reach column i of current row
        val rowMinPathSum = minPathSumOfFirstRow(grid)
        val numRows = grid.size
        repeat(numRows - 1) { currentRow ->
            updateMinPathSumToNextRow(currentRow, grid, rowMinPathSum)
        }
        return rowMinPathSum.last()
    }

    private fun minPathSumOfFirstRow(grid: Array<IntArray>): IntArray {
        val minPathSum = grid.first().clone()
        for (column in 1 until minPathSum.size) {
            minPathSum[column] += minPathSum[column - 1]
        }
        return minPathSum
    }

    private fun updateMinPathSumToNextRow(currentRow: Int, grid: Array<IntArray>, rowMinPathSum: IntArray) {
        val nextRow = currentRow + 1
        val numColumns = grid[currentRow].size
        rowMinPathSum[0] = grid[nextRow][0] + rowMinPathSum[0]
        for (column in 1 until numColumns) {
            rowMinPathSum[column] = grid[nextRow][column] +
                    minOf(rowMinPathSum[column - 1], rowMinPathSum[column])
        }
    }
}