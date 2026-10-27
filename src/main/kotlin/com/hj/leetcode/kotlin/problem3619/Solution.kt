package com.hj.leetcode.kotlin.problem3619

/**
 * LeetCode page: [3619. Count Islands With Total Value Divisible by K](https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(MN) where M and N are the number of rows
    // and columns of grid, respectively.
    fun countIslands(
        grid: Array<IntArray>,
        k: Int,
    ): Int {
        val m = grid.size
        val n = grid[0].size
        val visited = Array(m) { BooleanArray(n) }
        var count = 0
        for (r in 0..<m) {
            for (c in 0..<n) {
                val sum = dfs(grid, r, c, visited)
                if (0 < sum && sum % k == 0L) {
                    count++
                }
            }
        }
        return count
    }

    // Explores the connected component reachable from (r0, c0) and
    // returns the value sum.
    private fun dfs(
        grid: Array<IntArray>,
        r0: Int,
        c0: Int,
        visited: Array<BooleanArray>,
    ): Long {
        if (r0 !in grid.indices || c0 !in grid[r0].indices) {
            return 0
        }
        if (visited[r0][c0] || grid[r0][c0] == 0) {
            return 0
        }
        visited[r0][c0] = true
        return grid[r0][c0].toLong() +
            dfs(grid, r0 - 1, c0, visited) +
            dfs(grid, r0 + 1, c0, visited) +
            dfs(grid, r0, c0 - 1, visited) +
            dfs(grid, r0, c0 + 1, visited)
    }
}
