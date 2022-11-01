package com.hj.leetcode.kotlin.problem1706

/**
 * LeetCode page: [1706. Where Will the Ball Fall](https://leetcode.com/problems/where-will-the-ball-fall/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Aux_Space O(1) where M and N are the number of rows and columns of grid;
     */
    fun findBall(grid: Array<IntArray>): IntArray {
        val totalColumns = grid[0].size
        return IntArray(totalColumns) { index -> findExitColumn(index, grid) }
    }

    private fun findExitColumn(entryColumn: Int, grid: Array<IntArray>): Int {
        var currColumn = entryColumn

        for (row in grid.indices) {
            val direction = grid[row][currColumn]
            val nextColumn = currColumn + direction

            val isTrapped = grid[row].getOrNull(nextColumn) != direction
            if (isTrapped) return -1

            currColumn = nextColumn
        }

        return currColumn
    }
}