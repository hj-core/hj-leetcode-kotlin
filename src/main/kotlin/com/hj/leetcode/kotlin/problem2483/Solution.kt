package com.hj.leetcode.kotlin.problem2483

/**
 * LeetCode page: [2483. Minimum Penalty for a Shop](https://leetcode.com/problems/minimum-penalty-for-a-shop/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of customers.
    fun bestClosingTime(customers: String): Int {
        var penaltyY = customers.count { it == 'Y' }
        var penaltyN = 0
        var bestHour = 0
        var minPenalty = penaltyY

        for ((t, c) in customers.withIndex()) {
            if (c == 'Y') {
                penaltyY--
            } else {
                penaltyN++
            }

            val penalty = penaltyY + penaltyN
            if (penalty < minPenalty) {
                minPenalty = penalty
                bestHour = t + 1
            }
        }

        return bestHour
    }
}
