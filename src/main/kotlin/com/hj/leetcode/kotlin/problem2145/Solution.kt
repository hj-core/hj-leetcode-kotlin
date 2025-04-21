package com.hj.leetcode.kotlin.problem2145

/**
 * LeetCode page: [2145. Count the Hidden Sequences](https://leetcode.com/problems/count-the-hidden-sequences/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of differences.
    fun numberOfArrays(
        differences: IntArray,
        lower: Int,
        upper: Int,
    ): Int {
        var diffToFirst = 0L
        var maxDiffToFirst = differences[0].toLong()
        var minDiffToFirst = differences[0].toLong()

        for (diff in differences) {
            diffToFirst += diff
            maxDiffToFirst = maxOf(maxDiffToFirst, diffToFirst)
            minDiffToFirst = minOf(minDiffToFirst, diffToFirst)
        }

        val maxFirst = minOf(upper.toLong(), upper.toLong() - maxDiffToFirst)
        val minFirst = maxOf(lower.toLong(), lower.toLong() - minDiffToFirst)
        return maxOf(0, (maxFirst - minFirst + 1).toInt())
    }
}
