package com.hj.leetcode.kotlin.problem463

/**
 * LeetCode page: [463. Island Perimeter](https://leetcode.com/problems/island-perimeter/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(1) where M is the number of rows
     * and N is the number of columns in grid;
     */
    fun islandPerimeter(grid: Array<IntArray>): Int {
        var result = 0
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                result += countInterfaces(grid, row, column)
            }
        }
        return result
    }

    private fun countInterfaces(grid: Array<IntArray>, row: Int, column: Int): Int {
        if (grid[row][column] == 0) {
            return 0
        }

        var result = 0
        for (shift in intArrayOf(-1, 1)) {
            if (row + shift !in grid.indices || grid[row + shift][column] == 0) {
                result += 1
            }
            if (column + shift !in grid[row].indices || grid[row][column + shift] == 0) {
                result += 1
            }
        }
        return result
    }
}