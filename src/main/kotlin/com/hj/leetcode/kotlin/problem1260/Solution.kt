package com.hj.leetcode.kotlin.problem1260

/**
 * LeetCode page: [1260. Shift 2D Grid](https://leetcode.com/problems/shift-2d-grid/description/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Auxiliary Space O(1) where M and N are the number of rows
    // and columns in grid, respectively.
    fun shiftGrid(grid: Array<IntArray>, k: Int): List<List<Int>> {
        val m = grid.size
        val n = grid[0].size
        val size = m * n
        val k = k % size
        return List(m) { r ->
            List(n) { c ->
                val oldIndex = (r * n + c - k).let { if (it < 0) it + size else it }
                get(grid, oldIndex)
            }
        }
    }

    private fun get(grid: Array<IntArray>, index: Int): Int {
        val r = index / grid[0].size
        val c = index % grid[0].size
        return grid[r][c]
    }
}
