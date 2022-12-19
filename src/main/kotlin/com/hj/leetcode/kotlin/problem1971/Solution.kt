package com.hj.leetcode.kotlin.problem1971

/**
 * LeetCode page: [1971. Find if Path Exists in Graph](https://leetcode.com/problems/find-if-path-exists-in-graph/description/);
 */
class Solution {
    /* Complexity:
     * Time O(|edges|) and Space O(|edges|);
     */
    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        if (source == destination) return true
        val e = constructEdges(edges)
        val visited = hashSetOf<Int>()
        return isReachable(source, destination, e, visited)
    }

    private fun constructEdges(edges: Array<IntArray>): Map<Int, Set<Int>> {
        val e = hashMapOf<Int, MutableSet<Int>>()
        for ((u, v) in edges) {
            e.computeIfAbsent(u) { hashSetOf() }.add(v)
            e.computeIfAbsent(v) { hashSetOf() }.add(u)
        }
        return e
    }

    /* WARNING!!
     * 1) Throws a StackOverflowError if the search path is too long;
     * 2) Return false for the case that source is the same as destination;
     */
    private fun isReachable(
        source: Int, destination: Int, edges: Map<Int, Set<Int>>, visited: MutableSet<Int>
    ): Boolean {
        if (visited.contains(source)) return false

        visited.add(source)
        val nextVertices = edges[source] ?: emptySet()
        if (destination in nextVertices) return true

        for (vertex in nextVertices) {
            val isReachable = isReachable(vertex, destination, edges, visited)
            if (isReachable) return true
        }
        return false
    }
}