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
        if (!hasUniqueRemainder(grid, x)) {
            return -1
        }

        val values = sortedValues(grid)
        val midValue = values[values.size / 2]
        return countOperations(values, midValue, x)
    }

    private fun hasUniqueRemainder(
        grid: Array<IntArray>,
        modulus: Int,
    ): Boolean {
        val remainder = grid[0][0] % modulus
        return grid.all { row -> row.all { it % modulus == remainder } }
    }

    private fun sortedValues(grid: Array<IntArray>): IntArray {
        val m = grid.size
        val n = grid[0].size

        val values = IntArray(m * n)
        var i = 0
        for (row in grid) {
            for (value in row) {
                values[i++] = value
            }
        }

        values.sort()
        return values
    }

    private fun countOperations(
        values: IntArray,
        targetValue: Int,
        x: Int,
    ): Int = values.sumOf { abs(it - targetValue) / x }
}
