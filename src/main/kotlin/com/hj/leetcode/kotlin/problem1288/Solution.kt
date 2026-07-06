package com.hj.leetcode.kotlin.problem1288

/**
 * LeetCode page: [1288. Remove Covered Intervals](https://leetcode.com/problems/remove-covered-intervals/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of intervals.
    fun removeCoveredIntervals(intervals: Array<IntArray>): Int {
        val sortedIntervals =
            intervals.sortedArrayWith(
                compareBy({ it[0] }, { -it[1] }),
            )

        var count = 0
        var rCovered = sortedIntervals[0][0]
        for ((_, r) in sortedIntervals) {
            if (rCovered < r) {
                count++
                rCovered = r
            }
        }

        return count
    }
}
