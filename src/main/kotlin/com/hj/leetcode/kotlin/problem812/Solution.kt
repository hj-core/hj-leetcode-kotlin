package com.hj.leetcode.kotlin.problem812

import kotlin.math.abs

/**
 * LeetCode page: [812. Largest Triangle Area](https://leetcode.com/problems/largest-triangle-area/);
 */
class Solution {
    // Complexity:
    // Time O(N^3) and Space O(1) where N is the length of
    // points.
    fun largestTriangleArea(points: Array<IntArray>): Double {
        val n = points.size
        var maxProduct = 0
        for (i in 0..<(n - 2)) {
            for (j in (i + 1)..<(n - 1)) {
                for (k in (j + 1)..<n) {
                    val product = crossProduct(points[i], points[j], points[k])
                    if (product > maxProduct) {
                        maxProduct = product
                    }
                }
            }
        }
        return maxProduct / 2.0
    }

    // Returns the magnitude of vec(pt1 - pt0) X vec(pt2 - pt0).
    private fun crossProduct(
        pt0: IntArray,
        pt1: IntArray,
        pt2: IntArray,
    ): Int {
        val vec1X = pt1[0] - pt0[0]
        val vec1Y = pt1[1] - pt0[1]
        val vec2X = pt2[0] - pt0[0]
        val vec2Y = pt2[1] - pt0[1]
        return abs(vec1X * vec2Y - vec1Y * vec2X)
    }
}
