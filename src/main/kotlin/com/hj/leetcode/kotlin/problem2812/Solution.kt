package com.hj.leetcode.kotlin.problem2812

/**
 * LeetCode page: [2812. Find the Safest Path in a Grid](https://leetcode.com/problems/find-the-safest-path-in-a-grid/);
 */
class Solution {
    // Complexity:
    // Time O(n^2 Log n) and Space O(n^2) where n is the size of grid.
    fun maximumSafenessFactor(grid: List<List<Int>>): Int {
        val cellSafeness = computeCellSafeness(grid)
        // Binary search for the maximum factor in [lower-1, upper]
        var lower = 1
        var upper = minOf(cellSafeness[0][0], cellSafeness.last().last())
        while (lower <= upper) {
            val mid = lower + (upper - lower) / 2
            val visited = Array(grid.size) { BooleanArray(grid.size) }
            if (isReachable(Pair(0, 0), cellSafeness, mid, visited)) {
                lower = mid + 1
            } else {
                upper = mid - 1
            }
        }
        return upper
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

    private fun isReachable(
        start: Pair<Int, Int>,
        cellSafeness: Array<IntArray>,
        minSafeness: Int,
        visited: Array<BooleanArray>,
    ): Boolean {
        val (r, c) = start
        if (r !in cellSafeness.indices || c !in cellSafeness[r].indices) {
            return false
        }

        if (visited[r][c]) {
            return false
        }
        visited[r][c] = true

        if (cellSafeness[r][c] < minSafeness) {
            return false
        }

        if (r == cellSafeness.size - 1 && c == cellSafeness.size - 1) {
            return true
        }

        return isReachable(Pair(r + 1, c), cellSafeness, minSafeness, visited) ||
            isReachable(Pair(r - 1, c), cellSafeness, minSafeness, visited) ||
            isReachable(Pair(r, c + 1), cellSafeness, minSafeness, visited) ||
            isReachable(Pair(r, c - 1), cellSafeness, minSafeness, visited)
    }
}
