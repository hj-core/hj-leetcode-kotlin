package com.hj.leetcode.kotlin.problem3623

/**
 * LeetCode page: [3623. Count Number of Trapezoids I](https://leetcode.com/problems/count-number-of-trapezoids-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of points.
    fun countTrapezoids(points: Array<IntArray>): Int {
        // ySize[y]:= the number of points with y coordinate
        val ySize = points.groupingBy { it[1] }.eachCount()
        var result = 0L
        var sumPairs = 0L

        for ((_, size) in ySize) {
            val pairs = size.toLong() * (size - 1) / 2
            result += pairs * sumPairs
            sumPairs += pairs
        }
        return (result % 1_000_000_007).toInt()
    }
}
