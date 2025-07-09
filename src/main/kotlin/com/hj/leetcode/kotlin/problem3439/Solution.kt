package com.hj.leetcode.kotlin.problem3439

/**
 * LeetCode page: [3439. Reschedule Meetings for Maximum Free Time I](https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of startTime.
    fun maxFreeTime(
        eventTime: Int,
        k: Int,
        startTime: IntArray,
        endTime: IntArray,
    ): Int {
        // We convert the start and end times into an array that consists of
        // the time gaps preceding each event, including the gap before the
        // first event and after the last event. We then find the maximum subarray
        // sum with length at most k+1.
        val n = endTime.size

        fun getIthTimeGap(i: Int): Int =
            when (i) {
                0 -> startTime[0]
                n -> eventTime - endTime[n - 1]
                else -> startTime[i] - endTime[i - 1]
            }

        val wnwSize = k + 1
        var wndSum = (0..<minOf(wnwSize, n + 1)).sumOf { getIthTimeGap(it) }
        var result = wndSum

        for (i in wnwSize..n) {
            wndSum += getIthTimeGap(i) - getIthTimeGap(i - wnwSize)
            result = maxOf(result, wndSum)
        }
        return result
    }
}
