package com.hj.leetcode.kotlin.problem452

/**
 * LeetCode page: [452. Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of points;
     */
    fun findMinArrowShots(points: Array<IntArray>): Int {
        if (points.isEmpty()) {
            return 0
        }

        val sortedRanges = points.sortedBy { it[1] }
        var result = 1
        var arrowPosition = sortedRanges[0][1]
        for ((start, end) in sortedRanges) {
            if (start > arrowPosition) {
                result += 1
                arrowPosition = end
            }
        }
        return result
    }
}