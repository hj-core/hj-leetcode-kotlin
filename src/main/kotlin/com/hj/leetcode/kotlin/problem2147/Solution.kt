package com.hj.leetcode.kotlin.problem2147

/**
 * LeetCode page: [2147. Number of Ways to Divide a Long Corridor](https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of corridor.
    fun numberOfWays(corridor: String): Int {
        val modulo = 1_000_000_007
        var ways = 1L
        var totalSeats = 0
        var lastSeat = -1

        for (i in corridor.indices) {
            if (corridor[i] == 'P') {
                continue
            }

            totalSeats++
            if (totalSeats > 1 && totalSeats and 1 == 1) {
                ways = ways * (i - lastSeat) % modulo
            }
            lastSeat = i
        }

        if (totalSeats < 2 || totalSeats and 1 == 1) {
            return 0
        }
        return ways.toInt()
    }
}
