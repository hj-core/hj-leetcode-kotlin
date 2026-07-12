package com.hj.leetcode.kotlin.problem2685

/**
 * LeetCode page: [2685. Count the Number of Complete Components](https://leetcode.com/problems/count-the-number-of-complete-components/);
 */
class Solution {
    // Complexity:
    // Time O(n+E) and Space O(n) where E is the length of edges.
    fun countCompleteComponents(
        n: Int,
        edges: Array<IntArray>,
    ): Int {
        val uf = UnionFind(n)
        val degree = IntArray(n)
        for ((u, v) in edges) {
            uf.union(u, v)
            degree[u]++
            degree[v]++
        }

        return (0..<n).groupBy { uf.find(it) }.values.count { component ->
            component.all { degree[it] == component.size - 1 }
        }
    }

    private class UnionFind(
        n: Int,
    ) {
        private val parent = IntArray(n) { it }
        private val rank = IntArray(n)

        fun union(
            a: Int,
            b: Int,
        ) {
            val pa = find(a)
            val pb = find(b)
            if (pa == pb) {
                return
            }

            val ra = rank[pa]
            val rb = rank[pb]
            when {
                ra < rb -> {
                    parent[pa] = pb
                }

                ra > rb -> {
                    parent[pb] = pa
                }

                else -> {
                    parent[pb] = pa
                    rank[pa]++
                }
            }
        }

        fun find(a: Int): Int {
            if (parent[a] != a) {
                parent[a] = find(parent[a])
            }
            return parent[a]
        }
    }
}
