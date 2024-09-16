package com.hj.leetcode.kotlin.problem539

import kotlin.math.min

/**
 * LeetCode page: [539. Minimum Time Difference](https://leetcode.com/problems/minimum-time-difference/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of timePoints.
     */
    fun findMinDifference(timePoints: List<String>): Int {
        val countMinuteOfDay = IntArray(24 * 60) // Indexed by minute of day
        for (time in timePoints) {
            val minuteOfDay = minuteOfDay(time)
            countMinuteOfDay[minuteOfDay] += 1
            if (countMinuteOfDay[minuteOfDay] > 1) {
                return 0
            }
        }

        // Find the minimum adjacent time difference,
        // including the wrap around difference between the first and last.
        var result = Int.MAX_VALUE
        val stack = mutableListOf<Int>()
        for ((minuteOfDay, count) in countMinuteOfDay.withIndex()) {
            if (count == 0) {
                continue
            }
            if (stack.isNotEmpty()) {
                result = min(result, minuteOfDay - stack.last())
            }
            if (stack.size == 2) {
                stack.removeLast()
            }
            stack.add(minuteOfDay)
        }
        result = min(result, 24 * 60 - (stack.last() - stack.first()))
        return result
    }

    private fun minuteOfDay(time: String): Int {
        val (hour, minute) = time.split(":").map { it.toInt() }
        return hour * 60 + minute
    }
}
