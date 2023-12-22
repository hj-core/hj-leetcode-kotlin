package com.hj.leetcode.kotlin.problem1269

/**
 * LeetCode page: [1269. Number of Ways to Stay in the Same Place After Some Steps](https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/);
 */
class Solution2 {
    /* Complexity:
     * Time O(step * N) and Space O(N) where N represents the minimum
     * between steps and arrLen;
     */
    fun numWays(steps: Int, arrLen: Int): Int {
        val modulo = 1_000_000_007
        /* dp[i]@step_j::=
         * number of ways back to index 0 from index_i using j steps
         */
        val dp = IntArray(minOf(arrLen, steps + 1))
        dp[0] = 1 // base case dp[0]@step_0

        for (numSteps in 1..steps) {
            var waysFromLeft = 0
            for (i in 0..minOf(numSteps, dp.lastIndex)) {
                var ways = waysFromLeft
                ways = (ways + dp[i]) % modulo
                ways = (ways + dp.getOrElse(i + 1) { 0 }) % modulo
                waysFromLeft = dp[i]
                dp[i] = ways
            }
        }
        return dp[0]
    }
}