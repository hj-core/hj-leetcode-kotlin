package com.hj.leetcode.kotlin.problem2224

/**
 * LeetCode page: [2224. Minimum Number of Operations to Convert Time](https://leetcode.com/problems/minimum-number-of-operations-to-convert-time/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun convertTime(current: String, correct: String): Int {
        val allowedIncrementInMinutesSortedDescending = listOf(60, 15, 5, 1)
        var remainingTimeDiffInMinutes = getTimeDifferentInMinutes(current, correct)
        var minNumberOfOperation = 0

        for (increment in allowedIncrementInMinutesSortedDescending) {
            val maxNumberOfCurrentOperation = remainingTimeDiffInMinutes / increment
            minNumberOfOperation += maxNumberOfCurrentOperation
            remainingTimeDiffInMinutes -= maxNumberOfCurrentOperation * increment
        }

        check(remainingTimeDiffInMinutes == 0)
        return minNumberOfOperation
    }

    private fun getTimeDifferentInMinutes(from: String, to: String): Int {
        val (hourOfFrom, minutesOfFrom) = getHourAndMinutes(from)
        val (hourOfTo, minutesOfTo) = getHourAndMinutes(to)
        return (hourOfTo - hourOfFrom) * 60 + (minutesOfTo - minutesOfFrom)
    }

    // Return Pair(hour, minutes) from a 24-hr time string with format "HH:MM";
    private fun getHourAndMinutes(time: String): Pair<Int, Int> {
        val hour = time.slice(0..1).toInt()
        val minutes = time.slice(3..4).toInt()
        return Pair(hour, minutes)
    }
}