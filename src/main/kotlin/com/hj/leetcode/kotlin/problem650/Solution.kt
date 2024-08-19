package com.hj.leetcode.kotlin.problem650

import kotlin.math.min

/**
 * LeetCode page: [650. 2 Keys Keyboard](https://leetcode.com/problems/2-keys-keyboard/);
 */
class Solution {
    /* Complexity:
     * Time O(nLog(n)) and Space O(n);
     */
    fun minSteps(n: Int): Int {
        /* The idea is that when we reach n, the clipboard may have 1 to (n/2) 'A's
         * copied.
         * Although we don't know which clipboard state will give us the min steps,
         * we can try all and find the min one.
         * For a specific clipboard state that have j 'A's copied and j divides n,
         * the min step will be minSteps(j)+(n/j).
         */

        // dp[i]::=minSteps(i)
        val dp = IntArray(n + 1) { it }
        dp[1] = 0
        for (copied in 2..(n / 2)) {
            for (number in (copied * 2)..n step copied) {
                dp[number] = min(dp[number], dp[copied] + (number / copied))
            }
        }
        return dp[n]
    }
}