package com.hj.leetcode.kotlin.problem2033

import kotlin.math.abs

/**
 * LeetCode page: [2033. Minimum Operations to Make a Uni-Value Grid](https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/);
 */
class Solution {
    // Complexity:
    // Time O(KLogK) and Space O(K) where K is the flattened size of grid.
    fun minOperations(
        grid: Array<IntArray>,
        x: Int,
    ): Int {
        val m = grid.size
        val n = grid[0].size
        val requiredRem = grid[0][0] % x

        val heights = IntArray(m * n)
        var i = 0
        for (row in grid) {
            for (value in row) {
                if (value % x != requiredRem) {
                    return -1
                }
                heights[i++] = value
            }
        }
        heights.sort()

        val median = heights[heights.size / 2]
        return heights.sumOf { abs(it - median) / x }
    }
}
