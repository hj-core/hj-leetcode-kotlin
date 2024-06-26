package com.hj.leetcode.kotlin.problem861

import kotlin.math.max

/**
 * LeetCode page: [861. Score After Flipping Matrix](https://leetcode.com/problems/score-after-flipping-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(1) where M is the number of rows in grid
     * and N is the number of columns in grid;
     */
    fun matrixScore(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        var result = 0
        var bitUnit = 1
        for (col in n - 1 downTo 0) {
            val countEffectiveZeros = grid.count { it[0] != it[col] }
            result += max(countEffectiveZeros, m - countEffectiveZeros) * bitUnit
            bitUnit *= 2
        }
        return result
    }
}