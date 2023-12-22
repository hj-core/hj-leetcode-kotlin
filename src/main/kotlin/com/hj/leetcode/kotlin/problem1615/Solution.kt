package com.hj.leetcode.kotlin.problem1615

/**
 * LeetCode page: [1615. Maximal Network Rank](https://leetcode.com/problems/maximal-network-rank/);
 */
class Solution {
    /* Complexity:
     * Time O(n^2) and Space O(n+E) where E is the size of roads;
     */
    fun maximalNetworkRank(n: Int, roads: Array<IntArray>): Int {
        val roadsSet = roadsSet(roads)
        val cityDegrees = cityDegrees(n, roads)
        var result = 0

        forAllCityPairs(n) { aCity, bCity ->
            val hasDirectRoad = Pair(aCity, bCity) in roadsSet || Pair(bCity, aCity) in roadsSet
            val pairRank =
                cityDegrees[aCity] + cityDegrees[bCity] - (if (hasDirectRoad) 1 else 0)
            result = maxOf(result, pairRank)
        }
        return result
    }

    private fun roadsSet(roads: Array<IntArray>): Set<Pair<Int, Int>> {
        val result = hashSetOf<Pair<Int, Int>>()
        for ((u, v) in roads) {
            result.add(Pair(u, v))
        }
        return result
    }

    private fun cityDegrees(n: Int, roads: Array<IntArray>): IntArray {
        val result = IntArray(n)
        for ((u, v) in roads) {
            result[u]++
            result[v]++
        }
        return result
    }

    private inline fun forAllCityPairs(n: Int, onEachPair: (aCity: Int, bCity: Int) -> Unit) {
        for (aCity in 0 until n) {
            for (bCity in 0 until aCity) {
                onEachPair(aCity, bCity)
            }
        }
    }
}