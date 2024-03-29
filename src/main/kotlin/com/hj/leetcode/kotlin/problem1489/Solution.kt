package com.hj.leetcode.kotlin.problem1489

/**
 * LeetCode page: [1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree](https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(E^2 * Log n) and Space O(E) where E is the size of edges;
     */
    fun findCriticalAndPseudoCriticalEdges(n: Int, edges: Array<IntArray>): List<List<Int>> {
        val sortedEdges = sortedEdges(edges) // Sorted by monotonically increasing weight
        val mstWeight = checkNotNull(mstWeight(n, sortedEdges))

        val criticalEdges = criticalEdges(n, sortedEdges, mstWeight)
        val pseudoCriticalEdges = pseudoCriticalEdges(n, sortedEdges, criticalEdges, mstWeight)

        return listOf(criticalEdges.map { it.index }, pseudoCriticalEdges.map { it.index })
    }

    private data class Edge(val u: Int, val v: Int, val weight: Int, val index: Int)

    private fun sortedEdges(edges: Array<IntArray>): List<Edge> {
        val result = edges.mapIndexedTo(mutableListOf()) { index, array ->
            Edge(array[0], array[1], array[2], index)
        }
        result.sortBy { edge -> edge.weight }
        return result
    }

    /**
     * Return the MST weight, or null if the graph is not connected.
     *
     * If [mandatedExclusion] and [mandatedInclusion] are not disjoint, the result
     * is unexpected.
     */
    private fun mstWeight(
        n: Int,
        sortedEdges: List<Edge>,
        mandatedInclusion: Set<Edge> = emptySet(),
        mandatedExclusion: Set<Edge> = emptySet()
    ): Int? {
        var result = 0
        val unionFind = UnionFind(n)

        for (edge in mandatedInclusion) {
            unionFind.union(edge.u, edge.v)
            result+= edge.weight
        }

        for (edge in sortedEdges) {
            if (edge in mandatedExclusion) {
                continue
            }

            if (unionFind.union(edge.u, edge.v)) {
                result += edge.weight
            }
        }

        val isConnected = unionFind.numComponents == 1
        return if (isConnected) result else null
    }

    private class UnionFind(size: Int) {

        private val parents = IntArray(size) { it }
        private val ranks = IntArray(size)
        var numComponents = size
            private set

        fun union(x: Int, y: Int): Boolean {
            val xRoot = find(x)
            val yRoot = find(y)

            if (xRoot == yRoot) {
                return false
            }

            when {
                ranks[xRoot] < ranks[yRoot] -> parents[xRoot] = yRoot
                ranks[yRoot] < ranks[xRoot] -> parents[yRoot] = xRoot
                else -> {
                    parents[yRoot] = xRoot
                    ranks[xRoot]++
                }
            }
            numComponents--
            return true
        }

        private fun find(x: Int): Int {
            if (parents[x] != x) {
                parents[x] = find(parents[x])
            }
            return parents[x]
        }
    }

    private fun criticalEdges(n: Int, sortedEdges: List<Edge>, mstWeight: Int): Set<Edge> {
        val result = hashSetOf<Edge>()

        /* Find all critical edges:
         * If excluding an edge results in an increase in the MST weight or makes the graph
         * no longer connected, then it is a critical edge;
         */
        for (excludedEdge in sortedEdges) {
            val newMstWeight = mstWeight(
                n = n,
                sortedEdges = sortedEdges,
                mandatedExclusion = hashSetOf(excludedEdge)
            )
            if (newMstWeight == null || newMstWeight > mstWeight) {
                result.add(excludedEdge)
            }
        }
        return result
    }

    private fun pseudoCriticalEdges(
        n: Int,
        sortedEdges: List<Edge>,
        criticalEdges: Set<Edge>,
        mstWeight: Int
    ): Set<Edge> {
        val result = hashSetOf<Edge>()

        /* Find all pseudo-critical edges:
         * If an edge is not critical, but including it does not increase the MST weight,
         * then it is a pseudo-critical edge;
         */
        for (includedEdge in sortedEdges) {
            if (includedEdge in criticalEdges) {
                continue
            }

            val newMstWeight = mstWeight(
                n = n,
                sortedEdges = sortedEdges,
                mandatedInclusion = hashSetOf(includedEdge)
            )
            if (newMstWeight == mstWeight) {
                result.add(includedEdge)
            }
        }
        return result
    }
}