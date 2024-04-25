package com.hj.leetcode.kotlin.problem2370

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [2370. Longest Ideal Subsequence](https://leetcode.com/problems/longest-ideal-subsequence/);
 */
class Solution {
    /* Complexity:
     * Time O(kN) and Space O(1) where N is the length of s;
     */
    fun longestIdealString(s: String, k: Int): Int {
        // dp[j]@i::= the length of longest ideal string end with 'a'+j by s[0..i]
        val dp = IntArray(26) // base case i = -1

        for (char in s) {
            val iChar = char - 'a'
            val idealRange = max(0, iChar - k)..min(25, iChar + k)
            dp[iChar] = 1 + idealRange.maxOf { dp[it] }
        }
        return dp.max()
    }
}