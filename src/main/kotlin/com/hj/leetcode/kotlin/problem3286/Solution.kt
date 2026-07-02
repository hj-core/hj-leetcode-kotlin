package com.hj.leetcode.kotlin.problem3286

/**
 * LeetCode page: [3286. Find a Safe Walk Through a Grid](https://leetcode.com/problems/find-a-safe-walk-through-a-grid/);
 */
class Solution {
    // Complexity:
    // Time O(mn) and Space O(mn) where m and n are the number of rows and
    // columns of grid, respectively.
    fun findSafeWalk(
        grid: List<List<Int>>,
        health: Int,
    ): Boolean {
        val dirs = intArrayOf(-1, 0, 1, 0, -1)
        val m = grid.size
        val n = grid[0].size
        val minHealth = Array(m) { IntArray(n) { Int.MAX_VALUE } }
        val queue = ArrayDeque<Pair<Int, Int>>()

        // 0-1 BFS
        minHealth[0][0] = grid[0][0] + 1
        queue.add(Pair(0, 0))
        while (queue.isNotEmpty()) {
            val (r, c) = queue.removeFirst()
            for (i in 0..<4) {
                val nextR = r + dirs[i]
                val nextC = c + dirs[i + 1]
                if (nextR !in grid.indices || nextC !in grid[nextR].indices) {
                    continue
                }
                if (minHealth[nextR][nextC] != Int.MAX_VALUE) {
                    continue
                }

                minHealth[nextR][nextC] = minHealth[r][c] + grid[nextR][nextC]
                if (grid[nextR][nextC] == 0) {
                    queue.addFirst(Pair(nextR, nextC))
                } else {
                    queue.add(Pair(nextR, nextC))
                }
            }
        }

        return minHealth[m - 1][n - 1] <= health
    }
}
