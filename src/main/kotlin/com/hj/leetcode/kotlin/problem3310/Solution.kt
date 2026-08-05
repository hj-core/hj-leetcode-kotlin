package com.hj.leetcode.kotlin.problem3310

/**
 * LeetCode page: [3310. Remove Methods From Project](https://leetcode.com/problems/remove-methods-from-project/);
 */
class Solution {
    // Complexity:
    // Time O(n+E) and Space O(n+E) where E is the length of invocations.
    fun remainingMethods(
        n: Int,
        k: Int,
        invocations: Array<IntArray>,
    ): List<Int> {
        val adjList = buildAdjacencyList(n, invocations)
        val visited = BooleanArray(n)
        dfs(k, adjList, visited)

        val result = mutableListOf<Int>()
        for (node in 0..<n) {
            if (visited[node]) {
                continue
            }
            if (adjList[node].any { visited[it] }) {
                return List(n) { it }
            }
            result.add(node)
        }

        return result
    }

    private fun buildAdjacencyList(
        n: Int,
        invocations: Array<IntArray>,
    ): List<List<Int>> {
        val adjList = List(n) { mutableListOf<Int>() }
        for ((u, v) in invocations) {
            adjList[u].add(v)
        }
        return adjList
    }

    private fun dfs(
        root: Int,
        adjacencyList: List<List<Int>>,
        visited: BooleanArray,
    ) {
        if (visited[root]) {
            return
        }

        visited[root] = true
        for (next in adjacencyList[root]) {
            dfs(next, adjacencyList, visited)
        }
    }
}
