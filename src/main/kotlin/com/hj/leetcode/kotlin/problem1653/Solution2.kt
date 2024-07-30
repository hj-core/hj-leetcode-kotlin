package com.hj.leetcode.kotlin.problem1653

import kotlin.math.min

/**
 * LeetCode page: [1653. Minimum Deletions to Make String Balanced](https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun minimumDeletions(s: String): Int {
        /* Sub problem:
         *  dp[i]::= minimumDeletions(s[0..i])
         * Relation:
         *  dp[i+1] = dp[i] if s[i] == 'b' else min(dp[i]+1, # of 'b's before i)
         */

        var result = 0 // dp[-1]
        var bBefore = 0
        for (c in s) {
            if (c == 'b') {
                bBefore++
            } else {
                result = min(result + 1, bBefore)
            }
        }
        return result
    }
}