package com.hj.leetcode.kotlin.problem452

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [452. Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/);
 */
class Solution2 {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of points;
     */
    fun findMinArrowShots(points: Array<IntArray>): Int {
        val sortedRanges = points.sortedBy { it[0] } // by point.left
        var result = 0
        var arrowRange = intArrayOf(0, -1)

        for (range in sortedRanges) {
            arrowRange = intersect(arrowRange, range)
            if (arrowRange[1] < arrowRange[0]) {
                result += 1
                arrowRange = range
            }
        }
        return result
    }

    private fun intersect(range1: IntArray, range2: IntArray): IntArray {
        return intArrayOf(
            max(range1[0], range2[0]),
            min(range1[1], range2[1])
        )
    }
}