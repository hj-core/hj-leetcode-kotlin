package com.hj.leetcode.kotlin.problem2482

/**
 * LeetCode page: [2482. Difference Between Ones and Zeros in Row and Column](https://leetcode.com/problems/difference-between-ones-and-zeros-in-row-and-column/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(M+N) where M and N are the number of rows
     * and columns in grid;
     */
    fun onesMinusZeros(grid: Array<IntArray>): Array<IntArray> {
        val countRowZeros = IntArray(grid.size)
        val countColumnZeros = IntArray(grid[0].size)
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                if (grid[row][column] == 0) {
                    countRowZeros[row]++
                    countColumnZeros[column]++
                }
            }
        }

        val rowsPlusColumns = grid.size + grid[0].size
        return Array(grid.size) { row ->
            IntArray(grid[row].size) { column ->
                rowsPlusColumns - (countRowZeros[row] + countColumnZeros[column]) * 2
            }
        }
    }
}