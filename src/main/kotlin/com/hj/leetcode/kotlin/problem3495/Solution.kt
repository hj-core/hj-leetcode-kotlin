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
        var newLeft = query[0]
        var totalDivs = 0L
        var exp4 = 1
        var unitDivs = 0L

        while (exp4 <= right) {
            exp4 = exp4 shl 2
            unitDivs++

            if (newLeft < exp4) {
                val count = minOf(right - newLeft + 1, exp4 - newLeft)
                totalDivs += count * unitDivs
                newLeft = exp4
            }
        }
        return (totalDivs + 1) / 2
    }
}
