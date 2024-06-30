package com.hj.leetcode.kotlin.problem547

/**
 * LeetCode page: [547. Number of Provinces](https://leetcode.com/problems/number-of-provinces/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the size of isConnected;
     */
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val unionFind = UnionFind(isConnected.size)
        for (aCity in 0 until isConnected.lastIndex) {
            for (bCity in aCity + 1 until isConnected.size) {
                if (isConnected[aCity][bCity] == 1) {
                    unionFind.union(aCity, bCity)
                }
            }
        }
        return unionFind.numUnions()
    }

    private class UnionFind(size: Int) {

        private val parent = IntArray(size) { it }
        private val rank = IntArray(size)
        private var numUnions = size

        fun union(a: Int, b: Int) {
            val aRoot = find(a)
            val bRoot = find(b)
            if (aRoot == bRoot) {
                return
            }

            when {
                rank[aRoot] < rank[bRoot] -> parent[aRoot] = bRoot
                rank[aRoot] > rank[bRoot] -> parent[bRoot] = aRoot
                else -> {
                    parent[bRoot] = aRoot
                    rank[aRoot]++
                }
            }
            numUnions--
        }

        private tailrec fun find(a: Int): Int {
            return if (parent[a] == a) a else find(parent[a])
        }

        fun numUnions(): Int {
            return numUnions
        }
    }
}