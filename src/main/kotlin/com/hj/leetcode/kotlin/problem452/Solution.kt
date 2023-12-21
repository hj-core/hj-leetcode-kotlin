package com.hj.leetcode.kotlin.problem452

/**
 * LeetCode page: [452. Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of points;
     */
    fun findMinArrowShots(points: Array<IntArray>): Int {
        if (points.isEmpty()) return 0
        val sortedPoints = sortedPointsByEnd(points)
        var minShots = 1
        var currShotPosition = sortedPoints[0][1]
        for ((start, end) in sortedPoints) {
            if (start > currShotPosition) {
                minShots++
                currShotPosition = end
            }
        }
        return minShots
    }

    private fun sortedPointsByEnd(points: Array<IntArray>): List<IntArray> {
        return points.sortedBy { it[1] }
    }
}
