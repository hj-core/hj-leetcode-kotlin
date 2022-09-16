package com.hj.leetcode.kotlin.problem11

/**
 * LeetCode page: [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of height;
     */
    fun maxArea(height: IntArray): Int {
        var maxArea = 0
        var leftBoundIndex = 0
        var rightBoundIndex = height.lastIndex

        while (leftBoundIndex < rightBoundIndex) {
            val area = calculateArea(leftBoundIndex, rightBoundIndex, height)
            maxArea = maxOf(maxArea, area)

            val shouldMoveTowardsRight = height[leftBoundIndex] < height[rightBoundIndex]
            if (shouldMoveTowardsRight) leftBoundIndex++ else rightBoundIndex--
        }
        return maxArea
    }

    private fun calculateArea(leftBoundIndex: Int, rightBoundIndex: Int, heights: IntArray): Int {
        val effectiveHeight = minOf(heights[leftBoundIndex], heights[rightBoundIndex])
        val width = rightBoundIndex - leftBoundIndex
        return width * effectiveHeight
    }
}