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

        /* The idea is to form the resulting array using a greedy approach, where the value at
         * each index i is calculated by subtracting the distance between index i and the given
         * index from the value at the given index, with the resulting value constrained to be
         * at least one.
         *
         * Since the sum of the resulting array is directly proportional to the value at the
         * given index, we can perform a binary search to find the maximum value at the given
         * index.
         */

        var minValue = 1
        var maxValue = maxSum
        while (minValue <= maxValue) {
            val guessValue = (minValue + maxValue) ushr 1
            val sum = sumGreedyArray(n, index, guessValue)
            when {
                sum < maxSum -> minValue = guessValue + 1
                sum > maxSum -> maxValue = guessValue - 1
                else -> return guessValue
            }
        }
        return maxValue
    }

    private fun sumGreedyArray(n: Int, index: Int, indexValue: Int): Long {
        return (sumGreedyArrayUpToIndex(index, indexValue)
                + sumGreedyArrayFromIndex(n, index, indexValue)
                - indexValue)
    }

    private fun sumGreedyArrayUpToIndex(index: Int, indexValue: Int): Long {
        require(index >= 0)
        require(indexValue >= 1)

        // First value of the resulting array if it can be zero or negative
        val looseFirst = indexValue - index

        return if (looseFirst >= 1) {
            (looseFirst.toLong() + indexValue) * (index + 1) / 2
        } else {
            (1L + indexValue) * indexValue / 2 - looseFirst + 1
        }
    }

    private fun sumGreedyArrayFromIndex(n: Int, index: Int, indexValue: Int): Long {
        require(index in 0 until n)
        require(indexValue >= 1)

        // Last value of the resulting array if it can be zero or negative
        val looseLast = indexValue + index - (n - 1)

        return if (looseLast >= 1) {
            (indexValue.toLong() + looseLast) * (n - index) / 2
        } else {
            (1L + indexValue) * indexValue / 2 - looseLast + 1
        }
    }
}