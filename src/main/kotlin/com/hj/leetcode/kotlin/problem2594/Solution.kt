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
        val carsCal = CarsCalculator(ranks, maxRank)

        // Binary search on the repairing time.
        // The final result is in the range [low, high+1].
        var low = 1L
        var high = timeUpperBound(ranks.size, maxRank, cars)

        while (low <= high) {
            val mid = (low + high) ushr 1
            if (carsCal.teamRepairedCars(mid) >= cars) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return low
    }

    private fun CarsCalculator(
        ranks: IntArray,
        maxRank: Int,
    ): CarsCalculator =
        if (ranks.size <= maxRank * 2) {
            NormalCal(ranks)
        } else {
            val rankFreq = IntArray(maxRank + 1)
            for (rank in ranks) {
                rankFreq[rank]++
            }
            FreqBasedCal(rankFreq)
        }

    interface CarsCalculator {
        fun teamRepairedCars(time: Long): Long

        fun singleRepairedCars(
            rank: Int,
            time: Long,
        ): Long = sqrt((time / rank).toDouble()).toLong()
    }

    private class NormalCal(
        val ranks: IntArray,
    ) : CarsCalculator {
        override fun teamRepairedCars(time: Long): Long = ranks.sumOf { singleRepairedCars(it, time) }
    }

    private class FreqBasedCal(
        val rankFreq: IntArray, // rankFreq[i]::= the number of mechanics with rank i
    ) : CarsCalculator {
        override fun teamRepairedCars(time: Long): Long =
            rankFreq.foldIndexed(0L) { rank, acc, freq ->
                if (freq == 0) acc else acc + singleRepairedCars(rank, time) * freq
            }
    }

    private fun timeUpperBound(
        mechanics: Int,
        maxRank: Int,
        cars: Int,
    ): Long = (1L + cars / mechanics).let { maxRank * it * it }
}
