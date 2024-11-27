package com.hj.leetcode.kotlin.problem3243

/**
 * LeetCode page: [3243. Shortest Distance After Road Addition Queries I](https://leetcode.com/problems/shortest-distance-after-road-addition-queries-i/);
 */
class Solution {
    /* Complexity:
     * Time O(M*n^2) for dfsPropagate, O(M*(M+n)) for bfsPropagate,
     * and Space(n+M) where M is the length of queries.
     */
    fun shortestDistanceAfterQueries(
        n: Int,
        queries: Array<IntArray>,
    ): IntArray {
        val roadNet = RoadNet(n)
        return IntArray(queries.size) {
            val (from, to) = queries[it]
            roadNet.addRoad(from, to)
            roadNet.shortestDistance()
        }
    }
}

private class RoadNet(
    val n: Int,
) {
    private val dp = IntArray(n) { it } // dp[i]::= shortest distance from 0 to i
    private val adjacencyList = Array(n) { mutableListOf(it + 1) }

    init {
        adjacencyList[n - 1].removeLast()
    }

    fun addRoad(
        from: Int,
        to: Int,
    ) {
        adjacencyList[from].add(to)
        if (dp[from] + 1 < dp[to]) {
            dp[to] = dp[from] + 1
            dfsPropagate(to)
        }
    }

    private fun dfsPropagate(from: Int) {
        for (to in adjacencyList[from]) {
            if (dp[from] + 1 < dp[to]) {
                dp[to] = dp[from] + 1
                dfsPropagate(to)
            }
        }
    }

    private fun bfsPropagate(city: Int) {
        val improved = mutableSetOf(city)
        for (from in city..<n) {
            if (from !in improved) {
                continue
            }

            for (to in adjacencyList[from]) {
                if (dp[from] + 1 < dp[to]) {
                    dp[to] = dp[from] + 1
                    improved.add(to)
                }
            }
        }
    }

    fun shortestDistance(): Int = dp.last()
}
