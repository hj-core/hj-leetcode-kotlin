package com.hj.leetcode.kotlin.problem1870

/**
 * LeetCode page: [1870. Minimum Speed to Arrive on Time](https://leetcode.com/problems/minimum-speed-to-arrive-on-time/);
 */
class Solution {

    private val minPossibleSpeed = 1
    private val maxPossibleSpeed = 10_000_000
    private val valueIfCanNotOnTime = -1

    /* Complexity:
     * Time O(NLogK) and Space O(1) where N is the size of dist and K is the size of possible speed range;
     */
    fun minSpeedOnTime(dist: IntArray, hour: Double): Int {
        if (canNotOnTime(dist, hour)) {
            return valueIfCanNotOnTime
        }

        var lowerBound = minPossibleSpeed
        var upperBound = maxPossibleSpeed

        while (lowerBound < upperBound) {
            val mid = lowerBound + (upperBound - lowerBound) / 2
            val travelTime = travelTime(dist, mid)
            if (hour < travelTime) {
                lowerBound = mid + 1
            } else {
                upperBound = mid
            }
        }
        return lowerBound
    }

    private fun canNotOnTime(dist: IntArray, hour: Double): Boolean {
        return hour < (dist.size - 1) + (dist.last().toDouble() / maxPossibleSpeed)
    }

    private fun travelTime(dist: IntArray, speed: Int): Double {
        var result = 0.0
        for (index in 0 until dist.lastIndex) {
            result += Math.ceil(dist[index].toDouble() / speed)
        }
        result += dist.last().toDouble() / speed
        return result
    }
}