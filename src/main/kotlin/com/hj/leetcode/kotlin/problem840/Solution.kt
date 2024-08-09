package com.hj.leetcode.kotlin.problem840

/**
 * LeetCode page: [840. Magic Squares In Grid](https://leetcode.com/problems/magic-squares-in-grid/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(1) where M is the number of rows in grid
     * and N is the number of columns in grid;
     */
    fun numMagicSquaresInside(grid: Array<IntArray>): Int {
        if (grid.size < 3 || grid[0].size < 3) {
            return 0
        }

        val magicSquares = mutableSetOf(
            listOf(listOf(4, 3, 8), listOf(9, 5, 1), listOf(2, 7, 6)),
            listOf(listOf(2, 7, 6), listOf(9, 5, 1), listOf(4, 3, 8)),
            listOf(listOf(8, 3, 4), listOf(1, 5, 9), listOf(6, 7, 2)),
            listOf(listOf(6, 7, 2), listOf(1, 5, 9), listOf(8, 3, 4)),
            listOf(listOf(6, 1, 8), listOf(7, 5, 3), listOf(2, 9, 4)),
            listOf(listOf(8, 1, 6), listOf(3, 5, 7), listOf(4, 9, 2)),
            listOf(listOf(2, 9, 4), listOf(7, 5, 3), listOf(6, 1, 8)),
            listOf(listOf(4, 9, 2), listOf(3, 5, 7), listOf(8, 1, 6)),
        )

        var result = 0
        for (top in 0..(grid.size - 3)) {
            for (left in 0..(grid[top].size - 3)) {
                if (grid[top + 1][left + 1] != 5) {
                    continue
                }
                val square = listOf(
                    listOf(grid[top][left], grid[top][left + 1], grid[top][left + 2]),
                    listOf(grid[top + 1][left], grid[top + 1][left + 1], grid[top + 1][left + 2]),
                    listOf(grid[top + 2][left], grid[top + 2][left + 1], grid[top + 2][left + 2]),
                )
                if (square in magicSquares) {
                    result += 1
                }
            }
        }
        return result
    }
}