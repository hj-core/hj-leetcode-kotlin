package com.hj.leetcode.kotlin.problem2316

class Solution2 {
    /* Complexity:
     * Time O(n+E) and Space O(n) where E is the size of edges;
     */
    fun countPairs(n: Int, edges: Array<IntArray>): Long {
        val nodeUnionFind = UnionFind(n, edges)
        val componentSizes = sizesOfConnectedComponents(n, nodeUnionFind)
        return numUnreachablePairs(n, componentSizes)
    }

    private class UnionFind(numNodes: Int, edges: Array<IntArray>) {

        private val parent = IntArray(numNodes) { it }
        private val rank = IntArray(numNodes)

        init {
            for ((u, v) in edges) {
                union(u, v)
            }
        }

        private fun union(aNode: Int, bNode: Int) {
            val aParent = find(aNode)
            val bParent = find(bNode)
            if (aParent == bParent) return

            val aRank = rank[aParent]
            val bRank = rank[bParent]
            when {
                aRank < bRank -> parent[aParent] = bParent
                aRank > bRank -> parent[bParent] = aParent
                else -> {
                    parent[bParent] = aParent
                    rank[aParent]++
                }
            }
        }

        fun find(node: Int): Int {
            return if (node == parent[node]) node else find(parent[node])
        }
    }

    private fun sizesOfConnectedComponents(numNodes: Int, nodeUnionFind: UnionFind): List<Int> {
        val nodes = 0 until numNodes
        return nodes
            .groupingBy { nodeUnionFind.find(it) }
            .eachCount()
            .values
            .toList()
    }

    private fun numUnreachablePairs(numNodes: Int, componentSizes: List<Int>): Long {
        var remainingNodes = numNodes.toLong()
        var numPairs = 0L
        for (size in componentSizes) {
            remainingNodes -= size
            numPairs += size * remainingNodes
        }
        return numPairs
    }
}
