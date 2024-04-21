package com.hj.leetcode.kotlin.problem1971

/**
 * LeetCode page: [1971. Find if Path Exists in Graph](https://leetcode.com/problems/find-if-path-exists-in-graph/description/);
 */
class Solution2 {
    /* Complexity:
     * Time O(E) and Space O(E) where E is the size of edges;
     */
    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        if (source == destination) {
            return true
        }
        val adjacencyList = adjacencyList(edges)
        val bfsQueue = ArrayDeque<Int>()
        val visited = hashSetOf<Int>()

        bfsQueue.addLast(source)
        visited.add(source)
        while (bfsQueue.isNotEmpty()) {
            val vertex = bfsQueue.removeFirst()
            if (vertex == destination) {
                return true
            }

            for (next in adjacencyList[vertex] ?: emptyList()) {
                if (next !in visited) {
                    bfsQueue.addLast(next)
                    visited.add(next)
                }
            }
        }
        return false
    }

    private fun adjacencyList(edges: Array<IntArray>): Map<Int, List<Int>> {
        val result = hashMapOf<Int, MutableList<Int>>()
        for ((u, v) in edges) {
            result.computeIfAbsent(u) { mutableListOf() }.add(v)
            result.computeIfAbsent(v) { mutableListOf() }.add(u)
        }
        return result
    }
}