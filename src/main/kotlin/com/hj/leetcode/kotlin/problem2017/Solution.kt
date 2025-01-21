package com.hj.leetcode.kotlin.problem2017

/**
 * LeetCode page: [2017. Grid Game](https://leetcode.com/problems/grid-game/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of columns in grid.
     */
    fun gridGame(grid: Array<IntArray>): Long {
        // Restrict robot2's strategy to moving down at index 0 or the last index.
        // Base case: robot1 moves down at index 0.
        var downAtLast = grid[0].fold(0L, Long::plus) - grid[0][0]
        var downAtZero = 0L
        var result = downAtLast

        // Try other positions at which robot1 moves down
        for (i in 1..<grid[0].size) {
            downAtLast -= grid[0][i]
            downAtZero += grid[1][i - 1]
            result = minOf(result, maxOf(downAtLast, downAtZero))
        }
        return result
    }
}
