package com.hj.leetcode.kotlin.problem2492

/**
 * LeetCode page: [2492. Minimum Score of a Path Between Two Cities](https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/);
 */
class Solution2 {
    /* Complexity:
     * Time O(n+E) and Space O(n) where E is the size of roads;
     */
    fun minScore(n: Int, roads: Array<IntArray>): Int {
        val unionFind = buildUnionFindOfCity(n, roads)
        var minScore = Int.MAX_VALUE
        for ((u, _, distance) in roads) {
            if (unionFind.inSameUnion(u, 1) && distance < minScore) {
                minScore = distance
            }
        }
        return minScore
    }

    private fun buildUnionFindOfCity(numCities: Int, roads: Array<IntArray>): UnionFind {
        val unionFind = UnionFind(numCities + 1)
        for ((u, v, _) in roads) {
            unionFind.union(u, v)
        }
        return unionFind
    }

    private class UnionFind(size: Int) {

        private val parent = IntArray(size) { it }
        private val rank = IntArray(size)

        fun union(u: Int, v: Int) {
            val uParent = find(u)
            val vParent = find(v)
            if (uParent == vParent) return

            val uRank = rank[uParent]
            val vRank = rank[vParent]
            when {
                uRank > vRank -> parent[vParent] = uParent
                uRank < vRank -> parent[uParent] = vParent
                else -> {
                    parent[vParent] = uParent
                    rank[uParent]++
                }
            }
        }

        private fun find(city: Int): Int {
            return if (city == parent[city]) city else find(parent[city])
        }

        fun inSameUnion(u: Int, v: Int): Boolean = find(u) == find(v)
    }
}