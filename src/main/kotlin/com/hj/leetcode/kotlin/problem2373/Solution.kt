package com.hj.leetcode.kotlin.problem2373

import kotlin.math.max

/**
 * LeetCode page: [2373. Largest Local Values in a Matrix](https://leetcode.com/problems/largest-local-values-in-a-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N^2) where N is the size of grid;
     */
    fun largestLocal(grid: Array<IntArray>): Array<IntArray> {
        return Array(grid.size - 2) { row ->
            IntArray(grid.size - 2) { column ->
                localLargest(grid, row + 1, column + 1)
            }
        }
    }

    private fun localLargest(grid: Array<IntArray>, midRow: Int, midColumn: Int): Int {
        var result = grid[midRow][midColumn]
        for (row in midRow - 1..midRow + 1) {
            for (column in midColumn - 1..midColumn + 1) {
                result = max(result, grid[row][column])
            }
        }
        return result
    }
}