package com.hj.leetcode.kotlin.problem2054

import kotlin.math.max

/**
 * LeetCode page: [2054. Two Best Non-Overlapping Events](https://leetcode.com/problems/two-best-non-overlapping-events/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the length of events.
     */
    fun maxTwoEvents(events: Array<IntArray>): Int {
        val sortedEvents = events.sortedBy { (start, end, value) -> end }
        val prefixMaxValues = prefixMaxValues(sortedEvents)
        var result = prefixMaxValues.last()

        for ((start, _, value) in sortedEvents) {
            if (start <= sortedEvents[0][1]) {
                continue
            }
            val leftSize = sortedEvents.partitionPoint { (_, end, _) -> end < start }
            result = max(result, value + prefixMaxValues[leftSize - 1])
        }
        return result
    }

    private fun prefixMaxValues(sortedEvents: List<IntArray>): IntArray {
        // event = [start, end, value]
        val result = IntArray(sortedEvents.size)
        result[0] = sortedEvents[0][2]
        for (i in 1..<result.size) {
            result[i] = max(sortedEvents[i][2], result[i - 1])
        }
        return result
    }

    /**
     * Require the original list can be partitioned into left and right according to [isLeft].
     *
     * Return the size of the left partition.
     */
    private fun <T> List<T>.partitionPoint(isLeft: (T) -> Boolean): Int {
        var low = 0
        var high = lastIndex

        while (low <= high) {
            val mid = low + (high - low) / 2
            if (isLeft(this[mid])) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }
        return low
    }
}
