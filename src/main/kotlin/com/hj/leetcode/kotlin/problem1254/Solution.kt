package com.hj.leetcode.kotlin.problem1254

/**
 * LeetCode page: [1254. Number of Closed Islands](https://leetcode.com/problems/number-of-closed-islands/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the number of rows and columns of grid;
     */
    fun closedIsland(grid: Array<IntArray>): Int {
        var numClosedIslands = 0
        visitAllIslands(grid) { isClosed ->
            if (isClosed) numClosedIslands++
        }
        return numClosedIslands
    }

    private fun visitAllIslands(
        grid: Array<IntArray>,
        onEachIsland: (isClosed: Boolean) -> Unit
    ) {
        val numRows = grid.size
        val numColumns = grid[0].size
        val visited = Array(numRows) { BooleanArray(numColumns) }

        for (row in grid.indices) {
            for (column in grid[row].indices) {
                val cell = Cell(row, column)
                if (cell.isUnvisitedLand(grid, visited)) {
                    var isClosedIsland = true
                    visitAllConnectedUnvisitedLands(cell, grid, visited) { isOnBoundary ->
                        if (isOnBoundary) isClosedIsland = false
                    }
                    onEachIsland(isClosedIsland)
                }
            }
        }
    }

    private data class Cell(val row: Int, val column: Int)

    private fun Cell.isUnvisitedLand(grid: Array<IntArray>, visited: Array<BooleanArray>): Boolean {
        return isWithinBounds(grid) && isLand(grid[row][column]) && !visited[row][column]
    }

    private fun Cell.isWithinBounds(grid: Array<IntArray>): Boolean {
        return row in grid.indices && column in grid[row].indices
    }

    private fun isLand(gridValue: Int): Boolean = gridValue == 0

    private fun visitAllConnectedUnvisitedLands(
        source: Cell,
        grid: Array<IntArray>,
        visited: Array<BooleanArray>,
        onEachLand: (isOnBoundary: Boolean) -> Unit
    ) {
        if (source.isUnvisitedLand(grid, visited)) {
            onEachLand(source.isOnBoundary(grid))
            val (row, column) = source
            visited[row][column] = true

            visitAllConnectedUnvisitedLands(Cell(row + 1, column), grid, visited, onEachLand)
            visitAllConnectedUnvisitedLands(Cell(row - 1, column), grid, visited, onEachLand)
            visitAllConnectedUnvisitedLands(Cell(row, column + 1), grid, visited, onEachLand)
            visitAllConnectedUnvisitedLands(Cell(row, column - 1), grid, visited, onEachLand)
        }
    }

    private fun Cell.isOnBoundary(grid: Array<IntArray>): Boolean {
        return row == 0 || row == grid.lastIndex || column == 0 || column == grid[row].lastIndex
    }
}