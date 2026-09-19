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
        val dx = xCenter.coerceIn(x1, x2) - xCenter
        val dy = yCenter.coerceIn(y1, y2) - yCenter
        return dx * dx <= radius * radius - dy * dy
    }
}
