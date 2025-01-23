package com.hj.leetcode.kotlin.problem1267

/**
 * LeetCode page: [1267. Count Servers that Communicate](https://leetcode.com/problems/count-servers-that-communicate/);
 */
class Solution2 {
    /* Complexity:
     * Time O(MN) and Space O(M+N)
     * where M and N are the number of rows and columns in grid, respectively.
     */
    fun countServers(grid: Array<IntArray>): Int {
        // colServerCnt[col]::= the number of servers in column col
        val colServerCnt = IntArray(grid[0].size)
        // rowSingleCol[row]::= the column of the single server in row; otherwise, -1
        val rowSingleCol = IntArray(grid.size) { -1 }

        var result = 0
        for (row in grid.indices) {
            var rowServerCnt = 0
            for (col in grid[row].indices) {
                if (isServer(grid[row][col])) {
                    rowServerCnt++
                    colServerCnt[col]++
                    rowSingleCol[row] = col
                }
            }
            if (rowServerCnt > 1) {
                result += rowServerCnt
                rowSingleCol[row] = -1
            }
        }

        for (col in rowSingleCol) {
            if (col != -1 && colServerCnt[col] > 1) {
                result++
            }
        }
        return result
    }

    private fun isServer(value: Int): Boolean = value == 1
}
