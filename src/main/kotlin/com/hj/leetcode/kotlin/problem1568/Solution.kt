package com.hj.leetcode.kotlin.problem1568

/**
 * LeetCode page: [1568. Minimum Number of Days to Disconnect Island](https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island/);
 */
class Solution {
    /* Complexity:
     * Time O((MN)^2) and Space O(MN) where M is the number of rows in grid
     * and N is the number of columns in grid;
     */
    fun minDays(grid: Array<IntArray>): Int {
        val lands = findAllLands(grid)

        return when {
            isDisconnected(lands) -> 0
            isDisconnectedInOneDay(lands) -> 1
            else -> 2 // The grid can be disconnected in at most two days. Consider the rightmost-bottom land.
        }
    }

    private data class Cell(val row: Int, val column: Int)

    private fun findAllLands(grid: Array<IntArray>): Set<Cell> = buildSet {
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                if (grid[row][column] == 1) {
                    add(Cell(row, column))
                }
            }
        }
    }

    private fun isDisconnected(lands: Set<Cell>): Boolean {
        if (lands.isEmpty()) {
            return true
        }

        val visited = mutableSetOf<Cell>()
        dfs(lands.first(), lands, visited)
        return visited.size < lands.size
    }

    private fun dfs(source: Cell, lands: Set<Cell>, visited: MutableSet<Cell>) {
        if (source in visited || source !in lands) {
            return
        }
        visited.add(source)

        dfs(source.copy(row = source.row - 1), lands, visited)
        dfs(source.copy(row = source.row + 1), lands, visited)
        dfs(source.copy(column = source.column - 1), lands, visited)
        dfs(source.copy(column = source.column + 1), lands, visited)
    }

    private fun isDisconnectedInOneDay(lands: Set<Cell>): Boolean {
        if (lands.size == 1) {
            return true
        }

        for (land in lands) {
            val visited = mutableSetOf(land)
            val source = lands.first { it != land }
            dfs(source, lands, visited)

            if (visited.size < lands.size) {
                return true
            }
        }
        return false
    }
}