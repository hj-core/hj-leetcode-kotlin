package com.hj.leetcode.kotlin.problem3108

/**
 * LeetCode page: [3108. Minimum Cost Walk in Weighted Graph](https://leetcode.com/problems/minimum-cost-walk-in-weighted-graph/);
 */
class Solution {
    // Time O(n+E+Q) and Space O(n+Q)
    // where E and Q are the length of edges and query, respectively.
    fun minimumCost(
        n: Int,
        edges: Array<IntArray>,
        query: Array<IntArray>,
    ): IntArray {
        val uf = UnionFind(n)
        // The min costs with respect to the connected components.
        // Only the values at nodes that are uf roots are valid.
        val minCosts = IntArray(n) { -1 }

        for ((u, v, w) in edges) {
            uf.union(u, v)
            minCosts[u] = if (minCosts[u] == -1) w else minCosts[u] and w
        }

        for (x in 0..<n) {
            if (minCosts[x] != -1) {
                val xP = uf.find(x)
                minCosts[xP] = minCosts[xP] and minCosts[x]
            }
        }

        return IntArray(query.size) {
            val (u, v) = query[it]
            val uP = uf.find(u)
            val vP = uf.find(v)
            if (uP != vP) -1 else minCosts[uP]
        }
    }

    private class UnionFind(
        n: Int,
    ) {
        private val parents = IntArray(n) { it }
        private val ranks = IntArray(n)

        fun find(x: Int): Int {
            if (x != parents[x]) {
                parents[x] = find(parents[x])
            }
            return parents[x]
        }

        fun union(
            x: Int,
            y: Int,
        ) {
            val xP = find(x)
            val yP = find(y)
            if (xP == yP) {
                return
            }

            when {
                ranks[xP] < ranks[yP] -> parents[xP] = yP
                ranks[yP] < ranks[xP] -> parents[yP] = xP
                else -> {
                    parents[xP] = yP
                    ranks[yP]++
                }
            }
        }
    }
}
