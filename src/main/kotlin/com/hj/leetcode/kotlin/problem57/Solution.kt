package com.hj.leetcode.kotlin.problem57

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [57. Insert Interval](https://leetcode.com/problems/insert-interval/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of intervals;
     */
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty()) {
            return arrayOf(newInterval)
        }
        if (newInterval[1] < intervals[0][0]) {
            return arrayOf(newInterval) + intervals
        }
        if (newInterval[0] > intervals.last()[1]) {
            return intervals + arrayOf(newInterval)
        }

        val reversed = intArrayOf(newInterval[1], newInterval[0])
        // The insertion index of newInterval[0] among intervals.map { it[1] }
        val iStart = intervals
            .binarySearch(reversed, compareBy { it[1] })
            .let { if (it < 0) -(it + 1) else it }
        // The insertion index of newInterval[1] among intervals.map { it[0] }
        val iEnd = intervals
            .binarySearch(reversed, compareBy { it[0] })
            .let { if (it < 0) -(it + 1) else it }

        return when {
            iEnd == intervals.size -> {
                val result = Array(iStart + 1) { intArrayOf() }

                for (i in 0..<iStart) {
                    result[i] = intervals[i]
                }
                val mergedStart = min(newInterval[0], intervals[iStart][0])
                val mergedEnd = max(newInterval[1], intervals.last()[1])
                result[iStart] = intArrayOf(mergedStart, mergedEnd)
                result
            }

            newInterval[1] < intervals[iEnd][0] -> {
                val result = Array(iStart + 1 + intervals.size - iEnd) { intArrayOf() }

                for (i in 0..<iStart) {
                    result[i] = intervals[i]
                }
                val mergedStart = min(newInterval[0], intervals[iStart][0])
                val mergedEnd = max(newInterval[1], intervals[iEnd - 1][1])
                result[iStart] = intArrayOf(mergedStart, mergedEnd)
                for (i in iStart + 1..<result.size) {
                    result[i] = intervals[iEnd + i - (iStart + 1)]
                }
                result
            }

            else -> {
                val result = Array(iStart + intervals.size - iEnd) { intArrayOf() }

                for (i in 0..<iStart) {
                    result[i] = intervals[i]
                }
                val mergedStart = min(newInterval[0], intervals[iStart][0])
                result[iStart] = intArrayOf(mergedStart, intervals[iEnd][1])
                for (i in iStart + 1..<result.size) {
                    result[i] = intervals[iEnd + i - iStart]
                }
                result
            }
        }
    }
}