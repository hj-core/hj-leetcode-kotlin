package com.hj.leetcode.kotlin.problem63

/**
 * LeetCode page: [63. Unique Paths II](https://leetcode.com/problems/unique-paths-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the number of rows and columns in obstacleGrid;
     */
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        val numRows = obstacleGrid.size
        val numColumns = obstacleGrid[0].size

        if (isObstacle(0, 0, obstacleGrid)
            || isObstacle(numRows - 1, numColumns - 1, obstacleGrid)
        ) {
            return 0
        }

        // dp[i][j] ::= unique paths if robot initially located at cell(i, j)
        val dp = Array(numRows + 1) { IntArray(numColumns + 1) }
        dp[numRows - 1][numColumns - 1] = 1

        for (row in obstacleGrid.indices.reversed()) {
            for (column in obstacleGrid[row].indices.reversed()) {
                if (isObstacle(row, column, obstacleGrid)) {
                    continue
                }
                dp[row][column] += dp[row + 1][column] + dp[row][column + 1]
            }
        }
        return dp[0][0]
    }

    private fun isObstacle(row: Int, column: Int, obstacleGrid: Array<IntArray>): Boolean {
        return obstacleGrid[row][column] == 1
    }
}