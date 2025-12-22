package com.hj.leetcode.kotlin.problem960

import kotlin.math.max

/**
 * LeetCode page: [960. Delete Columns to Make Sorted III](https://leetcode.com/problems/delete-columns-to-make-sorted-iii/);
 */
class Solution2 {
    // Complexity:
    // Time O(NM^2) and Space O(NM) where N is the length of strs
    // and M is the length of each word.
    fun minDeletionSize(strs: Array<String>): Int {
        val m = strs[0].length
        val strsT = transpose(strs)
        var maxKeep = 1

        // dp[i]:= the maximum columns we can keep for strs[:][i:]
        // if we keep the column i.
        val dp = IntArray(m)
        for (first in m - 1 downTo 0) {
            dp[first] = 0 // Temporarily exclude first
            var next = first + 1
            while (next + dp[first] < m) {
                if (dp[first] < dp[next] &&
                    isValidNext(strsT[first], strsT[next])
                ) {
                    dp[first] = dp[next]
                }
                next++
            }
            dp[first] += 1 // Add 1 for first

            maxKeep = maxOf(maxKeep, dp[first])
        }

        return m - maxKeep
    }

    private fun transpose(strs: Array<String>): Array<CharArray> {
        val result = Array(strs[0].length) { CharArray(strs.size) }
        for (row in strs.indices) {
            for (col in strs[row].indices) {
                result[col][row] = strs[row][col]
            }
        }
        return result
    }

    // Returns whether it satisfies the lexicographic order for the
    // first two columns.
    private fun isValidNext(
        first: CharArray,
        next: CharArray,
    ): Boolean = first.indices.all { first[it] <= next[it] }
}
