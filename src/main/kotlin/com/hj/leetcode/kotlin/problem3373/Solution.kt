package com.hj.leetcode.kotlin.problem3373

/**
 * LeetCode page: [3373. Maximize the Number of Target Nodes After Connecting Trees II](https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(N+M) where N and M are the length of edges1 and
    // edges2 respectively.
    fun maxTargetNodes(
        edges1: Array<IntArray>,
        edges2: Array<IntArray>,
    ): IntArray {
        val adjacencyList1 = computeAdjacencyList(edges1.size + 1, edges1)
        var sizeGroup1A = 0
        var sizeGroup1B = 0
        val result = IntArray(adjacencyList1.size)

        dfs(0, -1, 0, adjacencyList1) { node, depth ->
            if (depth and 1 == 0) {
                sizeGroup1A++
                // result[node] = 0
            } else {
                sizeGroup1B++
                result[node] = 1
            }
        }

        val gain = computeMaxConnectionGain(edges2)
        val answerGroupA = sizeGroup1A + gain
        val answerGroupB = sizeGroup1B + gain
        for (node in result.indices) {
            result[node] = if (result[node] and 1 == 0) answerGroupA else answerGroupB
        }
        return result
    }

    private fun computeAdjacencyList(
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

    private fun dfs(
        node: Int,
        parentNode: Int,
        depth: Int,
        adjacencyList: List<List<Int>>,
        onEachNode: (node: Int, depth: Int) -> Unit,
    ) {
        onEachNode(node, depth)
        for (child in adjacencyList[node]) {
            if (child != parentNode) {
                dfs(child, node, depth + 1, adjacencyList, onEachNode)
            }
        }
    }

    private fun computeMaxConnectionGain(edges2: Array<IntArray>): Int {
        val adjacencyList2 = computeAdjacencyList(edges2.size + 1, edges2)
        var sizeGroup2A = 0
        var sizeGroup2B = 0
        dfs(0, -1, 0, adjacencyList2) { node, depth ->
            if (depth and 1 == 0) {
                sizeGroup2A++
            } else {
                sizeGroup2B++
            }
        }
        return maxOf(sizeGroup2A, sizeGroup2B)
    }
}
