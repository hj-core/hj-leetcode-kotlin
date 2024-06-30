package com.hj.leetcode.kotlin.problem2192

/**
 * LeetCode page: [2192. All Ancestors of a Node in a Directed Acyclic Graph](https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/);
 */
class Solution {
    /* Complexity:
     * Time O(nE+n^2*log(n)) and Space O(n^2+E) where E is the size of edges;
     */
    fun getAncestors(n: Int, edges: Array<IntArray>): List<List<Int>> {
        val adjacencyList = adjacencyList(n, edges)
        val inDegree = inDegree(n, edges)
        val ancestors = List(n) { mutableSetOf<Int>() }
        val zeroDegreeNodes = ArrayDeque<Int>()

        (0..<n).filterTo(zeroDegreeNodes) { inDegree[it] == 0 }
        while (zeroDegreeNodes.isNotEmpty()) {
            val node = zeroDegreeNodes.removeFirst()
            for (descendant in adjacencyList[node]) {
                ancestors[descendant].add(node)
                ancestors[descendant].addAll(ancestors[node])

                inDegree[descendant]--
                if (inDegree[descendant] == 0) {
                    zeroDegreeNodes.addLast(descendant)
                }

            }
        }
        return ancestors.map { it.sorted() }
    }

    private fun adjacencyList(n: Int, edges: Array<IntArray>): List<List<Int>> {
        val result = List(n) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            result[u].add(v)
        }
        return result
    }

    private fun inDegree(n: Int, edges: Array<IntArray>): IntArray {
        val result = IntArray(n)
        for ((_, v) in edges) {
            result[v]++
        }
        return result
    }
}