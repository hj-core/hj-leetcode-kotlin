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

        for ((start, end, value) in sortedEvents) {
            if (start <= sortedEvents[0][1]) {
                continue
            }
            // Binary search the last index of sortedEvents
            // where sortedEvents[index].end < currEvent.start
            var low = 0
            var high = sortedEvents.size - 1
            while (low <= high) {
                val mid = low + (high - low) / 2
                if (sortedEvents[mid][1] < start) {
                    low = mid + 1
                } else {
                    high = mid - 1
                }
            }
            result = max(result, value + prefixMaxValues[high])
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
}
