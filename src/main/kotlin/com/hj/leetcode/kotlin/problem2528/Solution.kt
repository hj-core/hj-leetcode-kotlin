package com.hj.leetcode.kotlin.problem2528

/**
 * LeetCode page: [2528. Maximize the Minimum Powered City](https://leetcode.com/problems/maximize-the-minimum-powered-city/);
 */
class Solution {
    // Complexity:
    // Time O(NLog(M+k)) and Space O(N) where N is the length of
    // stations and M is the minimum value in stations.
    fun maxPower(
        stations: IntArray,
        r: Int,
        k: Int,
    ): Long {
        val powers = calcPowers(stations, r)

        if (stations.size <= 1 + r * 2) {
            return powers.min() + k
        }

        // Binary search on the maximum minimum power, which is
        // in range [left-1, right].
        var left = 1L
        var right = powers.min() + k

        while (left <= right) {
            val mid = left + (right - left) / 2
            if (canAchieveMinPower(powers, r, k, mid)) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return right
    }

    private fun calcPowers(
        stations: IntArray,
        r: Int,
    ): LongArray {
        val n = stations.size
        val result = LongArray(n)

        // Sliding window
        var power =
            (0..<r).fold(0L) { acc, i -> acc + stations[i] }

        for (i in stations.indices) {
            if (r < i) {
                power -= stations[i - r - 1]
            }
            if (i + r < n) {
                power += stations[i + r]
            }

            result[i] = power
        }
        return result
    }

    private fun canAchieveMinPower(
        powers: LongArray,
        r: Int,
        k: Int,
        targetMin: Long,
    ): Boolean {
        val n = powers.size
        val dPowers = LongArray(n) // Sweep line
        var remainingK = k.toLong()

        for ((i, power) in powers.withIndex()) {
            if (i > 0) {
                dPowers[i] += dPowers[i - 1]
            }

            val shortage =
                maxOf(0, targetMin - power - dPowers[i])
            if (remainingK < shortage) {
                return false
            }

            remainingK -= shortage
            dPowers[i] += shortage
            val end = i + r * 2 + 1
            if (end < n) {
                dPowers[end] -= shortage
            }
        }
        return true
    }
}
