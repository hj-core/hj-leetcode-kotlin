package com.hj.leetcode.kotlin.problem2658

/**
 * LeetCode page: [2658. Maximum Number of Fish in a Grid](https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN)
     * where M and N are the number of rows and columns in grid.
     */
    fun findMaxFish(grid: Array<IntArray>): Int {
        val visited = Array(grid.size) { BooleanArray(grid[it].size) }
        var result = 0
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                result = maxOf(result, dfs(row, col, grid, visited))
            }
        }
        return result
    }

    private fun dfs(
        row: Int,
        col: Int,
        grid: Array<IntArray>,
        visited: Array<BooleanArray>,
    ): Int {
        if (row < 0 ||
            row > grid.lastIndex ||
            col < 0 ||
            col > grid[row].lastIndex ||
            grid[row][col] == 0 ||
            visited[row][col]
        ) {
            return 0
        }
        visited[row][col] = true
        return grid[row][col] +
            dfs(row, col + 1, grid, visited) +
            dfs(row, col - 1, grid, visited) +
            dfs(row + 1, col, grid, visited) +
            dfs(row - 1, col, grid, visited)
    }
}
