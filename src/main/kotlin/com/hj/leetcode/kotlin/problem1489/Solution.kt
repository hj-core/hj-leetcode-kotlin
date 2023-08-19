package com.hj.leetcode.kotlin.problem1489

/**
 * LeetCode page: [1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree](https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/);
 */
class Solution {
    fun findCriticalAndPseudoCriticalEdges(n: Int, edges: Array<IntArray>): List<List<Int>> {
        // Sort edges according to its weight in non-descending order;
        val sortedEdges = sortedEdges(edges)

        // Find the weight of MST;
        val mstWeight = mstWeight(n, sortedEdges)

        /* Find all critical edges:
         * If excluding an edge resulting in an increasing on the MST weight or make the
         * graph no longer connected, then it is a critical edge;
         */
        val criticalEdges = hashSetOf<Edge>()

        for (excludedEdge in sortedEdges) {
            var newMstWeight = 0
            val unionFind = UnionFind(n)

            for (edge in sortedEdges) {
                if (edge == excludedEdge) {
                    continue
                }

                if (unionFind.union(edge.u, edge.v)) {
                    newMstWeight += edge.weight
                }
            }

            if (unionFind.numComponents > 1 || newMstWeight > mstWeight) {
                criticalEdges.add(excludedEdge)
            }
        }

        /* Find all pseudo-critical edges:
         * If an edge is not critical but including it will not increase the MST weight,
         * then it is a pseudo-critical edge;
         */
        val pseudoCriticalEdges = hashSetOf<Edge>()

        for (includedEdge in sortedEdges) {
            if (includedEdge in criticalEdges) {
                continue
            }

            var newMstWeight = 0
            val unionFind = UnionFind(n)

            newMstWeight += includedEdge.weight
            unionFind.union(includedEdge.u, includedEdge.v)

            for (edge in sortedEdges) {
                if (unionFind.union(edge.u, edge.v)) {
                    newMstWeight += edge.weight
                }
            }

            if (newMstWeight == mstWeight) {
                pseudoCriticalEdges.add(includedEdge)
            }
        }

        // Return the result;
        return listOf(criticalEdges.map { it.index }, pseudoCriticalEdges.map { it.index })
    }

    private fun sortedEdges(edges: Array<IntArray>): List<Edge> {
        val result = edges.mapIndexedTo(mutableListOf()) { index, array ->
            Edge(array[0], array[1], array[2], index)
        }
        result.sortBy { edge -> edge.weight }
        return result
    }

    private data class Edge(val u: Int, val v: Int, val weight: Int, val index: Int)

    private fun mstWeight(n: Int, sortedEdges: List<Edge>): Int {
        var result = 0
        val unionFind = UnionFind(n)

        for (edge in sortedEdges) {
            if (unionFind.union(edge.u, edge.v)) {
                result += edge.weight
            }
        }
        return result
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
}