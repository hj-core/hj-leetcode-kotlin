package com.hj.leetcode.kotlin.problem1254

/**
 * LeetCode page: [1254. Number of Closed Islands](https://leetcode.com/problems/number-of-closed-islands/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(MN) where M and N are the number of rows and
    // columns in grid, respectively.
    fun closedIsland(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val visited = Array(m) { BooleanArray(n) }
        var closed = 0
        for (r in 1..<m - 1) {
            for (c in 1..<n - 1) {
                if (!visited[r][c] && grid[r][c] == 0 && dfs(grid, r, c, visited)) {
                    closed++
                }
            }
        }
        return closed
    }

    // Explores the island including (r0, c0) and return if it is closed.
    private fun dfs(
        grid: Array<IntArray>,
        r0: Int,
        c0: Int,
        visited: Array<BooleanArray>,
    ): Boolean {
        if (r0 !in grid.indices || c0 !in grid[r0].indices) {
            return false
        }
        if (visited[r0][c0] || grid[r0][c0] == 1) {
            return true
        }
        visited[r0][c0] = true
        var isClosed = true
        isClosed = dfs(grid, r0 - 1, c0, visited) && isClosed
        isClosed = dfs(grid, r0 + 1, c0, visited) && isClosed
        isClosed = dfs(grid, r0, c0 - 1, visited) && isClosed
        isClosed = dfs(grid, r0, c0 + 1, visited) && isClosed
        return isClosed
    }
}
