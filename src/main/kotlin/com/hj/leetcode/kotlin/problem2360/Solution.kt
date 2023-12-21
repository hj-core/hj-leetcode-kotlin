package com.hj.leetcode.kotlin.problem2360

/**
 * LeetCode page: [2360. Longest Cycle in a Graph](https://leetcode.com/problems/longest-cycle-in-a-graph/);
 */
class Solution {

    private val unknownLength = -1

    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of edges;
     */
    fun longestCycle(edges: IntArray): Int {
        val numNodes = edges.size
        // reachableCycleLengths[i] ::= the reachable cycle length of node i
        val reachableCycleLengths = IntArray(numNodes) { unknownLength }
        val nodes = 0 until numNodes
        for (node in nodes) {
            val hasVisited = reachableCycleLengths[node] != unknownLength
            if (hasVisited) continue
            updateReachableCycleLengths(node, edges, reachableCycleLengths)
        }
        val maxCycleLength = reachableCycleLengths.max()!!
        val noCycleFound = maxCycleLength == 0
        return if (noCycleFound) -1 else maxCycleLength
    }

    private fun updateReachableCycleLengths(
        sourceNode: Int,
        edges: IntArray,
        reachableCycleLengths: IntArray
    ) {
        val cycleLength = reachableCycleLengthOfNode(sourceNode, edges, reachableCycleLengths)
        var currentNode: Int? = sourceNode
        while (
            currentNode != null &&
            reachableCycleLengths[currentNode] == unknownLength
        ) {
            reachableCycleLengths[currentNode] = cycleLength
            currentNode = nextNodeOrNull(currentNode, edges)
        }
    }

    private fun reachableCycleLengthOfNode(
        node: Int,
        edges: IntArray,
        reachableCycleLengths: IntArray
    ): Int {
        var slowNode = node
        var fastNode = secondNextNodeOrNull(node, edges)
        while (
            fastNode != null &&
            reachableCycleLengths[fastNode] == unknownLength &&
            slowNode != fastNode
        ) {
            slowNode = nextNodeOrNull(slowNode, edges) ?: throw IllegalStateException()
            fastNode = secondNextNodeOrNull(fastNode, edges)
        }

        val noCycleFound = fastNode == null
        if (noCycleFound) return 0

        checkNotNull(fastNode)
        val isKnownLength = reachableCycleLengths[fastNode] != unknownLength
        if (isKnownLength) return reachableCycleLengths[fastNode]

        return cycleLength(fastNode, edges)
    }

    private fun nextNodeOrNull(currentNode: Int, edges: IntArray): Int? {
        return edges[currentNode].let { if (it == -1) null else it }
    }

    private fun secondNextNodeOrNull(currentNode: Int, edges: IntArray): Int? {
        return nextNodeOrNull(currentNode, edges)?.let { nextNodeOrNull(it, edges) }
    }

    private fun cycleLength(nodeInCycle: Int, edges: IntArray): Int {
        var length = 1
        var currentNode = edges[nodeInCycle]
        while (currentNode != nodeInCycle) {
            length++
            currentNode = edges[currentNode]
        }
        return length
    }
}