package com.hj.leetcode.kotlin.problem2187

/**
 * LeetCode page: [2187. Minimum Time to Complete Trips](https://leetcode.com/problems/minimum-time-to-complete-trips/);
 */
class Solution {
    /* Complexity:
     * Time O(N * Log(MK)) and Space O(1) where N is the size of time, M is totalTrips,
     * and K is the minimum value in time.
     */
    fun minimumTime(time: IntArray, totalTrips: Int): Long {
        var lower = 0L
        var upper = guessUpperBound(time, totalTrips)
        while (lower < upper) {
            val mid = lower + (upper - lower) / 2
            val canComplete = canComplete(totalTrips, mid, time)
            if (canComplete) {
                upper = mid
            } else {
                lower = mid + 1
            }
        }
        return lower
    }

    private fun guessUpperBound(tripTimeEachBus: IntArray, totalTrips: Int): Long {
        val minTime = tripTimeEachBus.min()!!
        return totalTrips.toLong() * minTime
    }

    private fun canComplete(targetTrips: Int, totalTime: Long, tripTimeEachBus: IntArray): Boolean {
        var totalTrips = 0L
        for (tripTime in tripTimeEachBus) {
            totalTrips += totalTime / tripTime
            if (totalTrips >= targetTrips) {
                return true
            }
        }
        return false
    }
}