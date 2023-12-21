package com.hj.leetcode.kotlin.problem1466

/**
 * LeetCode page: [1466. Reorder Routes to Make All Paths Lead to the City Zero](https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(n);
     */
    fun minReorder(n: Int, connections: Array<IntArray>): Int {
        val cityNeighbours = cityToItsNeighbours(n, connections)
        val cityDistances = cityToDistanceFromCapital(n, 0, cityNeighbours)
        return numReorderRoutes(connections, cityDistances)
    }

    private fun cityToItsNeighbours(numCities: Int, connections: Array<IntArray>): List<List<Int>> {
        val neighbours = List(numCities) { mutableListOf<Int>() }
        for ((origin, destination) in connections) {
            neighbours[origin].add(destination)
            neighbours[destination].add(origin)
        }
        return neighbours
    }

    private fun cityToDistanceFromCapital(
        numCities: Int,
        capital: Int,
        cityNeighbours: List<List<Int>>
    ): IntArray {

        val cityDistances = IntArray(numCities) { numCities }
        val destinationStack = ArrayDeque<Int>()

        cityDistances[capital] = 0
        destinationStack.addLast(capital)
        while (destinationStack.isNotEmpty()) {
            val destination = destinationStack.removeLast()
            val origins = cityNeighbours[destination]
            for (origin in origins) {
                val distance = cityDistances[destination] + 1
                val hasVisited = distance > cityDistances[origin]
                if (hasVisited) continue
                cityDistances[origin] = distance
                destinationStack.addLast(origin)
            }
        }
        return cityDistances
    }

    private fun numReorderRoutes(connections: Array<IntArray>, cityDistances: IntArray): Int {
        return connections.count { (origin, destination) ->
            cityDistances[origin] < cityDistances[destination]
        }
    }
}