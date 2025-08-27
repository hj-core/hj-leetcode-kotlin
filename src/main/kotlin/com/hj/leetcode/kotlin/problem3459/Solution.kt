package com.hj.leetcode.kotlin.problem3459

/**
 * LeetCode page: [3459. Length of Longest V-Shaped Diagonal Segment](https://leetcode.com/problems/length-of-longest-v-shaped-diagonal-segment/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(MN) where M and N are the number
    // of rows and columns in grid, respectively.
    fun lenOfVDiagonal(grid: Array<IntArray>): Int {
        if (!hasOne(grid)) {
            return 0
        }

        val m = grid.size
        val n = grid[0].size

        val dp45 = calcDp45NoTurn(grid)
        val dp315 = calcDp315NoTurn(grid)
        val dp225 = calcDp225NoTurn(grid)
        val dp135 = calcDp135NoTurn(grid)

        val dp45NoTurn = Array(dp45.size) { dp45[it].clone() }
        calcDp45MayTurnInPlace(dp45, grid, dp315)
        calcDp315MayTurnInPlace(dp315, grid, dp225)
        calcDp225MayTurnInPlace(dp225, grid, dp135)
        calcDp135MayTurnInPlace(dp135, grid, dp45NoTurn)

        var result = 1
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (grid[r][c] != 1) {
                    continue
                }

                // 45 degrees
                var nextR = r - 1
                var nextC = c + 1
                if (nextR >= 0 && nextC < n && grid[nextR][nextC] == 2) {
                    result = maxOf(result, 1 + dp45[nextR][nextC])
                }

                // 315 degrees
                nextR = r + 1
                nextC = c + 1
                if (nextR < m && nextC < n && grid[nextR][nextC] == 2) {
                    result = maxOf(result, 1 + dp315[nextR][nextC])
                }

                // 225 degrees
                nextR = r + 1
                nextC = c - 1
                if (nextR < m && nextC >= 0 && grid[nextR][nextC] == 2) {
                    result = maxOf(result, 1 + dp225[nextR][nextC])
                }

                // 135 degrees
                nextR = r - 1
                nextC = c - 1
                if (nextR >= 0 && nextC >= 0 && grid[nextR][nextC] == 2) {
                    result = maxOf(result, 1 + dp135[nextR][nextC])
                }
            }
        }
        return result
    }

    private fun hasOne(grid: Array<IntArray>): Boolean = grid.any { it.contains(1) }

    private fun calcDp45NoTurn(grid: Array<IntArray>): Array<IntArray> {
        val m = grid.size
        val n = grid[0].size
        val result = Array(m) { IntArray(n) }
        for (r in 0..<m) {
            for (c in n - 1 downTo 0) {
                if (grid[r][c] == 1) {
                    continue
                }
                val nextR = r - 1
                val nextC = c + 1
                if (nextR >= 0 && nextC < n && grid[nextR][nextC] != grid[r][c]) {
                    result[r][c] = 1 + result[nextR][nextC]
                } else {
                    result[r][c] = 1
                }
            }
        }
        return result
    }

    private fun calcDp315NoTurn(grid: Array<IntArray>): Array<IntArray> {
        val m = grid.size
        val n = grid[0].size
        val result = Array(m) { IntArray(n) }
        for (r in m - 1 downTo 0) {
            for (c in n - 1 downTo 0) {
                if (grid[r][c] == 1) {
                    continue
                }
                val nextR = r + 1
                val nextC = c + 1
                if (nextR < m && nextC < n && grid[nextR][nextC] != grid[r][c]) {
                    result[r][c] = 1 + result[nextR][nextC]
                } else {
                    result[r][c] = 1
                }
            }
        }
        return result
    }

    private fun calcDp225NoTurn(grid: Array<IntArray>): Array<IntArray> {
        val m = grid.size
        val n = grid[0].size
        val result = Array(m) { IntArray(n) }
        for (r in m - 1 downTo 0) {
            for (c in 0..<n) {
                if (grid[r][c] == 1) {
                    continue
                }
                val nextR = r + 1
                val nextC = c - 1
                if (nextR < m && nextC >= 0 && grid[nextR][nextC] != grid[r][c]) {
                    result[r][c] = 1 + result[nextR][nextC]
                } else {
                    result[r][c] = 1
                }
            }
        }
        return result
    }

    private fun calcDp135NoTurn(grid: Array<IntArray>): Array<IntArray> {
        val m = grid.size
        val n = grid[0].size
        val result = Array(m) { IntArray(n) }
        for (r in 0..<m) {
            for (c in 0..<n) {
                if (grid[r][c] == 1) {
                    continue
                }
                val nextR = r - 1
                val nextC = c - 1
                if (nextR >= 0 && nextC >= 0 && grid[nextR][nextC] != grid[r][c]) {
                    result[r][c] = 1 + result[nextR][nextC]
                } else {
                    result[r][c] = 1
                }
            }
        }
        return result
    }

    // Modify the dp45NoTurn to dp45MayTurn.
    private fun calcDp45MayTurnInPlace(
        dp45NoTurn: Array<IntArray>,
        grid: Array<IntArray>,
        dp315NoTurn: Array<IntArray>,
    ) {
        val m = grid.size
        val n = grid[0].size
        for (r in 0..<m) {
            for (c in n - 1 downTo 0) {
                if (grid[r][c] == 1) {
                    continue
                }
                val nextR = r - 1
                val nextC = c + 1
                if (nextR >= 0 && nextC < n && grid[nextR][nextC] != grid[r][c]) {
                    dp45NoTurn[r][c] = maxOf(1 + dp45NoTurn[nextR][nextC], dp315NoTurn[r][c])
                } else {
                    dp45NoTurn[r][c] = dp315NoTurn[r][c]
                }
            }
        }
    }

    // Modify the dp315NoTurn to dp315MayTurn.
    private fun calcDp315MayTurnInPlace(
        dp315NoTurn: Array<IntArray>,
        grid: Array<IntArray>,
        dp225NoTurn: Array<IntArray>,
    ) {
        val m = grid.size
        val n = grid[0].size
        for (r in m - 1 downTo 0) {
            for (c in n - 1 downTo 0) {
                if (grid[r][c] == 1) {
                    continue
                }
                val nextR = r + 1
                val nextC = c + 1
                if (nextR < m && nextC < n && grid[nextR][nextC] != grid[r][c]) {
                    dp315NoTurn[r][c] = maxOf(1 + dp315NoTurn[nextR][nextC], dp225NoTurn[r][c])
                } else {
                    dp315NoTurn[r][c] = dp225NoTurn[r][c]
                }
            }
        }
    }

    // Modify the dp225NoTurn to dp225MayTurn.
    private fun calcDp225MayTurnInPlace(
        dp225NoTurn: Array<IntArray>,
        grid: Array<IntArray>,
        dp135NoTurn: Array<IntArray>,
    ) {
        val m = grid.size
        val n = grid[0].size
        for (r in m - 1 downTo 0) {
            for (c in 0..<n) {
                if (grid[r][c] == 1) {
                    continue
                }
                val nextR = r + 1
                val nextC = c - 1
                if (nextR < m && nextC >= 0 && grid[nextR][nextC] != grid[r][c]) {
                    dp225NoTurn[r][c] = maxOf(1 + dp225NoTurn[nextR][nextC], dp135NoTurn[r][c])
                } else {
                    dp225NoTurn[r][c] = dp135NoTurn[r][c]
                }
            }
        }
    }

    // Modify the dp135NoTurn to dp135MayTurn.
    private fun calcDp135MayTurnInPlace(
        dp135NoTurn: Array<IntArray>,
        grid: Array<IntArray>,
        dp45NoTurn: Array<IntArray>,
    ) {
        val m = grid.size
        val n = grid[0].size
        for (r in 0..<m) {
            for (c in 0..<n) {
                if (grid[r][c] == 1) {
                    continue
                }
                val nextR = r - 1
                val nextC = c - 1
                if (nextR >= 0 && nextC >= 0 && grid[nextR][nextC] != grid[r][c]) {
                    dp135NoTurn[r][c] = maxOf(1 + dp135NoTurn[nextR][nextC], dp45NoTurn[r][c])
                } else {
                    dp135NoTurn[r][c] = dp45NoTurn[r][c]
                }
            }
        }
    }
}
