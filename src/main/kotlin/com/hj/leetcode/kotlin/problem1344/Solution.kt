package com.hj.leetcode.kotlin.problem1344

import kotlin.math.abs

/**
 * LeetCode page: [1344. Angle Between Hands of a Clock](https://leetcode.com/problems/angle-between-hands-of-a-clock/);
 */
class Solution {
    private val degreesPerHour = 360.0 / 12.0
    private val degreesPerMinute = 360.0 / 60.0

    // Complexity:
    // Time O(1) and Space O(1).
    fun angleClock(
        hour: Int,
        minutes: Int,
    ): Double {
        val hrDegrees = computeHourDegrees(hour, minutes)
        val minuteDegrees = computeMinuteDegrees(minutes)
        val angle = abs(hrDegrees - minuteDegrees)
        return minOf(angle, 360.0 - angle)
    }

    private fun computeHourDegrees(
        hour: Int,
        minutes: Int,
    ): Double {
        val hour = if (hour == 12) 0 else hour
        return (hour + minutes / 60.0) * degreesPerHour
    }

    private fun computeMinuteDegrees(minute: Int): Double = minute * degreesPerMinute
}
