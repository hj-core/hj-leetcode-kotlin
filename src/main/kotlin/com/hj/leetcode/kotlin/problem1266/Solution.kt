package com.hj.leetcode.kotlin.problem1266

import kotlin.math.abs
import kotlin.math.max

/**
 * LeetCode page: [1266. Minimum Time Visiting All Points](https://leetcode.com/problems/minimum-time-visiting-all-points/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the size of points.
    fun minTimeToVisitAllPoints(points: Array<IntArray>): Int {
        var minTime = 0
        for (i in 1..<points.size) {
            minTime += minTravelTime(points[i - 1], points[i])
        }

        return minTime
    }

    private fun minTravelTime(
        pt1: IntArray,
        pt2: IntArray,
    ): Int {
        val xDistance = abs(pt2[0] - pt1[0])
        val yDistance = abs(pt2[1] - pt1[1])
        return max(xDistance, yDistance)
    }
}
