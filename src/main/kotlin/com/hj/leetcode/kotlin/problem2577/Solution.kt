package com.hj.leetcode.kotlin.problem2577

import java.util.*

/**
 * LeetCode page: [2577. Minimum Time to Visit a Cell In a Grid](https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid/);
 */
class Solution {
    /* Complexity:
     * Time O(MNLog(MN)) and Space O(MN)
     * where M and N are the number of rows and columns in grid.
     */
    fun minimumTime(grid: Array<IntArray>): Int {
        if (grid[0][1] > 1 && grid[1][0] > 1) {
            return -1
        }
        val m = grid.size
        val n = grid[0].size
        val moves =
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 0),
                intArrayOf(0, -1),
                intArrayOf(-1, 0),
            )

        // Modified Dijkstra's algorithm
        // dp[r][c]::= the minimum visiting time of ceil (r, c)
        val dp = Array(m) { IntArray(n) { Int.MAX_VALUE } }
        val pq = PriorityQueue<IntArray>(compareBy { (row, column) -> dp[row][column] })
        dp[0][0] = 0
        pq.offer(intArrayOf(0, 0))

        // Hint: dp[r][c] has the same parity as r + c
        while (dp[m - 1][n - 1] == Int.MAX_VALUE) {
            val (r0, c0) = pq.poll()
            for ((dr, dc) in moves) {
                val r = r0 + dr
                val c = c0 + dc

                if (r !in grid.indices ||
                    c !in grid[r].indices ||
                    dp[r][c] != Int.MAX_VALUE
                ) {
                    continue
                }

                dp[r][c] =
                    when {
                        grid[r][c] <= dp[r0][c0] -> dp[r0][c0] + 1
                        (grid[r][c] - dp[r0][c0]) % 2 == 1 -> grid[r][c]
                        else -> grid[r][c] + 1
                    }
                pq.offer(intArrayOf(r, c))
            }
        }
        return dp[m - 1][n - 1]
    }
}
