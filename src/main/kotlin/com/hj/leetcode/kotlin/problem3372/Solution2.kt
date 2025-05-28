package com.hj.leetcode.kotlin.problem3372

/**
 * LeetCode page: [3372. Maximize the Number of Target Nodes After Connecting Trees I](https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/);
 */
class Solution2 {
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
        // Khan's algorithm for topological order
        val (degrees, adjacencyList) = computeDegreeAndAdjacencyList(edges)

        val queue = ArrayDeque<Int>()
        for (i in degrees.indices) {
            if (degrees[i] == 1) {
                queue.addLast(i)
            }
        }

        val result = IntArray(edges.size + 1)
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            dfs(node, node, -1, k, degrees, adjacencyList, result)
            result[node]++ // The node is a target of itself
            degrees[node]--

            for (child in adjacencyList[node]) {
                degrees[child]--
                if (degrees[child] == 1) {
                    queue.addLast(child)
                }
            }
        }
        return result
    }

    private fun computeDegreeAndAdjacencyList(edges: Array<IntArray>): Pair<IntArray, List<List<Int>>> {
        val degrees = IntArray(edges.size + 1)
        val adjacencyList = List(edges.size + 1) { mutableListOf<Int>() }

        for ((u, v) in edges) {
            degrees[u]++
            degrees[v]++
            adjacencyList[u].add(v)
            adjacencyList[v].add(u)
        }
        return Pair(degrees, adjacencyList)
    }

    // / `dfs` increases the countTargetNodes for both the root and the node, for each
    // / node that is not the root, not yet settled (degree[node] >= 1), and reachable
    // / from the root within k edges.
    private fun dfs(
        root: Int,
        node: Int,
        parentNode: Int,
        k: Int,
        degrees: IntArray,
        adjacencyList: List<List<Int>>,
        countTargetNodes: IntArray,
    ) {
        if (degrees[node] < 1) {
            return
        }

        if (node != root) {
            countTargetNodes[root]++
            countTargetNodes[node]++
        }
        if (k == 0) {
            return
        }

        for (child in adjacencyList[node]) {
            if (child == parentNode) {
                continue
            }
            dfs(root, child, node, k - 1, degrees, adjacencyList, countTargetNodes)
        }
    }
}
