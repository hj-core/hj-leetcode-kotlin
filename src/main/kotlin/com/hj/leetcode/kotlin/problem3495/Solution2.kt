package com.hj.leetcode.kotlin.problem3495

/**
 * LeetCode page: [3495. Minimum Operations to Make Array Elements Zero](https://leetcode.com/problems/minimum-operations-to-make-array-elements-zero/);
 */
class Solution2 {
    // Complexity:
    // Time O(N+LogM) and Space O(1) where N is the length
    // of queries and M is the product of all query rights.
    fun minOperations(queries: Array<IntArray>): Long =
        queries.fold(0L) { acc, q ->
            acc + solveQuery(q)
        }

    private fun solveQuery(query: IntArray): Long {
        val divsR = calcTotalDivs(query[1])
        val divsL = calcTotalDivs(query[0] - 1)
        return (divsR - divsL + 1) / 2
    }

    // Returns the total number of divisions required to
    // reduce all number in [1, right] to zeros.
    private fun calcTotalDivs(right: Int): Long {
        val j = (33 - right.countLeadingZeroBits()) shr 1
        // 0x5555_5555 is 4^0 + 4^1 + ... + 4^15 and sumExp4s
        // is 4^0 + 4^1 + ... + 4^(j-1).
        val sumExp4s = 0x5555_5555L shr ((16 - j) shl 1)
        return (right + 1L) * j - sumExp4s
    }
}
