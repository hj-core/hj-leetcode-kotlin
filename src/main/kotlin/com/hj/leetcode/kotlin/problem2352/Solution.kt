package com.hj.leetcode.kotlin.problem2352

/**
 * LeetCode page: [2352. Equal Row and Column Pairs](https://leetcode.com/problems/equal-row-and-column-pairs/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the number of rows and columns in grid;
     */
    fun equalPairs(grid: Array<IntArray>): Int {
        val distinctRows = grid
            .groupingBy { row -> row.toList() }
            .eachCount() // entry = (row, rowCount)

        var result = 0
        for (columnIndex in grid[0].indices) {
            val column = grid.map { row -> row[columnIndex] }
            result += distinctRows[column] ?: 0
        }
        return result
    }
}