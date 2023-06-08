package com.hj.leetcode.kotlin.problem1351

/**
 * LeetCode page: [1351. Count Negative Numbers in a Sorted Matrix](https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(1) where M and N are the number of rows and columns in grid;
     */
    fun countNegatives(grid: Array<IntArray>): Int {
        val hasNoNegative = grid.last().last() >= 0
        if (hasNoNegative) {
            return 0
        }

        val isAllNegative = grid.first().first() < 0
        if (isAllNegative) {
            return grid.size * grid[0].size
        }

        var result = 0
        var columnFirstNegative = grid[0].size
        for (rowValues in grid) {
            while (columnFirstNegative > 0 && rowValues[columnFirstNegative - 1] < 0) {
                columnFirstNegative--
            }
            result += rowValues.size - columnFirstNegative
        }
        return result
    }
}