package com.hj.leetcode.kotlin.problem2054

import kotlin.math.max

/**
 * LeetCode page: [2054. Two Best Non-Overlapping Events](https://leetcode.com/problems/two-best-non-overlapping-events/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of events.
    fun maxTwoEvents(events: Array<IntArray>): Int {
        val sortedEvents =
            events.sortedBy { (start, end, value) -> start }

        val suffixMaxValue = IntArray(events.size + 1)
        for (i in events.indices.reversed()) {
            suffixMaxValue[i] =
                max(suffixMaxValue[i + 1], sortedEvents[i][2])
        }

        var result = 0
        for ((_, end, value) in sortedEvents) {
            val suffix =
                sortedEvents.partitionPoint { (start, _, _) ->
                    start <= end
                }

            result = max(result, value + suffixMaxValue[suffix])
        }

        return result
    }

    /**
     * Returns the size of the first partition.
     *
     * The list is assumed to be comprised of all elements
     * satisfying the predicate and then all elements that do not.
     */
    private fun <T> List<T>.partitionPoint(
        pred: (T) -> Boolean,
    ): Int {
        // The size is within the range [low, high+1]
        var low = 0
        var high = lastIndex

        while (low <= high) {
            val mid = (low + high) ushr 1

            if (pred(this[mid])) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }

        return low
    }
}
