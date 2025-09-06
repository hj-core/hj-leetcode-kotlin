package com.hj.leetcode.kotlin.problem3495

/**
 * LeetCode page: [3495. Minimum Operations to Make Array Elements Zero](https://leetcode.com/problems/minimum-operations-to-make-array-elements-zero/);
 */
class Solution {
    // Complexity:
    // Time O(N+LogM) and Space O(1) where N is the length
    // of queries and M is the product of all query rights.
    fun minOperations(queries: Array<IntArray>): Long =
        queries.fold(0L) { acc, q ->
            acc + solveQuery(q)
        }

    private fun solveQuery(query: IntArray): Long {
        val right = query[1]
        val xLeft = query[0] - 1
        val diff = right - xLeft
        var totalDivs = 0L
        var exp4 = 1

        while (exp4 <= right) {
            totalDivs += if (exp4 <= xLeft) diff else right - exp4 + 1
            exp4 = exp4 shl 2
        }
        return (totalDivs + 1) / 2
    }
}
