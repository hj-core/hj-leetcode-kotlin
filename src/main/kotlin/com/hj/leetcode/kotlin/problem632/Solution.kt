package com.hj.leetcode.kotlin.problem632

/**
 * LeetCode page: [632. Smallest Range Covering Elements from K Lists](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the flattened length of nums.
     */
    fun smallestRange(nums: List<List<Int>>): IntArray {
        // Attach row information to each value and sort them by value
        val sortedValues =
            nums.flatMapIndexedTo(mutableListOf()) { row, list ->
                list.asSequence().map { value -> RowValue(row, value) }
            }
        sortedValues.sortBy { rowValue -> rowValue.value }
        // Use two-pointer to determine the smallest range
        var result = intArrayOf(sortedValues[0].value, sortedValues.last().value)
        var left = 0
        val counter = mutableMapOf<Int, Int>() // row to number of values in range

        for (right in sortedValues.indices) {
            counter.compute(sortedValues[right].row) { _, count -> (count ?: 0) + 1 }

            if (counter.size == nums.size) {
                while (checkNotNull(counter[sortedValues[left].row]) > 1) {
                    counter.compute(sortedValues[left].row) { _, count -> checkNotNull(count) - 1 }
                    left += 1
                }
                val range = intArrayOf(sortedValues[left].value, sortedValues[right].value)
                if (isSmaller(range, result)) {
                    result = range
                }
            }
        }
        return result
    }

    data class RowValue(
        val row: Int,
        val value: Int,
    )

    private fun isSmaller(
        range: IntArray,
        compareTo: IntArray,
    ): Boolean {
        val widthDiff = (range[1] - range[0]) - (compareTo[1] - compareTo[0])
        return when {
            widthDiff < 0 -> true
            widthDiff > 0 -> false
            else -> range[0] < compareTo[0]
        }
    }
}
