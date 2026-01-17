package com.hj.leetcode.kotlin.problem3047

/**
 * LeetCode page: [3047. Find the Largest Area of Square Inside Two Rectangles](https://leetcode.com/problems/find-the-largest-area-of-square-inside-two-rectangles/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(1) where N is the length of bottomLeft
    // and topRight.
    fun largestSquareArea(
        bottomLeft: Array<IntArray>,
        topRight: Array<IntArray>,
    ): Long {
        var maxWidth = 0
        for (i in bottomLeft.indices) {
            val bottomLeft1 = bottomLeft[i]
            val topRight1 = topRight[i]

            for (j in 0..<i) {
                val bottomLeft2 = bottomLeft[j]
                val topRight2 = topRight[j]

                val width =
                    maxSquareWidth(
                        bottomLeft1,
                        topRight1,
                        bottomLeft2,
                        topRight2,
                    )
                maxWidth = maxOf(maxWidth, width)
            }
        }

        return maxWidth * maxWidth.toLong()
    }

    private fun maxSquareWidth(
        bottomLeft1: IntArray,
        topRight1: IntArray,
        bottomLeft2: IntArray,
        topRight2: IntArray,
    ): Int {
        val hWidth =
            minOf(topRight1[0], topRight2[0]) -
                maxOf(bottomLeft1[0], bottomLeft2[0])
        if (hWidth <= 0) {
            return 0
        }

        val vWidth =
            minOf(topRight1[1], topRight2[1]) -
                maxOf(bottomLeft1[1], bottomLeft2[1])
        if (vWidth <= 0) {
            return 0
        }

        return minOf(hWidth, vWidth)
    }
}
