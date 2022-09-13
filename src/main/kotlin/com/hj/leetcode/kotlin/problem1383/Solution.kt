package com.hj.leetcode.kotlin.problem1383

import java.util.*

/**
 * LeetCode page: [1383. Maximum Performance of a Team](https://leetcode.com/problems/maximum-performance-of-a-team/);
 */
class Solution {
    private data class Engineer(val speed: Int, val efficiency: Int)

    /* Complexity:
     * Time O(NLogN) and Space O(N) where N equals n;
     */
    fun maxPerformance(n: Int, speed: IntArray, efficiency: IntArray, k: Int): Int {
        val sortedEngineers = createEngineersSortedByEfficiency(speed, efficiency)
        return findMaxPerformance(k, sortedEngineers)
    }

    private fun createEngineersSortedByEfficiency(speed: IntArray, efficiency: IntArray): List<Engineer> {
        return speed
            .asSequence()
            .zip(efficiency.asSequence()) { spd, eff -> Engineer(spd, eff) }
            .sortedBy { engineer -> engineer.efficiency }
            .toList()
    }

    private fun findMaxPerformance(maxTeamSize: Int, engineersSortedByEfficiency: List<Engineer>): Int {
        var maxPerformance = 0L
        val teamSpeedMinPq = PriorityQueue<Int>()
        var maxTeamSpeedSum = 0L
        for (index in engineersSortedByEfficiency.indices.reversed()) {
            val currSpeed = engineersSortedByEfficiency[index].speed
            val currEfficiency = engineersSortedByEfficiency[index].efficiency
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