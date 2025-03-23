package com.hj.leetcode.kotlin.problem1976

import java.util.TreeSet

/**
 * LeetCode page: [1976. Number of Ways to Arrive at Destination](https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/);
 */
class Solution {
    // Complexity:
    // Time O((n+E)*Log(n)) and Space O(n+E) where E is the length of roads.
    fun countPaths(
        n: Int,
        roads: Array<IntArray>,
    ): Int {
        val mod = 1_000_000_007
        val adjacencyList = adjacencyList(n, roads)
        val minTimes = LongArray(n) { -1 } // minTime[x]::= the minimum time to arrive at x from 0
        val ways = IntArray(n) // ways[x]::= the modded ways to arrive at x from 0 in minimum time

        // Pq for the Dijkstra algorithm.
        // Must also compare the node ID because the TreeSet uses the comparator to determine uniqueness.
        // Additionally, one should be cautious about the order of updating the pq and the minTimes.
        val pq = TreeSet<Int>(compareBy({ minTimes[it] }, { it }))

        minTimes[0] = 0
        ways[0] = 1
        pq.add(0)

        while (pq.isNotEmpty()) {
            val curr = checkNotNull(pq.pollFirst())

            for (road in adjacencyList[curr]) {
                val next = curr xor road[0] xor road[1]
                val timeToNext = minTimes[curr] + road[2]

                when {
                    minTimes[next] == -1L -> {
                        minTimes[next] = timeToNext
                        ways[next] = ways[curr]
                        pq.add(next)
                    }

                    minTimes[next] == timeToNext -> {
                        ways[next] = (ways[next] + ways[curr]) % mod
                    }

                    minTimes[next] > timeToNext -> {
                        pq.remove(next)
                        minTimes[next] = timeToNext
                        ways[next] = ways[curr]
                        pq.add(next)
                    }
                }
            }
        }
        return ways[n - 1]
    }

    private fun adjacencyList(
        n: Int,
        roads: Array<IntArray>,
    ): List<List<IntArray>> {
        val result = List(n) { mutableListOf<IntArray>() }
        for (road in roads) {
            result[road[0]].add(road)
            result[road[1]].add(road)
        }
        return result
    }
}
