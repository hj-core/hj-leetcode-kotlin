package com.hj.leetcode.kotlin.problem802

/**
 * LeetCode page: [802. Find Eventual Safe States](https://leetcode.com/problems/find-eventual-safe-states/);
 */
class Solution {
    /* Complexity:
     * Time O(E) and Space O(N)
     * where E is the flattened length of graph and N is the size of graph.
     */
    fun eventualSafeNodes(graph: Array<IntArray>): List<Int> {
        // 0=undefined, 1=safe, 2=unsafe
        val nodeSafety = IntArray(graph.size)
        return graph.indices.filter { dfs(it, graph, nodeSafety) }
    }

    // Update the nodeSafety and return whether the root is a safe node.
    private fun dfs(
        root: Int,
        graph: Array<IntArray>,
        nodeSafety: IntArray,
    ): Boolean {
        if (nodeSafety[root] == 1) {
            return true
        }
        if (nodeSafety[root] == 2) {
            return false
        }

        nodeSafety[root] = 2
        for (next in graph[root]) {
            if (!dfs(next, graph, nodeSafety)) {
                return false
            }
        }
        nodeSafety[root] = 1
        return true
    }
}
