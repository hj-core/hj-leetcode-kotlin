package com.hj.leetcode.kotlin.problem57

/**
 * LeetCode page: [57. Insert Interval](https://leetcode.com/problems/insert-interval/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of intervals;
     */
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty()) return arrayOf(newInterval)
        val indexMergeStart = findIndexAtMergeStart(intervals, newInterval)
        val indexMergeEnded = findIndexAtMergeEnded(intervals, newInterval)
        return buildIntervalsAfterInsertion(intervals, newInterval, indexMergeStart, indexMergeEnded)
    }

    private fun findIndexAtMergeStart(intervals: Array<IntArray>, newInterval: IntArray): Int {
        val (newStart, newEnd) = newInterval

        /* BinarySearch start of newInterval against the end of existing intervals such that
         * end_i-1 < start_new <= end_i
         */
        val i = intervals
            .binarySearchBy(newStart) { it[1] }
            .let { if (it < 0) -(it + 1) else it }

        return when (i) {
            0 -> if (newEnd < intervals[0][0]) -1 else 0
            else -> i
        }
    }

    private fun <T, K : Comparable<K>> Array<T>.binarySearchBy(
        key: K,
        fromIndex: Int = 0,
        untilIndex: Int = size,
        selector: (T) -> K
    ): Int {

        var left = fromIndex
        var right = untilIndex - 1
        while (left <= right) {
            val midIndex = (left + right) ushr 1
            val midValue = this[midIndex]
            val midKey = selector(midValue)
            when {
                key > midKey -> left = midIndex + 1
                key < midKey -> right = midIndex - 1
                else -> return midIndex
            }
        }
        return -left - 1
    }

    private fun findIndexAtMergeEnded(intervals: Array<IntArray>, newInterval: IntArray): Int {
        val (newStart, newEnd) = newInterval

        /* BinarySearch end of newInterval against the start of existing intervals such that
         * start_i-1 < newEnd <= start_i
         */
        val i = intervals
            .binarySearchBy(newEnd) { it[0] }
            .let { if (it < 0) -(it + 1) else it }

        return when (i) {
            0 -> if (newEnd < intervals[0][0]) -1 else 0

            intervals.size ->
                if (newStart > intervals.last()[1]) intervals.size else intervals.size - 1

            else -> if (intervals[i][0] == newEnd) i else i - 1
        }
    }

    private fun buildIntervalsAfterInsertion(
        intervals: Array<IntArray>,
        newInterval: IntArray,
        indexMergeStart: Int,
        indexMergeEnded: Int
    ): Array<IntArray> {

        if (intervals.isEmpty()) return arrayOf(newInterval)
        val revisedIntervals = mutableListOf<IntArray>()

        for (index in 0 until indexMergeStart) {
            revisedIntervals.add(intervals[index])
        }

        revisedIntervals.add(
            getMergedInterval(intervals, newInterval, indexMergeStart, indexMergeEnded)
        )

        for (index in indexMergeEnded + 1 until intervals.size) {
            revisedIntervals.add(intervals[index])
        }
        return revisedIntervals.toTypedArray()
    }

    private fun getMergedInterval(
        intervals: Array<IntArray>,
        newInterval: IntArray,
        indexMergeStart: Int,
        indexMergeEnded: Int
    ): IntArray {

        val (newStart, newEnd) = newInterval

        val mergedStart = when (indexMergeStart) {
            -1, intervals.size -> newStart
            else -> minOf(newStart, intervals[indexMergeStart][0])
        }

        val mergedEnd = when (indexMergeEnded) {
            -1, intervals.size -> newEnd
            else -> maxOf(newEnd, intervals[indexMergeEnded][1])
        }

        return intArrayOf(mergedStart, mergedEnd)
    }
}