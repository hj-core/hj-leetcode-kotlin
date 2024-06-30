package com.hj.leetcode.kotlin.problem91

/**
 * LeetCode page: [91. Decode Ways](https://leetcode.com/problems/decode-ways/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun numDecodings(s: String): Int {
        // dp@i=lastIndex ::= [numDecoding(s[i:]), numDecoding(s[i+1:])]
        val dp = mutableListOf((if (s.last() == '0') 0 else 1), 1)

        for (index in s.lastIndex - 1 downTo 0) {
            if (s[index] == '0') {
                dp[1] = dp[0]
                dp[0] = 0
                continue
            }

            var result = dp[0]
            if (s.slice(index..index + 1).toInt() <= 26) {
                result += dp[1]
            }
            dp[1] = dp[0]
            dp[0] = result
        }
        return dp[0]
    }
}