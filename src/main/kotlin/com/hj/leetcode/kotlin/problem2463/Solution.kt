package com.hj.leetcode.kotlin.problem2463

import kotlin.math.abs
import kotlin.math.min

/**
 * LeetCode page: [2463. Minimum Total Distance Traveled](https://leetcode.com/problems/minimum-total-distance-traveled/);
 */
class Solution {
    /* Complexity:
     * Time O(MN^2) and Space O(MN) where N is the size of robot and M is the size of factory.
     */
    fun minimumTotalDistance(
        robot: List<Int>,
        factory: Array<IntArray>,
    ): Long {
        val sortedRobots = robot.sorted()
        val sortedFactories =
            factory
                .sortedBy { (position, _) -> position }
                .flatMap { (position, limit) ->
                    sequence { repeat(limit) { yield(position) } }
                } // flatten to handle limits

        val impossible = -1L
        // dp[i]_j::= minimum total distance to repair sortedRobots[i:] with sortedFactories[j:]
        val dp = LongArray(robot.size + 1) { impossible }
        dp[robot.size] = 0 // Base case j= sortedFactories.size

        for (j in sortedFactories.indices.reversed()) {
            val capacity = sortedFactories.size - j
            var temp = dp[robot.size] // dp[i+1]_j+1

            for (i in sortedRobots.indices.reversed()) {
                val remainingRobots = robot.size - i
                if (remainingRobots > capacity) {
                    temp = dp[i].also { dp[i] = impossible }
                    continue
                }
                // There are two possible options:
                // Assign sortedRobot[i] to sortedFactory[j] or not
                val assign = abs(sortedRobots[i] - sortedFactories[j]) + temp
                val skip = dp[i] // dp[i]_j+1
                val subResult = if (skip == impossible) assign else min(assign, skip)
                dp[i] = subResult.also { temp = dp[i] }
            }
        }
        return dp[0]
    }
}
