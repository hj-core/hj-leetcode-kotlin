package com.hj.leetcode.kotlin.problem797

/**
 * LeetCode page: [797. All Paths From Source to Target](https://leetcode.com/problems/all-paths-from-source-to-target/);
 */
class Solution {
    /* Complexity:
     * Time O(2^n) and Space O(2^n).
     * The worst case could be a graph whose node i is connected to node 0, 1, .., i - 1 for i in 1, .., n.
     */
    fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
        val initialPath = mutableListOf(0)
        val destination = graph.lastIndex
        return mutableListOf<List<Int>>().apply {
            addAllPaths(initialPath, destination, graph)
        }
    }

    // CAUTION: Has a sideEffect that will remove the last item of initialPath;
    private fun MutableList<List<Int>>.addAllPaths(
        initialPath: MutableList<Int>,
        destination: Int,
        graph: Array<IntArray>
    ) {
        val currNode = initialPath.last()
        if (currNode == destination) {
            add(initialPath.toList())
        } else {
            val adjacency = graph[currNode]
            for (node in adjacency) {
                initialPath.add(node)
                addAllPaths(initialPath, destination, graph)
            }
        }
        initialPath.apply { removeAt(lastIndex) }
    }
}