package com.hj.leetcode.kotlin.problem3446

/**
 * LeetCode page: [3446. Sort Matrix by Diagonals](https://leetcode.com/problems/sort-matrix-by-diagonals/);
 */
class Solution {
    // Complexity:
    // Time O(N^2 * LogN) and Space O(N) where N is the number
    // of rows and columns in grid, respectively.
    fun sortMatrix(grid: Array<IntArray>): Array<IntArray> = sortTopRight(sortBottomLeft(grid))

    private fun sortTopRight(grid: Array<IntArray>): Array<IntArray> {
        val n = grid.size
        val values = MutableList(n - 1) { 0 }

        for (i in 1..<(n - 1)) {
            for (step in 0..<(n - i)) {
                values[step] = grid[step][i + step]
            }
            values.sort()

            for (step in 0..<(n - i)) {
                grid[step][i + step] = values[step]
            }
            values.removeLast()
        }
        return grid
    }

    private fun sortBottomLeft(grid: Array<IntArray>): Array<IntArray> {
        val n = grid.size
        val values = MutableList(n) { 0 }

        for (i in 0..<(n - 1)) {
            for (step in 0..<(n - i)) {
                values[step] = grid[i + step][step]
            }
            values.sortDescending()

            for (step in 0..<(n - i)) {
                grid[i + step][step] = values[step]
            }
            values.removeLast()
        }
        return grid
    }
}
