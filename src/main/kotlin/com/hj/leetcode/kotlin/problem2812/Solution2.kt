package com.hj.leetcode.kotlin.problem2812

/**
 * LeetCode page: [2812. Find the Safest Path in a Grid](https://leetcode.com/problems/find-the-safest-path-in-a-grid/);
 */
class Solution2 {
    // Complexity:
    // Time O(n^2) and Space O(n^2) where n is the size of grid.
    fun maximumSafenessFactor(grid: List<List<Int>>): Int {
        val dir = intArrayOf(-1, 0, 1, 0, -1)
        val cellSafeness = computeCellSafeness(grid)

        // 0-1 BFS
        val queue = ArrayDeque<Pair<Int, Int>>()
        val pathSafeness = Array(grid.size) { IntArray(grid.size) { Int.MAX_VALUE } }
        pathSafeness[0][0] = cellSafeness[0][0]
        queue.add(Pair(0, 0))
        while (queue.isNotEmpty()) {
            val (r, c) = queue.removeFirst()
            for (i in 0..<4) {
                val nextR = r + dir[i]
                val nextC = c + dir[i + 1]
                if (nextR !in grid.indices || nextC !in grid[nextR].indices) {
                    continue
                }
                if (pathSafeness[nextR][nextC] != Int.MAX_VALUE) {
                    continue
                }
                if (cellSafeness[nextR][nextC] < pathSafeness[r][c]) {
                    pathSafeness[nextR][nextC] = cellSafeness[nextR][nextC]
                    queue.add(Pair(nextR, nextC))
                } else {
                    pathSafeness[nextR][nextC] = pathSafeness[r][c]
                    queue.addFirst(Pair(nextR, nextC))
                }
            }
        }

        return pathSafeness.last().last()
    }

    private fun computeCellSafeness(grid: List<List<Int>>): Array<IntArray> {
        val safeness = Array(grid.size) { IntArray(grid.size) { grid.size * 2 } }
        val queue = ArrayDeque<Pair<Int, Int>>()

        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (grid[r][c] == 1) {
                    queue.add(Pair(r, c))
                    safeness[r][c] = 0
                }
            }
        }

        var currSafeness = 0
        while (queue.isNotEmpty()) {
            currSafeness++

            repeat(queue.size) {
                val (r, c) = queue.removeFirst()
                if (r > 0 && safeness[r - 1][c] > currSafeness) {
                    safeness[r - 1][c] = currSafeness
                    queue.add(Pair(r - 1, c))
                }
                if (r < grid.size - 1 && safeness[r + 1][c] > currSafeness) {
                    safeness[r + 1][c] = currSafeness
                    queue.add(Pair(r + 1, c))
                }
                if (c > 0 && safeness[r][c - 1] > currSafeness) {
                    safeness[r][c - 1] = currSafeness
                    queue.add(Pair(r, c - 1))
                }
                if (c < grid.size - 1 && safeness[r][c + 1] > currSafeness) {
                    safeness[r][c + 1] = currSafeness
                    queue.add(Pair(r, c + 1))
                }
            }
        }

        return safeness
    }
}
