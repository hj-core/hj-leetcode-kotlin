package com.hj.leetcode.kotlin.problem1584

/**
 * LeetCode page: [1584. Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2 LogN) and Space O(N^2) where N is the size of points;
     */
    fun minCostConnectPoints(points: Array<IntArray>): Int {
        var result = 0
        val unionFind = UnionFind(points.size)
        for (edge in sortedEdgesOfCompleteGraph(points)) {
            if (unionFind.union(edge.point1Index, edge.point2Index)) {
                result += edge.manhattanDistance
            }
        }
        return result
    }

    private fun sortedEdgesOfCompleteGraph(points: Array<IntArray>): List<Edge> {
        val result = mutableListOf<Edge>()
        for (point1Index in 0 until points.lastIndex) {
            for (point2Index in (point1Index + 1) until points.size) {
                val edge = Edge(
                    point1Index,
                    point2Index,
                    manhattanDistance(points[point1Index], points[point2Index])
                )
                result.add(edge)
            }
        }
        result.sortBy { it.manhattanDistance }
        return result
    }

    private data class Edge(
        val point1Index: Int,
        val point2Index: Int,
        val manhattanDistance: Int
    )

    private fun manhattanDistance(point1: IntArray, point2: IntArray): Int {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1])
    }

    private class UnionFind(size: Int) {
        private val parent = IntArray(size) { it }
        private val rank = IntArray(size)

        fun union(x: Int, y: Int): Boolean {
            val xRoot = find(x)
            val yRoot = find(y)

            if (xRoot == yRoot) {
                return false
            }

            when {
                rank[xRoot] < rank[yRoot] -> parent[xRoot] = yRoot
                rank[xRoot] > rank[yRoot] -> parent[yRoot] = xRoot
                else -> {
                    parent[xRoot] = yRoot
                    rank[xRoot]++
                }
            }
            return true
        }

        private fun find(x: Int): Int {
            if (parent[x] != x) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }
    }
}