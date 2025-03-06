package com.hj.leetcode.kotlin.problem2965

/**
 * LeetCode page: [2965. Find Missing and Repeated Values](https://leetcode.com/problems/find-missing-and-repeated-values/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(1) where N is the size of `grid`.
    fun findMissingAndRepeatedValues(grid: Array<IntArray>): IntArray {
        var sum = 0
        var sumOfSquares = 0L
        for (row in grid) {
            for (value in row) {
                sum += value
                sumOfSquares += value * value
            }
        }

        // Let a and b be the duplicated number and the missing number, respectively
        val n = grid.size
        val aMinusB = sum - sumTo(n * n)
        val aPlusB = (sumOfSquares - sumSquaresTo(n * n)) / aMinusB
        val a = (aPlusB + aMinusB) / 2
        val b = (aPlusB - aMinusB) / 2
        return intArrayOf(a.toInt(), b.toInt())
    }

    private fun sumTo(n: Int): Int = n * (n + 1) / 2

    private fun sumSquaresTo(n: Int): Long = n.toLong() * (n + 1) * (2 * n + 1) / 6
}
