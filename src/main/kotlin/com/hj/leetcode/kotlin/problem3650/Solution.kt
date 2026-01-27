package com.hj.leetcode.kotlin.problem3650

import java.util.PriorityQueue
import kotlin.math.cos

/**
 * LeetCode page: [3650. Minimum Cost Path with Edge Reversals](https://leetcode.com/problems/minimum-cost-path-with-edge-reversals/);
 */
class Solution {
    // Complexity:
    // Time O(VLogV+ELogV) and Space O(V+E) where V is n and E is the
    // length of edges.
    fun minCost(
        n: Int,
        edges: Array<IntArray>,
    ): Int {
        val adjList = adjacencyList(n, edges)

        // minCost[u]: The absolute value is the current minimum cost
        // from 0 to u. A negative value (except for node 0) marks the
        // cost as finalized. Note that all edge weights are positive.
        val minCost = IntArray(n) { Int.MAX_VALUE }
        minCost[0] = 0

        // A minHeap contains the (u, costU) snapshots.
        val pq =
            PriorityQueue<IntArray>(compareBy { (u, costU) -> costU })
        pq.add(intArrayOf(0, 0))

        while (pq.isNotEmpty()) {
            val (u, costU) = pq.poll()
            if (u == n - 1) {
                return costU
            }

            if (costU <= minCost[u]) {
                for ((_, v, w) in adjList[u]) {
                    val costV = costU + w
                    if (costV < minCost[v]) {
                        minCost[v] = costV
                        pq.add(intArrayOf(v, minCost[v]))
                    }
                }

                minCost[u] = -costU
            }
        }

        return -1
    }

    // Returns an adjacency list for each node, including the reversed
    // edges.
    private fun adjacencyList(
        n: Int,
        edges: Array<IntArray>,
    ): Array<MutableList<IntArray>> {
        val list = Array(n) { mutableListOf<IntArray>() }
        for (edge in edges) {
            val (u, v, w) = edge
            list[u].add(edge)
            list[v].add(intArrayOf(v, u, w * 2))
        }

        return list
    }
}
