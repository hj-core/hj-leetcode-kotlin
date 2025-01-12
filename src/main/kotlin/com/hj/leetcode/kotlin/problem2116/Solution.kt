package com.hj.leetcode.kotlin.problem2116

/**
 * LeetCode page: [2116. Check if a Parentheses String Can Be Valid](https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/);
 */
class Solution {
    /* Complexity:
     * Time O(N^3) and Space O(N^2) where N is the length of s and locked.
     *
     * WARNING: TLE.
     */
    fun canBeValid(
        s: String,
        locked: String,
    ): Boolean {
        // dp[i][len]::= canBeValid(s[i..<i+len], locked[i..<i+len]
        val dp = Array(s.length) { BooleanArray(s.length - it + 1) }

        for (i in dp.indices.reversed()) {
            for (len in dp[i].indices) {
                dp[i][len] =
                    when {
                        // Base cases
                        len == 0 -> true
                        len % 2 == 1 -> false
                        locked[i] == '1' && s[i] == ')' -> false
                        locked[i + len - 1] == '1' && s[i + len - 1] == '(' -> false
                        // Case (A)
                        dp[i + 1][len - 2] -> true
                        // Case AB
                        else -> (2..len - 2 step 2).any { dp[i][it] && dp[i + it][len - it] }
                    }
            }
        }
        return dp[0][s.length]
    }
}
