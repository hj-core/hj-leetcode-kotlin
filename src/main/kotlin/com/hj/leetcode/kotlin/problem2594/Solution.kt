package com.hj.leetcode.kotlin.problem2594

import kotlin.math.sqrt

/**
 * LeetCode page: [2594. Minimum Time to Repair Cars](https://leetcode.com/problems/minimum-time-to-repair-cars/);
 */
class Solution {
    // Complexity:
    // Time O(N + min(N,M)*Log(M*(cars/N)^2) and Space O(M)
    // where N is the length of ranks, and M is the maximum value in ranks.
    fun repairCars(
        ranks: IntArray,
        cars: Int,
    ): Long {
        val maxRank = ranks.max()
        val mechanics = mechanics(ranks, maxRank)

        // Binary search on the repairing time.
        // The final result is in the range [low, high+1].
        var low = 1L
        var high = timeUpperBound(ranks.size, maxRank, cars)

        while (low <= high) {
            val mid = (low + high) ushr 1
            if (mechanics.totalRepaired(mid) >= cars) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return low
    }

    private fun mechanics(
        ranks: IntArray,
        maxRank: Int,
    ): CarMechanics =
        if (ranks.size <= maxRank * 2) {
            Mechanics(ranks)
        } else {
            val rankCounts = IntArray(maxRank + 1)
            for (rank in ranks) {
                rankCounts[rank]++
            }
            MechanicGroups(rankCounts)
        }

    interface CarMechanics {
        fun totalRepaired(time: Long): Long

        fun individualRepaired(
            rank: Int,
            time: Long,
        ): Long = sqrt((time / rank).toDouble()).toLong()
    }

    private class Mechanics(
        val ranks: IntArray,
    ) : CarMechanics {
        override fun totalRepaired(time: Long): Long = ranks.sumOf { individualRepaired(it, time) }
    }

    private class MechanicGroups(
        val rankCounts: IntArray, // rankCounts[i]::= the number of mechanics with rank i
    ) : CarMechanics {
        override fun totalRepaired(time: Long): Long =
            rankCounts.foldIndexed(0L) { rank, acc, count ->
                if (count == 0) acc else acc + individualRepaired(rank, time) * count
            }
    }

    private fun timeUpperBound(
        mechanics: Int,
        maxRank: Int,
        cars: Int,
    ): Long = (1L + cars / mechanics).let { maxRank * it * it }
}
