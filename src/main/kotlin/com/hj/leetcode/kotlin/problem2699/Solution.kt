package com.hj.leetcode.kotlin.problem2699

import java.util.*

/**
 * LeetCode page: [2699. Modify Graph Edge Weights](https://leetcode.com/problems/modify-graph-edge-weights/);
 *
 * TODO([Dijkstra's with TC O((E + V) log V) Beats 100% in all languages](https://leetcode.com/problems/modify-graph-edge-weights/discuss/5708699/))
 */
class Solution {
    /* Complexity:
     * Time O((E+nLog(n))LogE) and Space O(n+E) where E is the size of edges;
     */
    fun modifiedGraphEdges(
        n: Int,
        edges: Array<IntArray>,
        source: Int,
        destination: Int,
        target: Int,
    ): Array<IntArray> {
        val adjacencyList = adjacencyList(n, edges)
        if (minPathWeight(n, source, destination, adjacencyList, -1) < target) {
            return emptyArray()
        }
        if (minPathWeight(n, source, destination, adjacencyList, edges.lastIndex) > target) {
            return emptyArray()
        }

        // Binary search on the index of edges
        var left = -1
        var right = edges.lastIndex
        while (left <= right) {
            val mid = left + (right - left) / 2
            val guess = minPathWeight(n, source, destination, adjacencyList, mid)
            // We want mid be the critical index that just makes minPathWeight below target
            when {
                guess > target -> left = mid + 1
                guess < target -> right = mid - 1
                else -> right = mid - 1
            }
        }

        val result = Array(edges.size) { edges[it].clone() }
        val guessWeight = minPathWeight(n, source, destination, adjacencyList, left)
        for ((i, edge) in result.withIndex()) {
            if (edge[2] == -1) {
                edge[2] =
                    when {
                        i < left -> 1
                        i > left -> target + 1
                        else -> target - guessWeight + 1
                    }
            }
        }
        return result
    }

    private fun adjacencyList(
        n: Int,
        edges: Array<IntArray>,
    ): List<List<Edge>> {
        val result = List(n) { mutableListOf<Edge>() }
        for ((i, edge) in edges.withIndex()) {
            val (u, v, weight) = edge
            result[u].add(Edge(u, v, weight, i))
            result[v].add(Edge(v, u, weight, i))
        }
        return result
    }

    private data class Edge(
        val u: Int,
        val v: Int,
        val weight: Int,
        val index: Int,
    )

    private fun minPathWeight(
        n: Int,
        source: Int,
        destination: Int,
        adjacencyList: List<List<Edge>>,
        modifyIndexWithin: Int,
    ): Int {
        // Dijkstra's algorithm
        val minPathWeight = IntArray(n) { Int.MAX_VALUE }
        val nodes = TreeSet<Int>(compareBy({ minPathWeight[it] }, { it }))

        minPathWeight[source] = 0
        nodes.add(source)
        while (nodes.isNotEmpty()) {
            val closest = checkNotNull(nodes.pollFirst())
            if (closest == destination) {
                return minPathWeight[destination]
            }
            for ((_, v, weight, index) in adjacencyList[closest]) {
                if (weight == -1 && modifyIndexWithin < index) {
                    continue
                }
                val modifiedWeight = if (weight == -1) 1 else weight
                if (minPathWeight[closest] + modifiedWeight < minPathWeight[v]) {
                    nodes.remove(v)
                    minPathWeight[v] = minPathWeight[closest] + modifiedWeight
                    nodes.add(v)
                }
            }
        }
        return Int.MAX_VALUE
    }
}
