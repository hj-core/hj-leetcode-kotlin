package com.hj.leetcode.kotlin.problem886

/**
 * LeetCode page: [886. Possible Bipartition](https://leetcode.com/problems/possible-bipartition/);
 */
class Solution {
    /* Complexity:
     * Time O(n+|dislikes|) and Space O(n+|dislikes|);
     */
    fun possibleBipartition(n: Int, dislikes: Array<IntArray>): Boolean {
        /* We reduce the problem to a two-coloring graph problem:
         * 1) each person is a vertex in the graph;
         * 2) dislike between people means an undirected edge between corresponding vertices;
         * 3) grouping people is coloring the graph and the color of each vertex can have values 0, 1 and -1,
         *    where 0 means unknown, 1 and -1 represent two different colors (i.e. groups);
         * The goal is to determine whether the graph can be colored in a way that each edge incidents at
         * different color vertices.
         */
        val edges = constructEdges(dislikes)
        val vertexColor = IntArray(n + 1)

        for (vertex in 1..n) {
            if (vertexColor[vertex] == 0) {
                val canBeColored = colorConnectedComponent(vertex, vertexColor, edges)
                if (!canBeColored) return false
            }
        }
        return true
    }

    private fun constructEdges(dislikes: Array<IntArray>): Map<Int, List<Int>> {
        val edges = hashMapOf<Int, MutableList<Int>>()
        for ((u, v) in dislikes) {
            edges.computeIfAbsent(u) { mutableListOf() }.add(v)
            edges.computeIfAbsent(v) { mutableListOf() }.add(u)
        }
        return edges
    }

    private fun colorConnectedComponent(source: Int, vertexColor: IntArray, edges: Map<Int, List<Int>>): Boolean {
        val verticesAtCurrDepth = ArrayDeque<Int>()
        vertexColor[source] = 1
        verticesAtCurrDepth.addLast(source)

        while (verticesAtCurrDepth.isNotEmpty()) {
            repeat(verticesAtCurrDepth.size) {
                val u = verticesAtCurrDepth.removeFirst()
                val color = vertexColor[u]
                val anotherColor = -color
                val adjacencies = edges[u] ?: emptyList()
                for (v in adjacencies) {
                    if (vertexColor[v] == color) return false
                    if (vertexColor[v] == anotherColor) continue
                    vertexColor[v] = anotherColor
                    verticesAtCurrDepth.addLast(v)
                }
            }
        }
        return true
    }
}