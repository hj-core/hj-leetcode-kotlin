package com.hj.leetcode.kotlin.problem2872

/**
 * LeetCode page: [2872. Maximum Number of K-Divisible Components](https://leetcode.com/problems/maximum-number-of-k-divisible-components/);
 */
class Solution {
    // Complexity:
    // Time O(n) and Space O(n).
    fun maxKDivisibleComponents(
        n: Int,
        edges: Array<IntArray>,
        values: IntArray,
        k: Int,
    ): Int = dfs(0, -1, adjacencyList(n, edges), values, k)[1]

    // Return the (sumPendingNodes, validSplits).
    private fun dfs(
        node: Int,
        parentNode: Int,
        adjacencyList: List<List<Int>>,
        values: IntArray,
        k: Int,
    ): IntArray {
        val result = intArrayOf(values[node] % k, 0)
        for (child in adjacencyList[node]) {
            if (child != parentNode) {
                val subResult =
                    dfs(child, node, adjacencyList, values, k)
                result[0] = (result[0] + subResult[0]) % k
                result[1] += subResult[1]
            }
        }
        if (result[0] == 0) {
            result[1] += 1
        }
        return result
    }

    private fun adjacencyList(
        n: Int,
        edges: Array<IntArray>,
    ): List<List<Int>> {
        val result = List(n) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            result[u].add(v)
            result[v].add(u)
        }
        return result
    }
}
