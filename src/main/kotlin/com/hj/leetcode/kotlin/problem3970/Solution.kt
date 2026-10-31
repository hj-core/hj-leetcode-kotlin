package com.hj.leetcode.kotlin.problem3970

import java.util.PriorityQueue

/**
 * LeetCode page: [3970. Shortest Path With At Most K Consecutive Identical Characters](https://leetcode.com/problems/shortest-path-with-at-most-k-consecutive-identical-characters/);
 */
class Solution {
    // Complexity:
    // Time O(???) and Space O(???).
    fun shortestPath(
        n: Int,
        edges: Array<IntArray>,
        labels: String,
        k: Int,
    ): Int {
        val adjList = buildAdjacencyList(n, edges)
        // minCost[u][r]:= minCost to reach u with path suffix is r consecutive labels[u]
        val minCost = Array(n) { IntArray(k + 1) { Int.MAX_VALUE } }
        minCost[0][1] = 0
        val pq = PriorityQueue<IntArray>(compareBy { (u, repeat, pathCost) -> pathCost })
        pq.add(intArrayOf(0, 1, 0))

        while (pq.isNotEmpty()) {
            val (u, repeat, pathCost) = pq.poll()
            if (pathCost > minCost[u][repeat]) {
                continue
            }
            if (u == n - 1) {
                return pathCost
            }

            for ((_, v, w) in adjList[u]) {
                val newRepeat = if (labels[v] == labels[u]) repeat + 1 else 1
                if (newRepeat > k) {
                    continue
                }

                val newPathCost = pathCost + w
                if (newPathCost < minCost[v][newRepeat]) {
                    minCost[v][newRepeat] = newPathCost
                    pq.add(intArrayOf(v, newRepeat, newPathCost))
                }
            }
        }

        return -1
    }

    private fun buildAdjacencyList(
        n: Int,
        edges: Array<IntArray>,
    ): Array<MutableList<IntArray>> {
        val adjList = Array(n) { mutableListOf<IntArray>() }
        for (edge in edges) {
            adjList[edge[0]].add(edge)
        }

        return adjList
    }
}
