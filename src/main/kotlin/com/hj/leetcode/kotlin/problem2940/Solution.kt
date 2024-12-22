package com.hj.leetcode.kotlin.problem2940

/**
 * LeetCode page: [2940. Find Building Where Alice and Bob Can Meet](https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/);
 */
class Solution {
    /* Complexity:
     * Time O(MLogM+MLogN) and Space O(M+N)
     * where N and M are the lengths of heights and queries, respectively.
     */
    fun leftmostBuildingQueries(
        heights: IntArray,
        queries: Array<IntArray>,
    ): IntArray {
        val sortedQueryIndices = queries.indices.sortedByDescending { queries[it].max() }
        val result = IntArray(queries.size)

        // A stack stores the indices of heights.
        // From top to bottom,
        // the indices are strictly increasing as well as their heights.
        val monoStack = mutableListOf<Int>()
        val reversedView = monoStack.asReversed()
        var lastUpdate = heights.size

        for (i in sortedQueryIndices) {
            val (left, right) = queries[i].min() to queries[i].max()

            if (left == right || heights[left] < heights[right]) {
                result[i] = right
                continue
            }
            updateMonoStack(right, monoStack, lastUpdate, heights)
            lastUpdate = right

            result[i] =
                if (monoStack.isEmpty() || heights[monoStack.first()] <= heights[left]) {
                    -1
                } else {
                    reversedView
                        .binarySearch { j -> heights[j] - heights[left] }
                        .let { if (it < 0) -(it + 1) else it + 1 }
                        .let { reversedView[it] }
                }
        }
        return result
    }

    private fun updateMonoStack(
        newRight: Int,
        monoStack: MutableList<Int>,
        lastUpdate: Int,
        heights: IntArray,
    ) {
        for (j in lastUpdate - 1 downTo newRight) {
            if (0 < j && heights[j] <= heights[j - 1]) {
                continue
            }
            while (monoStack.isNotEmpty() && heights[monoStack.last()] <= heights[j]) {
                monoStack.removeLast()
            }
            monoStack.add(j)
        }
    }
}
