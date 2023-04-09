package com.hj.leetcode.kotlin.problem1857

/**
 * LeetCode page: [1857. Largest Color Value in a Directed Graph](https://leetcode.com/problems/largest-color-value-in-a-directed-graph/);
 */
class Solution {
    /* Complexity:
     * Time O(V+E) and Space O(V+E) where V and E are the size of colors and edges;
     */
    fun largestPathValue(colors: String, edges: Array<IntArray>): Int {
        val hasSelfLoop = edges.any { (u, v) -> u == v }
        if (hasSelfLoop) return -1

        val nodes = colors.indices
        // adjacency[i] ::= the adjacency of node i
        val adjacency = adjacency(edges)
        // The nodes sorted with the order in which a full dfs finishes visiting each node
        val finishingOrder = finishingOrder(nodes, adjacency)

        val hasCycle = hasCycle(edges, finishingOrder)
        if (hasCycle) return -1
        return largestPathValue(colors, adjacency, finishingOrder)
    }

    private fun adjacency(edges: Array<IntArray>): Map<Int, List<Int>> {
        val adjacency = hashMapOf<Int, MutableList<Int>>()
        for ((u, v) in edges) {
            val uAdjacency = adjacency.computeIfAbsent(u) { mutableListOf() }
            uAdjacency.add(v)
        }
        return adjacency
    }

    private fun finishingOrder(nodes: IntRange, adjacency: Map<Int, List<Int>>): List<Int> {
        val finishingOrder = mutableListOf<Int>()
        val visited = hashSetOf<Int>()
        fullDfs(nodes, adjacency, visited) { node ->
            finishingOrder.add(node)
        }
        return finishingOrder
    }

    private fun fullDfs(
        nodes: IntRange,
        adjacency: Map<Int, List<Int>>,
        visited: MutableSet<Int>,
        onEachFinishedNode: (node: Int) -> Unit
    ) {
        for (node in nodes) {
            val hasVisited = node in visited
            if (hasVisited) continue
            dfs(node, adjacency, visited, onEachFinishedNode)
        }
    }

    private fun dfs(
        source: Int,
        adjacency: Map<Int, List<Int>>,
        visited: MutableSet<Int>,
        onEachFinishedNode: (node: Int) -> Unit
    ) {
        val hasVisited = source in visited
        if (hasVisited) return

        visited.add(source)
        val sourceAdjacency = adjacency[source] ?: emptyList()
        for (destination in sourceAdjacency) {
            dfs(destination, adjacency, visited, onEachFinishedNode)
        }
        onEachFinishedNode(source)
    }

    private fun hasCycle(edges: Array<IntArray>, finishingOrder: List<Int>): Boolean {
        // nodeOrder[i] ::= the order of ith node in finishingOrder
        val nodeOrder = nodeOrder(finishingOrder)
        return edges.any { (u, v) -> nodeOrder[u] < nodeOrder[v] }
    }

    private fun nodeOrder(finishingOrder: List<Int>): IntArray {
        val nodeOrder = IntArray(finishingOrder.size)
        for ((order, node) in finishingOrder.withIndex()) {
            nodeOrder[node] = order
        }
        return nodeOrder
    }

    private fun largestPathValue(
        colors: String,
        adjacency: Map<Int, List<Int>>,
        finishingOrder: List<Int>
    ): Int {
        // subResults[i][j] ::= the largest value color 'a'+j can be among the paths start at node i
        val subResults = hashMapOf<Int, IntArray>()
        updateSubResults(colors, adjacency, finishingOrder, subResults)
        return originalProblem(colors, subResults)
    }

    private fun updateSubResults(
        colors: String,
        adjacency: Map<Int, List<Int>>,
        finishingOrder: List<Int>,
        subResults: MutableMap<Int, IntArray>
    ) {
        for (node in finishingOrder) {
            val largestColorValues = IntArray(26)
            val nodeAdjacency = adjacency[node] ?: emptyList()
            for (destination in nodeAdjacency) {
                val subResult = checkNotNull(subResults[destination])
                for ((index, value) in largestColorValues.withIndex()) {
                    if (value < subResult[index]) {
                        largestColorValues[index] = subResult[index]
                    }
                }
            }
            val nodeColorIndex = colors[node] - 'a'
            largestColorValues[nodeColorIndex]++
            subResults[node] = largestColorValues
        }
    }

    private fun originalProblem(colors: String, subResults: MutableMap<Int, IntArray>): Int {
        var largestValue = 0
        for ((node, nodeLargestValues) in subResults) {
            val nodeColorIndex = colors[node] - 'a'
            /* Compare with the current node color value only since other colors will be covered by the
             * rest nodes in the paths.
             */
            largestValue = maxOf(largestValue, nodeLargestValues[nodeColorIndex])
        }
        return largestValue
    }
}