package com.hj.leetcode.kotlin.problem2463

import kotlin.math.abs
import kotlin.math.min

/**
 * LeetCode page: [2463. Minimum Total Distance Traveled](https://leetcode.com/problems/minimum-total-distance-traveled/);
 */
class Solution2 {
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
        // dp[startBot]_startFac::=
        // minimumTotalDistance(sortedRobot[startBot:], sortedFactories[startFac:])
        val dp = LongArray(robot.size + 1) { impossible }
        dp[robot.size] = 0 // Base case startFac= sortedFactories.size

        for (startFac in sortedFactories.indices.reversed()) {
            val totalCapacity = sortedFactories.size - startFac
            val minStartBot = (sortedRobots.size - totalCapacity).coerceAtLeast(0)

            for (startBot in 0..<minStartBot) {
                dp[startBot] = impossible
            }

            var temp = dp[robot.size] // dp[startBot+1]_(startFac+1)
            for (startBot in sortedRobots.lastIndex downTo minStartBot) {
                // There are two possible options: Assign startBot to startFac or not
                val assign = abs(sortedRobots[startBot] - sortedFactories[startFac]) + temp
                val notAssign = dp[startBot] // dp[startBot]_(startFac+1)
                val minDistance = if (notAssign == impossible) assign else min(assign, notAssign)
                dp[startBot] = minDistance.also { temp = dp[startBot] }
            }
        }
        return dp[0]
    }
}
