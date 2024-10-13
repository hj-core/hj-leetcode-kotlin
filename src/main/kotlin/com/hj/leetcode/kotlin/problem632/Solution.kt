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
        val sorted =
            nums.flatMapIndexedTo(mutableListOf()) { row, list ->
                list.asSequence().map { value -> RowValue(row, value) }
            }
        sorted.sortBy { rowValue -> rowValue.value }

        // Use two-pointer to determine the smallest range
        var result = intArrayOf(sorted[0].value, sorted.last().value)
        var left = 0
        val count = mutableMapOf<Int, Int>()

        for (right in sorted.indices) {
            count.compute(sorted[right].row) { _, v -> 1 + (v ?: 0) }

            if (count.size == nums.size) {
                while (checkNotNull(count[sorted[left].row]) > 1) {
                    count.compute(sorted[left].row) { _, v -> checkNotNull(v) - 1 }
                    left += 1
                }

                val range = intArrayOf(sorted[left].value, sorted[right].value)
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
