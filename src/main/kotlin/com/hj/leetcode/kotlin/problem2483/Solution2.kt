package com.hj.leetcode.kotlin.problem2483

/**
 * LeetCode page: [2483. Minimum Penalty for a Shop](https://leetcode.com/problems/minimum-penalty-for-a-shop/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of customers.
    fun bestClosingTime(customers: String): Int {
        // We minimize an indirect goal: penalty - totalY
        var penalty2 = 0
        var minPenalty2 = 0
        var bestHour = 0

        for ((t, c) in customers.withIndex()) {
            if (c == 'Y') {
                penalty2--
            } else {
                penalty2++
            }

            if (penalty2 < minPenalty2) {
                minPenalty2 = penalty2
                bestHour = t + 1
            }
        }

        return bestHour
    }
}
