package com.hj.leetcode.kotlin.problem1266

import kotlin.math.abs
import kotlin.math.max

/**
 * LeetCode page: [1266. Minimum Time Visiting All Points](https://leetcode.com/problems/minimum-time-visiting-all-points/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of points;
     */
    fun minTimeToVisitAllPoints(points: Array<IntArray>): Int {
        return points
            .asSequence()
            .zipWithNext()
            .fold(0) { acc, pair ->
                acc + minVisitingTime(pair.first, pair.second)
            }
    }

    private fun minVisitingTime(fromPoint: IntArray, toPoint: IntArray): Int {
        val xDistance = abs(toPoint[0] - fromPoint[0])
        val yDistance = abs(toPoint[1] - fromPoint[1])
        return max(xDistance, yDistance)
    }
}