package com.hj.leetcode.kotlin.problem223

/**
 * LeetCode page: [223. Rectangle Area](https://leetcode.com/problems/rectangle-area/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun computeArea(ax1: Int, ay1: Int, ax2: Int, ay2: Int, bx1: Int, by1: Int, bx2: Int, by2: Int): Int {
        val rec1Area = calculateRectangleArea(ax1, ax2, ay1, ay2)
        val rec2Area = calculateRectangleArea(bx1, bx2, by1, by2)

        val xOverlapLen = findOverlapLength(ax1..ax2, bx1..bx2)
        val yOverlapLen = findOverlapLength(ay1..ay2, by1..by2)
        val overlapArea = xOverlapLen * yOverlapLen

        return rec1Area + rec2Area - overlapArea
    }

    private fun calculateRectangleArea(
        xBottomLeft: Int, xTopRight: Int,
        yBottomLeft: Int, yTopRight: Int
    ): Int {
        return (xTopRight - xBottomLeft) * (yTopRight - yBottomLeft)
    }

    private fun findOverlapLength(intRange1: IntRange, intRange2: IntRange): Int {
        val range1 = if (intRange1.first < intRange1.last) intRange1 else intRange1.reversed()
        val range2 = if (intRange2.first < intRange2.last) intRange2 else intRange2.reversed()

        val startOfOverlap = maxOf(range1.first, range2.first)
        val endOfOverlap = minOf(range1.last, range2.last)
        return (endOfOverlap - startOfOverlap).coerceAtLeast(0)
    }
}