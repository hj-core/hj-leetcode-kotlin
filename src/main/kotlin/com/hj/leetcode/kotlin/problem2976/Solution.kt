package com.hj.leetcode.kotlin.problem2976

import kotlin.math.min

/**
 * LeetCode page: [2976. Minimum Cost to Convert String I](https://leetcode.com/problems/minimum-cost-to-convert-string-i/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N+C^3) and Space O(C^2) where M is the size of source and target,
     * N is the size of original, changed and cost, and C is the size of charset.
     */
    fun minimumCost(
        source: String,
        target: String,
        original: CharArray,
        changed: CharArray,
        cost: IntArray,
    ): Long {
        val minCostMatrix = floydWarshall(original, changed, cost)
        var result = 0L

        for (i in source.indices) {
            val from = source[i] - 'a'
            val to = target[i] - 'a'
            if (minCostMatrix[from][to] == Int.MAX_VALUE) {
                return -1
            }
            result += minCostMatrix[from][to]
        }
        return result
    }

    private fun floydWarshall(
        original: CharArray,
        changed: CharArray,
        cost: IntArray,
    ): Array<IntArray> {
        val result = getAdjacencyMatrix(original, changed, cost)
        for (k in 0..<26) {
            for (i in 0..<26) {
                for (j in 0..<26) {
                    if (result[i][k] == Int.MAX_VALUE || result[k][j] == Int.MAX_VALUE) {
                        continue
                    }
                    result[i][j] = min(result[i][j], result[i][k] + result[k][j])
                }
            }
        }
        return result
    }

    private fun getAdjacencyMatrix(
        original: CharArray,
        changed: CharArray,
        cost: IntArray,
    ): Array<IntArray> {
        val result = Array(26) { IntArray(26) { Int.MAX_VALUE } }
        // Set cost for self loop
        for (i in 0..<26) {
            result[i][i] = 0
        }
        // Set cost for edges
        for (i in original.indices) {
            val from = original[i] - 'a'
            val to = changed[i] - 'a'
            result[from][to] = min(result[from][to], cost[i])
        }
        return result
    }
}