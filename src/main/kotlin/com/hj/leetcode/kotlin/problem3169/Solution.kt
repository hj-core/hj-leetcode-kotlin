package com.hj.leetcode.kotlin.problem3169

/**
 * LeetCode page: [3169. Count Days Without Meetings](https://leetcode.com/problems/count-days-without-meetings/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of meetings.
    fun countDays(
        days: Int,
        meetings: Array<IntArray>,
    ): Int {
        val sortedMeetings = meetings.sortedBy { (start, end) -> start }
        var currEnd = 0
        var result = 0

        for ((start, end) in sortedMeetings) {
            result += maxOf(0, start - currEnd - 1)
            currEnd = maxOf(currEnd, end)
        }
        result += maxOf(0, days - currEnd)
        return result
    }
}
