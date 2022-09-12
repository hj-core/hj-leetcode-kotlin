package com.hj.leetcode.kotlin.problem1383

import java.util.*

/**
 * LeetCode page: [1383. Maximum Performance of a Team](https://leetcode.com/problems/maximum-performance-of-a-team/);
 */
class Solution {
    private data class Agile(val speed: Int, val efficiency: Int)

    /* Complexity:
     * Time O(NLogN) and Space O(N) where N equals n;
     */
    fun maxPerformance(n: Int, speed: IntArray, efficiency: IntArray, k: Int): Int {
        val sortedAgile = getAgileSortedByEfficiency(speed, efficiency)
        return getMaxPerformance(k, sortedAgile)
    }

    private fun getAgileSortedByEfficiency(speed: IntArray, efficiency: IntArray): List<Agile> {
        return speed
            .asSequence()
            .zip(efficiency.asSequence()) { spd, eff -> Agile(spd, eff) }
            .sortedBy { agile -> agile.efficiency }
            .toList()
    }

    private fun getMaxPerformance(maxTeamSize: Int, agileSortedByEfficiency: List<Agile>): Int {
        var maxPerformance = 0L
        val teamSpeedMinPq = PriorityQueue<Int>()
        var maxTeamSpeedSum = 0L
        for (index in agileSortedByEfficiency.indices.reversed()) {
            val currSpeed = agileSortedByEfficiency[index].speed
            val currEfficiency = agileSortedByEfficiency[index].efficiency
            teamSpeedMinPq.offer(currSpeed)
            maxTeamSpeedSum += currSpeed
            if (teamSpeedMinPq.size > maxTeamSize) maxTeamSpeedSum -= teamSpeedMinPq.poll()
            val leastMaxPerformance = maxTeamSpeedSum * currEfficiency
            maxPerformance = maxOf(maxPerformance, leastMaxPerformance)
        }
        return maxPerformance.toOutputFormat()
    }

    private fun Long.toOutputFormat(): Int {
        val requiredModulo = 1_000_000_007
        return (this % requiredModulo).toInt()
    }
}