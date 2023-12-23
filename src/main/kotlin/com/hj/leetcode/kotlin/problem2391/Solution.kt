package com.hj.leetcode.kotlin.problem2391

/**
 * LeetCode page: [2391. Minimum Amount of Time to Collect Garbage](https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage/);
 */
class Solution {
    /* Complexity:
     * Time O(L) and Space O(1) where L id the flattened length of garbage;
     */
    fun garbageCollection(garbage: Array<String>, travel: IntArray): Int {
        val pickUpTime = garbage.sumOf { it.length }
        val truckTypes = charArrayOf('M', 'P', 'G')
        val finalPositions = truckTypes.map { type ->
            garbage
                .indexOfLast { assortment -> assortment.contains(type) }
                .coerceAtLeast(0)
        }
        return pickUpTime + travelTime(finalPositions, travel)
    }

    private fun travelTime(finalPositions: List<Int>, travel: IntArray): Int {
        val sortedPositions = finalPositions.sorted()
        var result = 0
        for (index in sortedPositions.indices) {
            val routeStart = sortedPositions.getOrElse(index - 1) { 0 }
            val routeEnd = sortedPositions[index]
            val routeTravelTime = (routeStart..<routeEnd).sumOf { travel[it] }
            val travellingTrucks = sortedPositions.size - index
            result += routeTravelTime * travellingTrucks
        }
        return result
    }
}