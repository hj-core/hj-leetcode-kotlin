package com.hj.leetcode.kotlin.problem435

/**
 * LeetCode page: [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of intervals;
     */
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        val sortedIntervals = intervals.sortedBy { it[0] }
        var result = 0
        var currentIndex = 0
        var competitorIndex = 1

        while (competitorIndex < sortedIntervals.size) {
            val currentEnd = sortedIntervals[currentIndex][1]
            val (competitorStart, competitorEnd) = sortedIntervals[competitorIndex]
            when {
                currentEnd <= competitorStart -> {
                    currentIndex = competitorIndex
                    competitorIndex = currentIndex + 1
                }

                currentEnd < competitorEnd -> {
                    result++
                    competitorIndex++
                }

                else -> {
                    result++
                    currentIndex = competitorIndex
                    competitorIndex = currentIndex + 1
                }
            }
        }
        return result
    }
}