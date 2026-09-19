package com.hj.leetcode.kotlin.problem1401

/**
 * LeetCode page: [1401. Circle and Rectangle Overlapping](https://leetcode.com/problems/circle-and-rectangle-overlapping/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun checkOverlap(
        radius: Int,
        xCenter: Int,
        yCenter: Int,
        x1: Int,
        y1: Int,
        x2: Int,
        y2: Int,
    ): Boolean {
        val x = closest(xCenter, x1..x2)
        val y = closest(yCenter, y1..y2)
        return square(x - xCenter) <= square(radius) - square(y - yCenter)
    }

    private fun closest(
        p0: Int,
        range: IntRange,
    ): Int =
        when {
            p0 < range.first -> range.first
            p0 > range.last -> range.last
            else -> p0
        }

    private fun square(x: Int): Int = x * x
}
