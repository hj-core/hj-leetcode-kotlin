package com.hj.leetcode.kotlin.problem223

/**
 * LeetCode page: [223. Rectangle Area](https://leetcode.com/problems/rectangle-area/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun computeArea(ax1: Int, ay1: Int, ax2: Int, ay2: Int, bx1: Int, by1: Int, bx2: Int, by2: Int): Int {
        val rectangle1 = Rectangle(ax1, ay1, ax2, ay2)
        val rectangle2 = Rectangle(bx1, by1, bx2, by2)

        val area1 = rectangle1.area()
        val area2 = rectangle2.area()
        val overlapArea = getOverlapArea(rectangle1, rectangle2)

        return area1 + area2 - overlapArea
    }

    private data class Rectangle(
        val xBottomLeft: Int,
        val yBottomLeft: Int,
        val xTopRight: Int,
        val yTopRight: Int
    )

    private fun Rectangle.area(): Int {
        val side1 = xTopRight - xBottomLeft
        val side2 = yTopRight - yBottomLeft
        return side1 * side2
    }

    private fun getOverlapArea(rectangle1: Rectangle, rectangle2: Rectangle): Int {
        val side1 = getOverlapLength(
            line1Start = rectangle1.xBottomLeft,
            line1End = rectangle1.xTopRight,
            line2Start = rectangle2.xBottomLeft,
            line2End = rectangle2.xTopRight
        )

        val side2 = getOverlapLength(
            line1Start = rectangle1.yBottomLeft,
            line1End = rectangle1.yTopRight,
            line2Start = rectangle2.yBottomLeft,
            line2End = rectangle2.yTopRight
        )

        return side1 * side2
    }

    private fun getOverlapLength(line1Start: Int, line1End: Int, line2Start: Int, line2End: Int): Int {
        val overlapStart = maxOf(line1Start, line2Start)
        val overlapEnd = minOf(line1End, line2End)
        return (overlapEnd - overlapStart).coerceAtLeast(0)
    }
}