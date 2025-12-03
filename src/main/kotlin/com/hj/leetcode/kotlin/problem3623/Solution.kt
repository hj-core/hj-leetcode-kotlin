package com.hj.leetcode.kotlin.problem3623

/**
 * LeetCode page: [3623. Count Number of Trapezoids I](https://leetcode.com/problems/count-number-of-trapezoids-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of points.
    fun countTrapezoids(points: Array<IntArray>): Int {
        // yCount[y]:= the number of points with y coordinate
        val yCount = points.groupingBy { it[1] }.eachCount()
        var result = 0L
        var totalPairs = 0L

        for ((_, cnt) in yCount) {
            val pairs = cnt.toLong() * (cnt - 1) / 2
            result += pairs * totalPairs
            totalPairs += pairs
        }
        return (result % 1_000_000_007).toInt()
    }
}
