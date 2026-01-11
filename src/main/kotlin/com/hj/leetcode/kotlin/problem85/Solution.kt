package com.hj.leetcode.kotlin.problem85

/**
 * LeetCode page: [85. Maximal Rectangle](https://leetcode.com/problems/maximal-rectangle/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(N), where M and N are the number of rows
    // and columns of the matrix.
    fun maximalRectangle(matrix: Array<CharArray>): Int {
        val histogram = IntArray(matrix[0].size)
        var maximal = 0
        for (row in matrix) {
            for ((i, value) in row.withIndex()) {
                histogram[i] = if (value == '1') histogram[i] + 1 else 0
            }

            maximal =
                maxOf(maximal, maximalRectangleInHistogram(histogram))
        }

        return maximal
    }

    private fun maximalRectangleInHistogram(histogram: IntArray): Int {
        // Store strictly increasing (left, height) pairs, where
        // histogram[..<left] < height and histogram[left..] >= height.
        val monoStack = mutableListOf<Pair<Int, Int>>()
        var maximal = 0

        for ((i, height) in histogram.withIndex()) {
            var left = i
            while (
                monoStack.isNotEmpty() &&
                monoStack.last().second >= height
            ) {
                val (lastLeft, lastHeight) = monoStack.removeLast()
                maximal = maxOf(maximal, (i - lastLeft) * lastHeight)
                left = lastLeft
            }

            monoStack.add(Pair(left, height))
        }

        for ((left, height) in monoStack) {
            maximal = maxOf(maximal, (histogram.size - left) * height)
        }

        return maximal
    }
}
