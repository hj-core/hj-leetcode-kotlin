package com.hj.leetcode.kotlin.problem1514

import java.util.TreeSet
import kotlin.math.exp
import kotlin.math.ln

/**
 * LeetCode page: [1514. Path with Maximum Probability](https://leetcode.com/problems/path-with-maximum-probability/);
 */
class Solution {
    /* Complexity:
     * Time O((n+E)Log(n)) and Space O(n+E) where E is the size of edges;
     */
    fun maxProbability(
        n: Int,
        edges: Array<IntArray>,
        succProb: DoubleArray,
        start_node: Int,
        end_node: Int,
    ): Double {
        val adjacencyList = adjacencyList(n, edges, succProb)
        val minPathWeights = MutableList(n) { Double.MAX_VALUE }
        val nodes =
            TreeSet<Int>(
                compareBy(
                    { minPathWeights[it] },
                    { it }, // Important because of how TreeSet recognizes item identity
                ),
            )

        minPathWeights[start_node] = 0.0
        nodes.add(start_node)
        while (nodes.isNotEmpty()) {
            val closest = checkNotNull(nodes.pollFirst())
            if (closest == end_node) {
                return toProbability(minPathWeights[closest])
            }

            for (edge in adjacencyList[closest]) {
                val pathWeight = minPathWeights[closest] + edge.weight
                if (pathWeight < minPathWeights[edge.to]) {
                    nodes.remove(edge.to)
                    minPathWeights[edge.to] = pathWeight
                    nodes.add(edge.to)
                }
            }
        }
        return 0.0
    }

    private fun adjacencyList(
        n: Int,
        edges: Array<IntArray>,
        succProb: DoubleArray,
    ): List<List<Edge>> {
        val result = List(n) { mutableListOf<Edge>() }
        for (i in edges.indices) {
            if (succProb[i] == 0.0) {
                continue
            }
            val (from, to) = edges[i]
            val weight = toVirtualWeight(succProb[i])
            result[from].add(Edge(from, to, weight))
            result[to].add(Edge(to, from, weight))
        }
        return result
    }

    private inner class Edge(
        val from: Int,
        val to: Int,
        val weight: Double,
    )

    private fun toVirtualWeight(probability: Double): Double = -ln(probability)

    private fun toProbability(virtualWeight: Double): Double = exp(-virtualWeight)
}
