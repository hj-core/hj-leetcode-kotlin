package com.hj.leetcode.kotlin.problem1219

import kotlin.math.max

/**
 * LeetCode page: [1219. Path with Maximum Gold](https://leetcode.com/problems/path-with-maximum-gold/);
 */
class Solution {
    /* Complexity:
     * Time O(MN * 3^(MN)) and Space O(MN) where M is the number of rows in grid
     * and N is the number of columns in grid;
     */
    fun getMaximumGold(grid: Array<IntArray>): Int {
        var result = 0
        val visited = hashSetOf<Pair<Int, Int>>()
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                dfs(grid, row, column, 0, visited) { pathSum ->
                    result = max(result, pathSum)
                }
            }
        }
        return result
    }

    private fun dfs(
        grid: Array<IntArray>,
        row: Int,
        column: Int,
        fromPathSum: Int,
        visited: MutableSet<Pair<Int, Int>>,
        onPathSum: (sum: Int) -> Unit
    ) {
        if (row !in grid.indices || column !in grid[row].indices) {
            return
        }
        if (grid[row][column] == 0 || Pair(row, column) in visited) {
            return
        }

        val pathSum = grid[row][column] + fromPathSum
        onPathSum(pathSum)
        visited.add(Pair(row, column))

        dfs(grid, row - 1, column, pathSum, visited, onPathSum)
        dfs(grid, row + 1, column, pathSum, visited, onPathSum)
        dfs(grid, row, column - 1, pathSum, visited, onPathSum)
        dfs(grid, row, column + 1, pathSum, visited, onPathSum)
        visited.remove(Pair(row, column))
    }
}