package com.hj.leetcode.kotlin.problem684

/**
 * LeetCode page: [684. Redundant Connection](https://leetcode.com/problems/redundant-connection/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of edges.
     */
    fun findRedundantConnection(edges: Array<IntArray>): IntArray {
        val uf = UnionFind(edges.size + 1)
        for (edge in edges) {
            if (!uf.union(edge[0], edge[1])) {
                return edge
            }
        }
        throw IllegalArgumentException("No edges found.")
    }

    private class UnionFind(
        size: Int,
    ) {
        val parents = IntArray(size) { it }
        val ranks = IntArray(size) { 0 }

        private fun find(x: Int): Int {
            if (parents[x] != x) {
                parents[x] = find(parents[x])
            }
            return parents[x]
        }

        fun union(
            x: Int,
            y: Int,
        ): Boolean {
            val xParent = find(x)
            val yParent = find(y)
            if (xParent == yParent) {
                return false
            }

            when {
                ranks[xParent] < ranks[yParent] -> parents[xParent] = yParent
                ranks[yParent] < ranks[xParent] -> parents[yParent] = xParent
                else -> {
                    parents[xParent] = yParent
                    ranks[yParent]++
                }
            }
            return true
        }
    }
}
