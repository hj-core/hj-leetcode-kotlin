package com.hj.leetcode.kotlin.problem2492

/**
 * LeetCode page: [2492. Minimum Score of a Path Between Two Cities](https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/);
 */
class Solution {
    // Complexity:
    // Time O(n + E) and Space O(E) where E is the length of roads.
    fun minScore(
        n: Int,
        roads: Array<IntArray>,
    ): Int {
        val roadMap = buildRoadMap(roads)
        val visited = BooleanArray(n + 1)
        var minScore = Int.MAX_VALUE

        dfs(1, roadMap, visited) { road ->
            minScore = minOf(minScore, road.distance)
        }
        check(visited[n])
        return minScore
    }

    private fun buildRoadMap(roads: Array<IntArray>): Map<Int, List<Road>> {
        val roadMap = hashMapOf<Int, MutableList<Road>>()
        for ((u, v, distance) in roads) {
            roadMap.computeIfAbsent(u) { mutableListOf() }.add(Road(u, v, distance))
            roadMap.computeIfAbsent(v) { mutableListOf() }.add(Road(v, u, distance))
        }
        return roadMap
    }

    private class Road(
        val fromCity: Int,
        val toCity: Int,
        val distance: Int,
    )

    private fun dfs(
        start: Int,
        roadMap: Map<Int, List<Road>>,
        visited: BooleanArray,
        onEachRoad: (road: Road) -> Unit,
    ) {
        if (visited[start]) {
            return
        }

        visited[start] = true
        for (road in roadMap[start] ?: return) {
            onEachRoad(road)
            dfs(road.toCity, roadMap, visited, onEachRoad)
        }
    }
}
