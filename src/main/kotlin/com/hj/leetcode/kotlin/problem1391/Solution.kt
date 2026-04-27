package com.hj.leetcode.kotlin.problem1391

/**
 * LeetCode page: [1391. Check if There is a Valid Path in a Grid](https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(MN) where M and N are the number of rows and columns in grid.
    fun hasValidPath(grid: Array<IntArray>): Boolean {
        val seen = Array(grid.size) { BooleanArray(grid[0].size) }
        return dfs(0, 0, grid, seen)
    }

    private fun dfs(
        r: Int,
        c: Int,
        grid: Array<IntArray>,
        seen: Array<BooleanArray>,
    ): Boolean {
        if (seen[r][c]) {
            return false
        }
        if (r == grid.lastIndex && c == grid[r].lastIndex) {
            return true
        }

        seen[r][c] = true
        return when (grid[r][c]) {
            1 -> {
                tryDfsLeft(r, c, grid, seen) || tryDfsRight(r, c, grid, seen)
            }

            2 -> {
                tryDfsUp(r, c, grid, seen) || tryDfsDown(r, c, grid, seen)
            }

            3 -> {
                tryDfsLeft(r, c, grid, seen) || tryDfsDown(r, c, grid, seen)
            }

            4 -> {
                tryDfsRight(r, c, grid, seen) || tryDfsDown(r, c, grid, seen)
            }

            5 -> {
                tryDfsLeft(r, c, grid, seen) || tryDfsUp(r, c, grid, seen)
            }

            6 -> {
                tryDfsRight(r, c, grid, seen) || tryDfsUp(r, c, grid, seen)
            }

            else -> {
                throw IllegalArgumentException("Invalid grid value ${grid[r][c]}")
            }
        }
    }

    private fun tryDfsLeft(
        r: Int,
        c: Int,
        grid: Array<IntArray>,
        seen: Array<BooleanArray>,
    ): Boolean = c > 0 && grid[r][c - 1] in intArrayOf(1, 4, 6) && dfs(r, c - 1, grid, seen)

    private fun tryDfsRight(
        r: Int,
        c: Int,
        grid: Array<IntArray>,
        seen: Array<BooleanArray>,
    ): Boolean =
        c < grid[r].lastIndex && grid[r][c + 1] in intArrayOf(1, 3, 5) &&
            dfs(r, c + 1, grid, seen)

    private fun tryDfsUp(
        r: Int,
        c: Int,
        grid: Array<IntArray>,
        seen: Array<BooleanArray>,
    ): Boolean = r > 0 && grid[r - 1][c] in intArrayOf(2, 3, 4) && dfs(r - 1, c, grid, seen)

    private fun tryDfsDown(
        r: Int,
        c: Int,
        grid: Array<IntArray>,
        seen: Array<BooleanArray>,
    ): Boolean =
        r < grid.lastIndex && grid[r + 1][c] in intArrayOf(2, 5, 6) &&
            dfs(r + 1, c, grid, seen)
}
