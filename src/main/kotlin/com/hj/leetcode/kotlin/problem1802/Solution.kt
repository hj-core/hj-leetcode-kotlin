package com.hj.leetcode.kotlin.problem1802

/**
 * LeetCode page: [1802. Maximum Value at a Given Index in a Bounded Array](https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/);
 */
class Solution {
    /* Complexity:
     * Time O(Log(maxSum)) and Space O(1);
     */
    fun maxValue(n: Int, index: Int, maxSum: Int): Int {
        require(n >= 1)
        require(index in 0 until n)
        require(maxSum >= n)

        /* To maximize the value at the given index, we use a greedy approach where the value at
         * each index i in the resulting array is calculated by subtracting the distance between
         * the given index and i from the index value, with the resulting value constrained to be
         * at least 1.
         */

        // Use binary search to find the max value at index
        var minValue = 1
        var maxValue = maxSum
        while (minValue <= maxValue) {
            val midValue = (minValue + maxValue) ushr 1
            val sum = sumGreedy(n, index, midValue)
            when {
                sum < maxSum -> minValue = midValue + 1
                sum > maxSum -> maxValue = midValue - 1
                else -> return midValue
            }
        }
        return maxValue
    }

    private fun sumGreedy(n: Int, index: Int, indexValue: Int): Long {
        return (sumGreedyUpToIndex(index, indexValue)
                + sumGreedyFromIndex(n, index, indexValue)
                - indexValue)
    }

    private fun sumGreedyUpToIndex(index: Int, indexValue: Int): Long {
        require(index >= 0)
        require(indexValue >= 1)

        val looseFirst = indexValue - index // value if it can be zero or negative
        return if (looseFirst >= 1) {
            (looseFirst.toLong() + indexValue) * (index + 1) / 2
        } else {
            (1L + indexValue) * indexValue / 2 - looseFirst + 1
        }
    }

    private fun sumGreedyFromIndex(n: Int, index: Int, indexValue: Int): Long {
        require(index in 0 until n)
        require(indexValue >= 1)

        val looseLast = indexValue + index - (n - 1) // value if it can be zero or negative
        return if (looseLast >= 1) {
            (indexValue.toLong() + looseLast) * (n - index) / 2
        } else {
            (1L + indexValue) * indexValue / 2 - looseLast + 1
        }
    }
}