package com.hj.leetcode.kotlin.problem1335

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [1335. Minimum Difficulty of a Job Schedule](https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/);
 *
 * TODO : There is solution with time complexity O(Nd) [(see Ref)](https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/discuss/490316/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2 * d) and Space O(N) where N is the size of jobDifficulty;
     */
    fun minDifficulty(jobDifficulty: IntArray, d: Int): Int {
        val n = jobDifficulty.size
        if (n < d) {
            return -1
        }

        // dp[i]@d ::= minDifficulty(jobDifficulty[i:], d)
        val dp = IntArray(n)

        // Base cases at d = 1
        dp[n - 1] = jobDifficulty[n - 1]
        for (index in n - 2 downTo 0) {
            dp[index] = max(dp[index + 1], jobDifficulty[index])
        }

        // Update dp@d from dp@d-1
        repeat(d - 1) {
            val currentDay = it + 1
            for (firstDayStart in 0..<n - currentDay) {
                var subresult = Int.MAX_VALUE
                var firstDayCost = jobDifficulty[firstDayStart]
                for (firstDayEnd in firstDayStart..<n - currentDay) {
                    firstDayCost = max(firstDayCost, jobDifficulty[firstDayEnd])
                    subresult = min(subresult, firstDayCost + dp[firstDayEnd + 1])
                }
                dp[firstDayStart] = subresult
            }
        }
        return dp[0]
    }
}