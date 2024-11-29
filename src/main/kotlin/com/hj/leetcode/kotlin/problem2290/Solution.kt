package com.hj.leetcode.kotlin.problem2290

/**
 * LeetCode page: [2290. Minimum Obstacle Removal to Reach Corner](https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the number of rows and columns
     * in grid, respectively.
     */
    fun minimumObstacles(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val empty = 0
        val obstacle = 1
        val moves =
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 0),
                intArrayOf(0, -1),
                intArrayOf(-1, 0),
            )

        val infLevel = m + n
        // dp[i][j]::= the minimum removal required to reach cell(i, j), i.e. its level
        val dp = Array(m) { IntArray(n) { infLevel } }
        dp[0][0] = 0
        var currLevelCells = mutableListOf(intArrayOf(0, 0))
        var nextLevelCells = mutableListOf<IntArray>()

        while (dp[m - 1][n - 1] == infLevel) {
            while (currLevelCells.isNotEmpty()) {
                val (row, col) = currLevelCells.removeLast()
                for ((dr, dc) in moves) {
                    val rowMoved = row + dr
                    val colMoved = col + dc

                    if (rowMoved !in grid.indices ||
                        colMoved !in grid[rowMoved].indices ||
                        dp[rowMoved][colMoved] != infLevel
                    ) {
                        continue
                    }

                    if (grid[rowMoved][colMoved] == empty) {
                        dp[rowMoved][colMoved] = dp[row][col]
                        currLevelCells.add(intArrayOf(rowMoved, colMoved))
                    } else {
                        dp[rowMoved][colMoved] = dp[row][col] + 1
                        nextLevelCells.add(intArrayOf(rowMoved, colMoved))
                    }
                }
            }
            currLevelCells = nextLevelCells.also { nextLevelCells = currLevelCells }
        }
        return dp[m - 1][n - 1]
    }
}
