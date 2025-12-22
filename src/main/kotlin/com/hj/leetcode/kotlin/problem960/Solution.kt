package com.hj.leetcode.kotlin.problem960

/**
 * LeetCode page: [960. Delete Columns to Make Sorted III](https://leetcode.com/problems/delete-columns-to-make-sorted-iii/);
 */
class Solution {
    // Complexity:
    // Time O(NM^2) and Space O(NM) where N is the length of strs
    // and M is the length of each word.
    fun minDeletionSize(strs: Array<String>): Int {
        val m = strs[0].length
        val strsT = transpose(strs)
        var result = m - 1

        // dp[i]:= the minimum deletions for strs[:][i:] if we keep
        // the column i.
        val dp = IntArray(m)
        for (first in m - 2 downTo 0) {
            dp[first] = m - first - 1
            var next = first + 1
            while (next - first - 1 < dp[first]) {
                if (next - first - 1 + dp[next] < dp[first] &&
                    isValidNext(strsT[first], strsT[next])
                ) {
                    dp[first] = next - first - 1 + dp[next]
                }
                next++
            }
            result = minOf(result, first + dp[first])
        }

        return result
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
