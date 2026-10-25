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
        // Prevent short-circuit evaluation which could fail test cases
        // like `grid = [[1,1,0,1,1],[1,0,0,0,1],[1,1,1,1,1]]`.
        val above = dfs(grid, r0 - 1, c0, visited)
        val below = dfs(grid, r0 + 1, c0, visited)
        val left = dfs(grid, r0, c0 - 1, visited)
        val right = dfs(grid, r0, c0 + 1, visited)
        return above && below && left && right
    }
}
