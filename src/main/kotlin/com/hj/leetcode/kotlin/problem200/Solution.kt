package com.hj.leetcode.kotlin.problem200

/**
 * LeetCode page: [200. Number of Islands](https://leetcode.com/problems/number-of-islands/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M is the height of grid
     * and N is the width of grid;
     */
    fun numIslands(grid: Array<CharArray>): Int {
        var result = 0
        val visited = BooleanArray(grid.size * grid[0].size)
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                if (grid[row][column] == '0'
                    || visited[flattenedIndex(grid, row, column)]
                ) {
                    continue
                }
                result++
                dfs(grid, row, column, visited)
            }
        }
        return result
    }

    private fun flattenedIndex(grid: Array<CharArray>, row: Int, column: Int): Int {
        return row * grid[0].size + column
    }

    private fun dfs(grid: Array<CharArray>, row: Int, column: Int, visited: BooleanArray) {
        if (notInGrid(grid, row, column)
            || grid[row][column] == '0'
            || visited[flattenedIndex(grid, row, column)]
        ) {
            return
        }

        visited[flattenedIndex(grid, row, column)] = true
        for (shift in intArrayOf(-1, 1)) {
            dfs(grid, row + shift, column, visited)
            dfs(grid, row, column + shift, visited)
        }
    }

    private fun notInGrid(grid: Array<CharArray>, row: Int, column: Int): Boolean {
        return row !in grid.indices || column !in grid[row].indices
    }
}