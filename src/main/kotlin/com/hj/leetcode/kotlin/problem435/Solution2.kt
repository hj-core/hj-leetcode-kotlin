package com.hj.leetcode.kotlin.problem435

/**
 * LeetCode page: [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/);
 */
class Solution2 {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of intervals;
     */
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        val sortedInterval = intervals.sortedBy { it[1] }
        var result = 0
        var earliestStart = sortedInterval[0][0]

        for ((start, end) in sortedInterval) {
            val noOverlap = earliestStart <= start
            if (noOverlap) {
                earliestStart = end
            } else {
                result++
            }
        }
        return result
    }
}