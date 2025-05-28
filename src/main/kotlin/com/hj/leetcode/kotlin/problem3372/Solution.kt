package com.hj.leetcode.kotlin.problem3372

/**
 * LeetCode page: [3372. Maximize the Number of Target Nodes After Connecting Trees I](https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/);
 */
class Solution {
    // Complexity:
    // Time O(N^2+M^2) and Space O(N+M) where N and M are the length of
    // edges1 and edges2, respectively.
    fun maxTargetNodes(
        edges1: Array<IntArray>,
        edges2: Array<IntArray>,
        k: Int,
    ): IntArray {
        if (k == 0) {
            return IntArray(edges1.size + 1) { 1 }
        }

        val result = countTargetNodes(edges1, k)
        val inc = if (k == 1) 1 else countTargetNodes(edges2, k - 1).max()
        for (i in result.indices) {
            result[i] += inc
        }
        return result
    }

    // `countTargetNodes` returns the number of reachable nodes within k
    // edges for each node of the tree.
    private fun countTargetNodes(
        edges: Array<IntArray>,
        k: Int,
    ): IntArray {
        val adjacencyList = computeAdjacencyList(edges)
        val result = IntArray(edges.size + 1)
        for (node in 0..edges.size) {
            result[node] = dfs(node, -1, k, adjacencyList)
        }
        return result
    }

    private fun computeAdjacencyList(edges: Array<IntArray>): List<List<Int>> {
        val result = List(edges.size + 1) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            result[u].add(v)
            result[v].add(u)
        }
        return result
    }

    // `dfs` returns the number of nodes that can be reached by the node
    // within k edges.
    private fun dfs(
        node: Int,
        parentNode: Int,
        k: Int,
        adjacencyList: List<List<Int>>,
    ): Int {
        if (k == 0) {
            return 1
        }

        var result = 1
        for (child in adjacencyList[node]) {
            if (child == parentNode) {
                continue
            }
            result += dfs(child, node, k - 1, adjacencyList)
        }
        return result
    }
}
