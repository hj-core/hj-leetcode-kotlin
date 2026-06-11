package com.hj.leetcode.kotlin.problem3558

/**
 * LeetCode page: [3558. Number of Ways to Assign Edge Weights I](https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of edges.
    fun assignEdgeWeights(edges: Array<IntArray>): Int {
        val adjList = buildAdjacencyList(edges)
        val height = dfsHeight(1, adjList, 0)
        return computeWays(height) // 2^(height-1) % 1_000_000_007
    }

    private fun buildAdjacencyList(edges: Array<IntArray>): List<List<Int>> {
        val n = edges.size + 1
        val adjList = List(n + 1) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            adjList[u].add(v)
            adjList[v].add(u)
        }

        return adjList
    }

    private fun dfsHeight(
        root: Int,
        adjacencyList: List<List<Int>>,
        parent: Int,
    ): Int {
        var height = 0
        for (child in adjacencyList[root]) {
            if (child != parent) {
                height = maxOf(height, 1 + dfsHeight(child, adjacencyList, root))
            }
        }
        return height
    }

    private fun computeWays(height: Int): Int {
        val modulus = 1_000_000_007
        var ways = 1L
        var base = 2L
        var pow = height - 1
        while (pow > 0) {
            if (pow and 1 == 1) {
                ways = (ways * base) % modulus
            }
            base = (base * base) % modulus
            pow = pow shr 1
        }

        return ways.toInt()
    }
}
