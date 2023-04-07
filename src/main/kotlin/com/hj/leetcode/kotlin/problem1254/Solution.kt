package com.hj.leetcode.kotlin.problem1254

/**
 * LeetCode page: [1254. Number of Closed Islands](https://leetcode.com/problems/number-of-closed-islands/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the number of rows and columns of grid;
     */
    fun closedIsland(grid: Array<IntArray>): Int {
        val numRows = grid.size
        val numColumns = grid[0].size
        val visited = Array(numRows) { BooleanArray(numColumns) }
        var numClosedIslands = 0
        visitAllIslands(grid, visited) { isClosed ->
            if (isClosed) numClosedIslands++
        }
        return numClosedIslands
    }

    private fun visitAllIslands(
        grid: Array<IntArray>,
        visited: Array<BooleanArray>,
        onEachIsland: (isClosed: Boolean) -> Unit
    ) {
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                val cell = Cell(row, column)
                if (cell.isNotUnvisitedLand(grid, visited)) {
                    continue
                }

                var isClosedIsland = true
                visitAllConnectedLands(cell, grid, visited) { isOnBoundary ->
                    if (isOnBoundary) isClosedIsland = false
                }
                onEachIsland(isClosedIsland)
            }
        }
    }

    private data class Cell(val row: Int, val column: Int)

    private fun Cell.isNotUnvisitedLand(grid: Array<IntArray>, visited: Array<BooleanArray>): Boolean {
        return isOutOfBounds(grid) || isWater(grid[row][column]) || visited[row][column]
    }

    private fun Cell.isOutOfBounds(grid: Array<IntArray>): Boolean {
        return row !in grid.indices || column !in grid[row].indices
    }

    private fun isWater(gridValue: Int): Boolean = gridValue == 1

    private fun visitAllConnectedLands(
        source: Cell,
        grid: Array<IntArray>,
        visited: Array<BooleanArray>,
        onEachLand: (isOnBoundary: Boolean) -> Unit
    ) {
        if (source.isNotUnvisitedLand(grid, visited)) {
            return
        }

        val (row, column) = source
        visited[row][column] = true
        onEachLand(source.isOnBoundary(grid))

        visitAllConnectedLands(Cell(row + 1, column), grid, visited, onEachLand)
        visitAllConnectedLands(Cell(row - 1, column), grid, visited, onEachLand)
        visitAllConnectedLands(Cell(row, column + 1), grid, visited, onEachLand)
        visitAllConnectedLands(Cell(row, column - 1), grid, visited, onEachLand)
    }

    private fun Cell.isOnBoundary(grid: Array<IntArray>): Boolean {
        return row == 0 || row == grid.lastIndex || column == 0 || column == grid[row].lastIndex
    }
}