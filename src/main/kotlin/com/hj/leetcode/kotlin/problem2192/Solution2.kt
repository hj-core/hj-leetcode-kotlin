package com.hj.leetcode.kotlin.problem2192

/**
 * LeetCode page: [2192. All Ancestors of a Node in a Directed Acyclic Graph](https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/);
 */
class Solution2 {
    /* Complexity:
     * Time O(nE) and Space O(n^2+E) where E is the size of edges;
     */
    fun getAncestors(n: Int, edges: Array<IntArray>): List<List<Int>> {
        val adjacencyList = adjacencyList(n, edges)
        val result = List(n) { mutableListOf<Int>() }
        for (ancestor in 0..<n) {
            dfs(ancestor, adjacencyList, mutableSetOf()) { descendant ->
                if (descendant != ancestor) {
                    result[descendant].add(ancestor)
                }
            }
        }
        return result
    }

    private fun adjacencyList(n: Int, edges: Array<IntArray>): List<List<Int>> {
        val result = List(n) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            result[u].add(v)
        }
        return result
    }

    private fun dfs(
        root: Int,
        adjacencyList: List<List<Int>>,
        visited: MutableSet<Int>,
        onEachNode: (node: Int) -> Unit,
    ) {
        if (root in visited) {
            return
        }
        onEachNode(root)
        visited.add(root)
        for (descendant in adjacencyList[root]) {
            dfs(descendant, adjacencyList, visited, onEachNode)
        }
    }
}