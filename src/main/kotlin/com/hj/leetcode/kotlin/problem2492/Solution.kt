package com.hj.leetcode.kotlin.problem2492

/**
 * LeetCode page: [2492. Minimum Score of a Path Between Two Cities](https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/);
 */
class Solution {
    /* Complexity:
     * Time O(E) and Space O(E) where E is the size of roads;
     */
    fun minScore(n: Int, roads: Array<IntArray>): Int {
        val roadMap = convertToRoadMap(roads)
        var minScore = Int.MAX_VALUE
        var isCityNReachable = false

        dfs(1, roadMap) { road ->
            if (road.distance < minScore) minScore = road.distance
            if (road.destination == n) isCityNReachable = true
        }

        check(isCityNReachable)
        return minScore
    }

    private fun convertToRoadMap(roads: Array<IntArray>): Map<Int, List<Road>> {
        val roadMap = hashMapOf<Int, MutableList<Road>>()
        for ((u, v, distance) in roads) {
            roadMap.computeIfAbsent(u) { mutableListOf() }.add(Road(u, v, distance))
            roadMap.computeIfAbsent(v) { mutableListOf() }.add(Road(v, u, distance))
        }
        return roadMap
    }

    private class Road(val origin: Int, val destination: Int, val distance: Int)

    private fun dfs(
        origin: Int,
        roadMap: Map<Int, List<Road>>,
        visited: MutableSet<Int> = hashSetOf(),
        sideEffect: (road: Road) -> Unit
    ) {
        if (origin in visited) return

        visited.add(origin)
        val roads = roadMap[origin] ?: emptyList()
        for (road in roads) {
            sideEffect(road)
            dfs(road.destination, roadMap, visited, sideEffect)
        }
    }
}