package com.hj.leetcode.kotlin.problem3440

/**
 * LeetCode page: [3440. Reschedule Meetings for Maximum Free Time II](https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of endTime.
    fun maxFreeTime(
        eventTime: Int,
        startTime: IntArray,
        endTime: IntArray,
    ): Int =
        maxOf(
            maxFreeTimeLeftOnly(eventTime, startTime, endTime),
            maxFreeTimeRightOnly(eventTime, startTime, endTime),
        )

    // Returns the maximum free time if events are only allowed to
    // move to the left.
    private fun maxFreeTimeLeftOnly(
        eventTime: Int,
        startTime: IntArray,
        endTime: IntArray,
    ): Int {
        var result = 0
        var prefixMaxGap = 0

        for (i in endTime.indices) {
            val duration = endTime[i] - startTime[i]
            val leftGap = getIthTimeGap(i, eventTime, startTime, endTime)
            val rightGap = getIthTimeGap(i + 1, eventTime, startTime, endTime)

            var mergedGap = leftGap + rightGap
            if (duration <= prefixMaxGap) {
                mergedGap += duration
            }

            result = maxOf(result, mergedGap)
            prefixMaxGap = maxOf(prefixMaxGap, leftGap)
        }
        return result
    }

    private fun getIthTimeGap(
        i: Int,
        eventTime: Int,
        startTime: IntArray,
        endTime: IntArray,
    ): Int =
        when (i) {
            0 -> startTime[0]
            endTime.size -> eventTime - endTime[i - 1]
            else -> startTime[i] - endTime[i - 1]
        }

    // Returns the maximum free time if events are only allowed to
    // move to the right.
    private fun maxFreeTimeRightOnly(
        eventTime: Int,
        startTime: IntArray,
        endTime: IntArray,
    ): Int {
        var result = 0
        var suffixMaxGap = 0

        for (i in endTime.indices.reversed()) {
            val duration = endTime[i] - startTime[i]
            val leftGap = getIthTimeGap(i, eventTime, startTime, endTime)
            val rightGap = getIthTimeGap(i + 1, eventTime, startTime, endTime)

            var mergedGap = leftGap + rightGap
            if (duration <= suffixMaxGap) {
                mergedGap += duration
            }

            result = maxOf(result, mergedGap)
            suffixMaxGap = maxOf(suffixMaxGap, rightGap)
        }
        return result
    }
}
