package com.hj.leetcode.kotlin.problem1319

/**
 * LeetCode page: [1319. Number of Operations to Make Network Connected](https://leetcode.com/problems/number-of-operations-to-make-network-connected/);
 */
class Solution2 {
    /* Complexity:
     * Time O(E) and Space O(n) where E is the size of connections;
     */
    fun makeConnected(n: Int, connections: Array<IntArray>): Int {
        val numCables = connections.size
        if (numCables < n - 1) return -1

        val computerUnionFind = UnionFind(n, connections)
        val numConnectedComponents = numConnectedComponents(computerUnionFind)
        return numConnectedComponents - 1
    }

    private class UnionFind(numComputers: Int, connections: Array<IntArray>) {

        private val parent = IntArray(numComputers) { it }
        private val rank = IntArray(numComputers)

        init {
            for ((aComputer, bComputer) in connections) {
                union(aComputer, bComputer)
            }
        }

        private fun union(aComputer: Int, bComputer: Int) {
            val aParent = find(aComputer)
            val bParent = find(bComputer)
            if (aParent == bParent) return

            val aRank = rank[aComputer]
            val bRank = rank[bComputer]
            when {
                aRank < bRank -> parent[aParent] = bParent
                aRank > bRank -> parent[bParent] = aParent
                else -> {
                    parent[aParent] = bParent
                    rank[bParent]++
                }
            }
        }

        private tailrec fun find(computer: Int): Int {
            val isRoot = parent[computer] == computer
            return if (isRoot) computer else find(parent[computer])
        }

        fun numUnions(): Int {
            val computers = parent.indices
            return computers.count { it == parent[it] }
        }
    }

    private fun numConnectedComponents(computerUnionFind: UnionFind): Int {
        return computerUnionFind.numUnions()
    }
}