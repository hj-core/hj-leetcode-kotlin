package com.hj.leetcode.kotlin.problem435

/**
 * LeetCode page: [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of intervals;
     */
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        val sortedInterval = intervals.sortedBy { it[0] }
        var result = 0
        var holdingInterval = sortedInterval[0]

        for (index in 1..<sortedInterval.size) {
            val newInterval = sortedInterval[index]
            val noOverlap = holdingInterval[1] <= newInterval[0]
            if (noOverlap) {
                holdingInterval = newInterval
                continue
            }

            result++
            if (newInterval[1] < holdingInterval[1]) {
                holdingInterval = newInterval
            }
        }
        return result
    }
}