package com.hj.leetcode.kotlin.problem983

/**
 * LeetCode page: [983. Minimum Cost For Tickets](https://leetcode.com/problems/minimum-cost-for-tickets/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of days.
     */
    fun mincostTickets(
        days: IntArray,
        costs: IntArray,
    ): Int {
        // dp[i]::= mincostTickets(days[i:], costs)
        val dp = IntArray(days.size + 1)
        // First index of days that a pass bought at days[i] became invalid
        var weekInvalid = days.size
        var monthInvalid = days.size

        for (i in days.indices.reversed()) {
            while (days[i] + 7 <= days[weekInvalid - 1]) {
                weekInvalid--
            }
            while (days[i] + 30 <= days[monthInvalid - 1]) {
                monthInvalid--
            }

            dp[i] =
                minOf(
                    costs[0] + dp[i + 1],
                    costs[1] + dp[weekInvalid],
                    costs[2] + dp[monthInvalid],
                )
        }
        return dp[0]
    }
}
