package com.hj.leetcode.kotlin.problem959

/**
 * LeetCode page: [959. Regions Cut By Slashes](https://leetcode.com/problems/regions-cut-by-slashes/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N^2) where N is the number of rows and columns in grid;
     */
    fun regionsBySlashes(grid: Array<String>): Int {
        val (nodes, edges) = buildGraph(grid)
        val uf = UnionFind()
        uf.makeSet(nodes)

        var result = nodes.size
        for ((nodeA, nodeB) in edges) {
            if (uf.union(nodeA, nodeB)) {
                result -= 1
            }
        }
        return result
    }

    private data class Graph(val nodes: Set<String>, val edges: List<Pair<String, String>>)

    private fun buildGraph(grid: Array<String>): Graph {
        val n = grid.size
        val nodes = mutableSetOf<String>()
        val edges = mutableListOf<Pair<String, String>>()

        for (r in 0..<n) {
            for (c in 0..<n) {
                when (grid[r][c]) {
                    ' ' -> {
                        val name = "$r,$c"
                        nodes.add(name)
                        if (r - 1 >= 0) {
                            when (grid[r - 1][c]) {
                                ' ' -> edges.add(Pair(name, "${r - 1},$c"))
                                '/' -> edges.add(Pair(name, "${r - 1},$c,R"))
                                '\\' -> edges.add(Pair(name, "${r - 1},$c,L"))
                            }
                        }
                        if (c - 1 >= 0) {
                            when (grid[r][c - 1]) {
                                ' ' -> edges.add(Pair(name, "$r,${c - 1}"))
                                else -> edges.add(Pair(name, "$r,${c - 1},R"))
                            }
                        }
                    }

                    '/' -> {
                        val leftName = "$r,$c,L"
                        nodes.add(leftName)
                        if (r - 1 >= 0) {
                            when (grid[r - 1][c]) {
                                ' ' -> edges.add(Pair(leftName, "${r - 1},$c"))
                                '/' -> edges.add(Pair(leftName, "${r - 1},$c,R"))
                                '\\' -> edges.add(Pair(leftName, "${r - 1},$c,L"))
                            }
                        }
                        if (c - 1 >= 0) {
                            when (grid[r][c - 1]) {
                                ' ' -> edges.add(Pair(leftName, "$r,${c - 1}"))
                                else -> edges.add(Pair(leftName, "$r,${c - 1},R"))
                            }
                        }

                        val rightName = "$r,$c,R"
                        nodes.add(rightName)
                    }

                    '\\' -> {
                        val leftName = "$r,$c,L"
                        nodes.add(leftName)
                        if (c - 1 >= 0) {
                            when (grid[r][c - 1]) {
                                ' ' -> edges.add(Pair(leftName, "$r,${c - 1}"))
                                else -> edges.add(Pair(leftName, "$r,${c - 1},R"))
                            }
                        }

                        val rightName = "$r,$c,R"
                        nodes.add(rightName)
                        if (r - 1 >= 0) {
                            when (grid[r - 1][c]) {
                                ' ' -> edges.add(Pair(rightName, "${r - 1},$c"))
                                '/' -> edges.add(Pair(rightName, "${r - 1},$c,R"))
                                '\\' -> edges.add(Pair(rightName, "${r - 1},$c,L"))
                            }
                        }
                    }
                }
            }
        }
        return Graph(nodes, edges)
    }

    private class UnionFind {
        private val parents = mutableMapOf<String, String>()
        private val ranks = mutableMapOf<String, Int>()

        fun makeSet(nodes: Collection<String>) {
            for (node in nodes) {
                parents[node] = node
                ranks[node] = 0
            }
        }

        fun find(node: String): String {
            if (parents[node] != node) {
                parents[node] = find(checkNotNull(parents[node]))
            }
            return checkNotNull(parents[node])
        }

        fun union(nodeA: String, nodeB: String): Boolean {
            val parentA = find(nodeA)
            val parentB = find(nodeB)
            if (parentA == parentB) {
                return false
            }

            val rankParentA = checkNotNull(ranks[parentA])
            val rankParentB = checkNotNull(ranks[parentB])

            when {
                rankParentA < rankParentB -> parents[parentA] = parentB
                rankParentA > rankParentB -> parents[parentB] = parentA
                else -> {
                    parents[parentB] = parentA
                    ranks[parentA] = rankParentA + 1
                }
            }
            return true
        }
    }
}