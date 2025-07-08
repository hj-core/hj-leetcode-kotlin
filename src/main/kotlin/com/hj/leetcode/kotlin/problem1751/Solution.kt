package com.hj.leetcode.kotlin.problem1751

/**
 * LeetCode page: [1751. Maximum Number of Events That Can Be Attended II](https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/);
 */
class Solution {
    // Complexity:
    // Time O(kN+NLogN) and Space O(kN) where N is the length of
    // events.
    fun maxValue(
        events: Array<IntArray>,
        k: Int,
    ): Int {
        val sortedEvents = events.sortedBy { it[0] }

        // dp[i][t]:= the maximum value if we can pick at most t
        // events from the (sorted) events[i:].
        val dp = Array(events.size + 1) { IntArray(k + 1) }

        for (i in sortedEvents.indices.reversed()) {
            val j = countStarted(sortedEvents, i)
            for (t in 1..k) {
                dp[i][t] = maxOf(dp[i + 1][t], sortedEvents[i][2] + dp[j][t - 1])
            }
        }
        return dp[0][k]
    }

    // Returns the number of events that have started before
    // events[i] ends. The events should be in non-descending
    // order of starting times.
    private fun countStarted(
        events: List<IntArray>,
        i: Int,
    ): Int {
        // Binary search for the result, which is in the range
        // [left, right].
        var left = i + 1
        var right = events.size

        while (left < right) {
            val mid = left + (right - left) / 2
            if (events[mid][0] <= events[i][1]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}
