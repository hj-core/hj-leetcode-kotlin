package com.hj.leetcode.kotlin.problem1020

/**
 * LeetCode page: [1020. Number of Enclaves](https://leetcode.com/problems/number-of-enclaves/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the number of rows and columns of grid;
     */
    fun numEnclaves(grid: Array<IntArray>): Int {
        var numEnclaves = numLands(grid)
        visitAllLandsCanWalkOffBoundary(grid) { numEnclaves-- }
        return numEnclaves
    }

    private fun numLands(grid: Array<IntArray>): Int {
        var numLands = 0
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                val value = grid[row][column]
                if (isLand(value)) numLands++
            }
        }
        return numLands
    }

    private fun isLand(gridValue: Int): Boolean = gridValue == 1

    private fun visitAllLandsCanWalkOffBoundary(
        grid: Array<IntArray>,
        onEachLand: () -> Unit
    ) {
        val numRows = grid.size
        val numColumns = grid[0].size
        val visited = Array(numRows) { BooleanArray(numColumns) }

        val boundaryRows = hashSetOf(0, numRows - 1)
        for (row in boundaryRows) {
            for (column in grid[row].indices) {
                visitAllConnectedUnvisitedLands(Cell(row, column), grid, visited, onEachLand)
            }
        }

        val boundaryColumns = hashSetOf(0, numColumns - 1)
        for (row in 0 until numRows) {
            for (column in boundaryColumns) {
                visitAllConnectedUnvisitedLands(Cell(row, column), grid, visited, onEachLand)
            }
        }
    }

    private data class Cell(val row: Int, val column: Int)

    private fun visitAllConnectedUnvisitedLands(
        source: Cell,
        grid: Array<IntArray>,
        visited: Array<BooleanArray>,
        onEachLand: () -> Unit
    ) {
        if (source.isUnvisitedLand(grid, visited)) {
            onEachLand()
            val (row, column) = source
            visited[row][column] = true

            visitAllConnectedUnvisitedLands(Cell(row + 1, column), grid, visited, onEachLand)
            visitAllConnectedUnvisitedLands(Cell(row - 1, column), grid, visited, onEachLand)
            visitAllConnectedUnvisitedLands(Cell(row, column + 1), grid, visited, onEachLand)
            visitAllConnectedUnvisitedLands(Cell(row, column - 1), grid, visited, onEachLand)
        }
    }

    private fun Cell.isUnvisitedLand(grid: Array<IntArray>, visited: Array<BooleanArray>): Boolean {
        return isWithinBounds(grid) && isLand(grid[row][column]) && !visited[row][column]
    }

    private fun Cell.isWithinBounds(grid: Array<IntArray>): Boolean {
        return row in grid.indices && column in grid[row].indices
    }
}