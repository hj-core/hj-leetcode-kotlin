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
        val visited = Array(numRows) { row ->
            BooleanArray(numColumns) { column ->
                isWater(grid[row][column]) // set visited if it is water
            }
        }
        var numClosedIslands = 0
        visitAllLands(grid, visited) { isClosed ->
            if (isClosed) numClosedIslands++
        }
        return numClosedIslands
    }

    private fun isWater(gridValue: Int): Boolean = gridValue == 1

    private fun visitAllLands(
        grid: Array<IntArray>,
        visited: Array<BooleanArray>,
        onEachIsland: (isClosed: Boolean) -> Unit
    ) {
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                visitAllConnectedLands(Cell(row, column), grid, visited, onEachIsland)
            }
        }
    }

    private fun visitAllConnectedLands(
        origin: Cell,
        grid: Array<IntArray>,
        visited: Array<BooleanArray>,
        onEachIsland: (isClosed: Boolean) -> Unit
    ) {
        if (origin.isOutOfBounds(grid)) {
            onEachIsland(false)
            return
        }

        val (row, column) = origin
        val hasVisited = visited[row][column]
        val noConnectedLands = isWater(grid[row][column])
        if (hasVisited || noConnectedLands) {
            return
        }

        visited[row][column] = true
        var isClosed = true
        visitAllConnectedLands(Cell(row + 1, column), grid, visited) { isClosed = isClosed && it}
        visitAllConnectedLands(Cell(row - 1, column), grid, visited) { isClosed = isClosed && it}
        visitAllConnectedLands(Cell(row, column + 1), grid, visited) { isClosed = isClosed && it}
        visitAllConnectedLands(Cell(row, column - 1), grid, visited) { isClosed = isClosed && it}
        onEachIsland(isClosed)
    }

    private data class Cell(val row: Int, val column: Int)

    private fun Cell.isOutOfBounds(grid: Array<IntArray>): Boolean {
        return row !in grid.indices || column !in grid[row].indices
    }
}