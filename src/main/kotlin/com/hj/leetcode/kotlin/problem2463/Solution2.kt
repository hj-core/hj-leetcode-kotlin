package com.hj.leetcode.kotlin.problem2463

import kotlin.math.abs
import kotlin.math.min

/**
 * LeetCode page: [2463. Minimum Total Distance Traveled](https://leetcode.com/problems/minimum-total-distance-traveled/);
 */
class Solution2 {
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

            for (startBot in sortedRobots.indices.reversed()) {
                val totalRobots = sortedRobots.size - startBot
                if (totalCapacity < totalRobots) {
                    dp[startBot] = impossible
                    continue
                }
                // Consider all possible numbers of robots assigned to startFac
                val maxAssigned = min(totalRobots, facLimits)
                dp[startBot] = prevDp[startBot] // No robot is assigned to startFac
                var accDistance = 0L // Distance of the robots assigned to startFac

                for (assigned in 1..maxAssigned) {
                    val currentBot = startBot + assigned - 1
                    accDistance += abs(facPosition - sortedRobots[currentBot])
                    if (prevDp[currentBot + 1] == impossible) {
                        continue
                    }
                    val distance = accDistance + prevDp[currentBot + 1]
                    dp[startBot] =
                        dp[startBot].let {
                            if (it == impossible) distance else min(it, distance)
                        }
                }
            }
        }
        return dp[0]
    }
}
