package com.hj.leetcode.kotlin.problem1383

import java.util.*

/**
 * LeetCode page: [1383. Maximum Performance of a Team](https://leetcode.com/problems/maximum-performance-of-a-team/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N equals n;
     */
    fun maxPerformance(n: Int, speed: IntArray, efficiency: IntArray, k: Int): Int {
        val sortedEngineers = getEngineersSortedByEfficiency(speed, efficiency)

        var maxPerformance = 0L
        val teamSpeedMinPq = PriorityQueue<Int>()
        var sumOfTeamSpeed = 0L

        for (index in sortedEngineers.indices.reversed()) {
            val currSpeed = sortedEngineers[index].speed
            val currEfficiency = sortedEngineers[index].efficiency

            teamSpeedMinPq.offer(currSpeed)
            sumOfTeamSpeed += currSpeed
            val teamSizeExceeded = teamSpeedMinPq.size > k
            if (teamSizeExceeded) sumOfTeamSpeed -= teamSpeedMinPq.poll()

            val leastMaxPerformance = sumOfTeamSpeed * currEfficiency
            maxPerformance = maxOf(maxPerformance, leastMaxPerformance)
        }
        return maxPerformance.toOutputFormat()
    }

    private fun getEngineersSortedByEfficiency(speed: IntArray, efficiency: IntArray): List<Engineer> {
        return speed
            .asSequence()
            .zip(efficiency.asSequence()) { spd, eff -> Engineer(spd, eff) }
            .sortedBy { engineer -> engineer.efficiency }
            .toList()
    }

    private data class Engineer(val speed: Int, val efficiency: Int)

    private fun Long.toOutputFormat(): Int {
        val requiredModulo = 1_000_000_007
        return (this % requiredModulo).toInt()
    }
}