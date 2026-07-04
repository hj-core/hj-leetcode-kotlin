package com.hj.leetcode.kotlin.problem2492

/**
 * LeetCode page: [2492. Minimum Score of a Path Between Two Cities](https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/);
 */
class Solution2 {
    // Complexity:
    // Time O(n+E) and Space O(n) where E is the length of roads.
    fun minScore(
        n: Int,
        roads: Array<IntArray>,
    ): Int {
        val cityUnionFind = newCityUnionFind(n, roads)
        check(cityUnionFind.inConnected(1, n))
        return minDistanceAmongReachableRoads(1, roads, cityUnionFind)
    }

    private fun newCityUnionFind(
        n: Int,
        roads: Array<IntArray>,
    ): UnionFind {
        val uf = UnionFind(n + 1)
        for ((u, v, _) in roads) {
            uf.union(u, v)
        }
        return uf
    }

    private class UnionFind(
        size: Int,
    ) {
        private val parent = IntArray(size) { it }
        private val rank = IntArray(size)

        fun union(
            u: Int,
            v: Int,
        ) {
            val parent1 = find(u)
            val parent2 = find(v)
            if (parent1 == parent2) {
                return
            }

            val rank1 = rank[parent1]
            val rank2 = rank[parent2]
            when {
                rank1 > rank2 -> {
                    parent[parent2] = parent1
                }

                rank1 < rank2 -> {
                    parent[parent1] = parent2
                }

                else -> {
                    parent[parent2] = parent1
                    rank[parent1]++
                }
            }
        }

        private fun find(city: Int): Int {
            if (parent[city] != city) {
                parent[city] = find(parent[city])
            }
            return parent[city]
        }

        fun inConnected(
            u: Int,
            v: Int,
        ): Boolean = find(u) == find(v)
    }

    private fun minDistanceAmongReachableRoads(
        start: Int,
        roads: Array<IntArray>,
        cityUnionFind: UnionFind,
    ): Int {
        var minDistance = Int.MAX_VALUE
        for ((u, _, distance) in roads) {
            if (cityUnionFind.inConnected(u, start) && distance < minDistance) {
                minDistance = distance
            }
        }
        return minDistance
    }
}
