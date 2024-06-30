package com.hj.leetcode.kotlin.problem1971

/**
 * LeetCode page: [1971. Find if Path Exists in Graph](https://leetcode.com/problems/find-if-path-exists-in-graph/description/);
 */
class Solution {
    /* Complexity:
     * Time O(E) and Space O(E) where E is the size of edges;
     */
    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        if (source == destination) {
            return true
        }
        return hasValidPath(source, destination, adjacencyList(edges), hashSetOf())
    }

    private fun hasValidPath(
        source: Int,
        destination: Int,
        adjacencyList: Map<Int, Set<Int>>,
        visited: MutableSet<Int>
    ): Boolean {
        if (source == destination) {
            return true
        }
        if (source in visited) {
            return false
        }

        visited.add(source)
        return adjacencyList[source]?.any {
            hasValidPath(it, destination, adjacencyList, visited)
        } ?: false
    }

    private fun adjacencyList(edges: Array<IntArray>): Map<Int, Set<Int>> {
        val result = hashMapOf<Int, MutableSet<Int>>()
        for ((u, v) in edges) {
            result.computeIfAbsent(u) { hashSetOf() }.add(v)
            result.computeIfAbsent(v) { hashSetOf() }.add(u)
        }
        return result
    }
}