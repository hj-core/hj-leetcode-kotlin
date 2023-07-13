package com.hj.leetcode.kotlin.problem802

/**
 * LeetCode page: [802. Find Eventual Safe States](https://leetcode.com/problems/find-eventual-safe-states/);
 */
class Solution {
    /* Complexity:
     * Time O(V+E) and Space O(V) where V and E are the size and flatten size of graph;
     */
    fun eventualSafeNodes(graph: Array<IntArray>): List<Int> {
        val nodes = graph.indices
        val isTrapped = isTrappedByCycle(graph)
        return nodes.filter { !isTrapped[it] }
    }

    private fun isTrappedByCycle(graph: Array<IntArray>): BooleanArray {
        val nodes = graph.indices
        val visited = BooleanArray(graph.size)
        val result = BooleanArray(graph.size)

        for (node in nodes) {
            dfs(node, hashSetOf(), graph, visited, result)
        }
        return result
    }

    private fun dfs(
        currentNode: Int,
        currentPath: MutableSet<Int>,
        graph: Array<IntArray>,
        visited: BooleanArray,
        isTrappedByCycle: BooleanArray
    ) {
        if (visited[currentNode]) {
            if (currentNode in currentPath || isTrappedByCycle[currentNode]) {
                currentPath.forEach { isTrappedByCycle[it] = true }
            }
            return
        }

        visited[currentNode] = true
        currentPath.add(currentNode)
        for (next in graph[currentNode]) {
            dfs(next, currentPath, graph, visited, isTrappedByCycle)
        }
        currentPath.remove(currentNode)
    }
}