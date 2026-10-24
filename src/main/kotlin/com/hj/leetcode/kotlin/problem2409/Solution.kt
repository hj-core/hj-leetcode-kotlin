package com.hj.leetcode.kotlin.problem2409

/**
 * LeetCode page: [2409. Count Days Spent Together](https://leetcode.com/problems/count-days-spent-together/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun countDaysTogether(
        arriveAlice: String,
        leaveAlice: String,
        arriveBob: String,
        leaveBob: String,
    ): Int {
        val a0 = daysOfNonLeapYear(arriveAlice)
        val a1 = daysOfNonLeapYear(leaveAlice)
        val b0 = daysOfNonLeapYear(arriveBob)
        val b1 = daysOfNonLeapYear(leaveBob)
        return (minOf(a1, b1) - maxOf(a0, b0) + 1).coerceAtLeast(0)
    }

    private fun daysOfNonLeapYear(date: String): Int {
        val month = date.substring(0, 2).toInt()
        val day = date.substring(3, 5).toInt()
        if (month == 1) {
            return day
        }

        val daysAtMonthEnds = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        for (i in 2..12) {
            daysAtMonthEnds[i] += daysAtMonthEnds[i - 1]
        }

        return daysAtMonthEnds[month - 1] + day
    }
}
