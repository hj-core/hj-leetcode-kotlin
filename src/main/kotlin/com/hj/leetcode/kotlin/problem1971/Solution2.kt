package com.hj.leetcode.kotlin.problem1971

/**
 * LeetCode page: [1971. Find if Path Exists in Graph](https://leetcode.com/problems/find-if-path-exists-in-graph/description/);
 */
class Solution2 {
    /* Complexity:
     * Time O(|edges|) and Space O(|edges|);
     */
    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        if (source == destination) return true
        val e = constructEdges(edges)
        val verticesAtCurrDepth = ArrayDeque<Int>()
        val verticesVisited = hashSetOf<Int>()

        verticesAtCurrDepth.addLast(source)
        verticesVisited.add(source)
        while (verticesAtCurrDepth.isNotEmpty()) {
            repeat(verticesAtCurrDepth.size) {
                val vertex = verticesAtCurrDepth.removeFirst()
                val adjacencies = e[vertex] ?: emptyList()
                for (v in adjacencies) {
                    if (verticesVisited.contains(v)) continue
                    if (v == destination) return true
                    verticesAtCurrDepth.addLast(v)
                    verticesVisited.add(v)
                }
            }
        }
        return false
    }

    private fun constructEdges(edges: Array<IntArray>): Map<Int, List<Int>> {
        val e = hashMapOf<Int, MutableList<Int>>()
        for ((u, v) in edges) {
            e.computeIfAbsent(u) { mutableListOf() }.add(v)
            e.computeIfAbsent(v) { mutableListOf() }.add(u)
        }
        return e
    }
}