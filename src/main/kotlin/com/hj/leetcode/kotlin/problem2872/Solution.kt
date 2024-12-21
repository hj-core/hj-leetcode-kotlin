package com.hj.leetcode.kotlin.problem2872

/**
 * LeetCode page: [2872. Maximum Number of K-Divisible Components](https://leetcode.com/problems/maximum-number-of-k-divisible-components/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(n) where M is the size of edges.
     */
    fun maxKDivisibleComponents(
        n: Int,
        edges: Array<IntArray>,
        values: IntArray,
        k: Int,
    ): Int {
        val adjacencyList = adjacencyList(edges)
        return dfs(root(adjacencyList), -1, adjacencyList, values, k)[1]
    }

    private fun adjacencyList(edges: Array<IntArray>): Map<Int, List<Int>> {
        val result = mutableMapOf<Int, MutableList<Int>>()
        for ((u, v) in edges) {
            result.computeIfAbsent(u) { mutableListOf() }.add(v)
            result.computeIfAbsent(v) { mutableListOf() }.add(u)
        }
        return result
    }

    // Return the (sumPendingNodes, validSplits).
    private fun dfs(
        node: Int,
        parentNode: Int,
        adjacencyList: Map<Int, List<Int>>,
        values: IntArray,
        k: Int,
    ): IntArray {
        val result = intArrayOf(values[node] % k, 0)
        for (child in adjacencyList[node] ?: emptyList()) {
            if (child == parentNode) {
                continue
            }
            val subResult = dfs(child, node, adjacencyList, values, k)
            result[0] = (result[0] + subResult[0]) % k
            result[1] += subResult[1]
        }
        if (result[0] == 0) {
            result[1] += 1
        }
        return result
    }

    private fun root(adjacencyList: Map<Int, List<Int>>): Int {
        if (adjacencyList.isEmpty()) {
            return 0
        }
        return adjacencyList.entries.first { (_, list) -> list.size <= 2 }.key
    }
}
