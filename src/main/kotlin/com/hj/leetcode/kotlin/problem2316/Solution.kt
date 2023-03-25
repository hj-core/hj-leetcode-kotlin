package com.hj.leetcode.kotlin.problem2316

/**
 * LeetCode page: [2316. Count Unreachable Pairs of Nodes in an Undirected Graph](https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/);
 */
class Solution {
    /* Complexity:
     * Time O(n+E) and Space O(n+E) where E is the size of edges;
     */
    fun countPairs(n: Int, edges: Array<IntArray>): Long {
        val nodeAdjacency = nodeToItsAdjacency(edges)
        var remainingNodes = n.toLong()
        var numPairs = 0L
        dfs(n, nodeAdjacency) { componentSize ->
            remainingNodes -= componentSize
            numPairs += componentSize * remainingNodes
        }
        return numPairs
    }

    private fun nodeToItsAdjacency(edges: Array<IntArray>): Map<Int, List<Int>> {
        val adjacent = hashMapOf<Int, MutableList<Int>>()
        for ((u, v) in edges) {
            adjacent.computeIfAbsent(u) { mutableListOf() }.add(v)
            adjacent.computeIfAbsent(v) { mutableListOf() }.add(u)
        }
        return adjacent
    }

    private fun dfs(
        numNodes: Int,
        nodeAdjacency: Map<Int, List<Int>>,
        visited: MutableSet<Int> = hashSetOf(),
        sideEffect: (connectedComponentSize: Int) -> Unit
    ) {
        val nodes = 0 until numNodes
        for (node in nodes) {
            if (node in visited) continue
            val oldVisitedSize = visited.size
            visitAllReachableNodes(node, nodeAdjacency, visited)
            val newVisitedSize = visited.size
            val componentSize = newVisitedSize - oldVisitedSize
            sideEffect(componentSize)
        }
    }

    private fun visitAllReachableNodes(
        sourceNode: Int,
        nodeAdjacency: Map<Int, List<Int>>,
        visited: MutableSet<Int>
    ) {
        visited.add(sourceNode)
        val adjacent = nodeAdjacency[sourceNode] ?: emptyList()
        for (node in adjacent) {
            if (node in visited) continue
            visitAllReachableNodes(node, nodeAdjacency, visited)
        }
    }
}