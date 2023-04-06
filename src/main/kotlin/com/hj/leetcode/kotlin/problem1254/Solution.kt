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
                isWater(grid[row][column]) // set water visited
            }
        }
        var numClosedIsland = 0
        visitAllLands(grid, visited) { isClosed ->
            if (isClosed) numClosedIsland++
        }
        return numClosedIsland
    }

    private fun isWater(gridValue: Int): Boolean = gridValue == 1

    private fun visitAllLands(
        grid: Array<IntArray>,
        visited: Array<BooleanArray>,
        onEachIsland: (isClosed: Boolean) -> Unit
    ) {
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                visitAllConnectedLands(Land(row, column), grid, visited, onEachIsland)
            }
        }
    }

    private fun visitAllConnectedLands(
        sourceLand: Land,
        grid: Array<IntArray>,
        visited: Array<BooleanArray>,
        onEachIsland: (isClosed: Boolean) -> Unit
    ) {
        if (sourceLand.isOutOfBoundary(grid)) {
            onEachIsland(false)
            return
        }

        val (row, column) = sourceLand
        val hasVisited = visited[row][column]
        val isWater = isWater(grid[row][column])
        if (hasVisited || isWater) return

        visited[row][column] = true
        var isClosed = true
        visitAllConnectedLands(Land(row + 1, column), grid, visited) { isClosed = isClosed && it}
        visitAllConnectedLands(Land(row - 1, column), grid, visited) { isClosed = isClosed && it}
        visitAllConnectedLands(Land(row, column + 1), grid, visited) { isClosed = isClosed && it}
        visitAllConnectedLands(Land(row, column - 1), grid, visited) { isClosed = isClosed && it}
        onEachIsland(isClosed)
    }

    private data class Land(val row: Int, val column: Int)

    private fun Land.isOutOfBoundary(grid: Array<IntArray>): Boolean {
        return row !in grid.indices || column !in grid[row].indices
    }
}