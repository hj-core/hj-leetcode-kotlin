package com.hj.leetcode.kotlin.problem2463

import kotlin.math.abs
import kotlin.math.min

/**
 * LeetCode page: [2463. Minimum Total Distance Traveled](https://leetcode.com/problems/minimum-total-distance-traveled/);
 */
class Solution {
    /* Complexity:
     * Time O(MN^2) and Space O(M+N) where N is the length of robot and M is the length of factory.
     */
    fun minimumTotalDistance(
        robot: List<Int>,
        factory: Array<IntArray>,
    ): Long {
        val sortedRobots = robot.sorted()
        val sortedFactories = factory.sortedBy { (position, limits) -> position }
        val impossible = -1L
        // dp[startBot]_startFac::=
        // minimumTotalDistance(sortedRobot[startBot:], sortedFactories[startFac:])
        val dp = LongArray(sortedRobots.size + 1) { impossible }
        dp[sortedRobots.size] = 0L // base case startFac= sortedFactories.size
        var totalCapacity = 0

        for (startFac in sortedFactories.indices.reversed()) {
            val (facPosition, facLimits) = sortedFactories[startFac]
            totalCapacity += facLimits
            val prevDp = dp.clone() // dp_(starFac+1)
            val minStartBot = (sortedRobots.size - totalCapacity).coerceAtLeast(0)

            for (startBot in 0..<minStartBot) {
                dp[startBot] = impossible
            }

            for (startBot in minStartBot..<sortedRobots.size) {
                // Consider all possible numbers of robots assigned to startFac
                dp[startBot] = prevDp[startBot] // No robot is assigned to startFac
                val maxAssign = min(facLimits, sortedRobots.size - startBot)
                var assignDistance = 0L // Total distance induced by the robots assigned to startFac

                for (k in 1..maxAssign) {
                    val newAssign = startBot + k - 1
                    assignDistance += abs(facPosition - sortedRobots[newAssign])
                    if (prevDp[newAssign + 1] == impossible) {
                        continue
                    }

                    val minDistance = assignDistance + prevDp[newAssign + 1]
                    dp[startBot] =
                        dp[startBot].let {
                            if (it == impossible) minDistance else min(it, minDistance)
                        }
                }
            }
        }
        return dp[0]
    }
}
