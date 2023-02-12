package com.hj.leetcode.kotlin.problem2477

/**
 * LeetCode page: [2477. Minimum Fuel Cost to Report to the Capital](https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(n) where n is the number of cities and equals roads.size + 1;
     */
    fun minimumFuelCost(roads: Array<IntArray>, seats: Int): Long {
        val edges = buildEdges(roads)
        val argumentation = getNodeArgumentation(0, edges, seats, -1)
        return argumentation.totalFuelSpent
    }

    private fun buildEdges(roads: Array<IntArray>): Map<Int, List<Int>> {
        val edges = hashMapOf<Int, MutableList<Int>>()
        for ((u, v) in roads) {
            edges.computeIfAbsent(u) { mutableListOf() }.add(v)
            edges.computeIfAbsent(v) { mutableListOf() }.add(u)
        }
        return edges
    }

    private fun getNodeArgumentation(
        node: Int,
        edges: Map<Int, List<Int>>,
        seats: Int,
        parentNode: Int
    ): NodeArgumentation {

        val children = edges[node] ?: emptyList()

        if (children.isEmpty()) {
            return NodeArgumentation(0, 1, 1)
        }

        var totalFuelSpent = 0L
        var numPassengersLeavingHere = 0

        for (child in children) {
            if (child == parentNode) continue

            val argumentation = getNodeArgumentation(child, edges, seats, node)
            numPassengersLeavingHere += argumentation.numPassengersLeavingHere
            totalFuelSpent += argumentation.totalFuelSpent + argumentation.numCarsLeavingHere
        }

        numPassengersLeavingHere++
        val numCarsLeavingHere = getMinRequiredCars(numPassengersLeavingHere, seats)
        return NodeArgumentation(totalFuelSpent, numPassengersLeavingHere, numCarsLeavingHere)
    }

    private class NodeArgumentation(
        val totalFuelSpent: Long,
        val numPassengersLeavingHere: Int,
        val numCarsLeavingHere: Int
    )

    private fun getMinRequiredCars(totalPassengers: Int, seats: Int): Int {
        var minCars = totalPassengers / seats
        if (minCars * seats < totalPassengers) minCars++
        return minCars
    }
}