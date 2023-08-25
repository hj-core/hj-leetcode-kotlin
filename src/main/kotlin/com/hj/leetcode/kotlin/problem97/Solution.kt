package com.hj.leetcode.kotlin.problem97

/**
 * LeetCode page: [97. Interleaving String](https://leetcode.com/problems/interleaving-string/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the lengths of s1 and s2;
     */
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        if (s1.length + s2.length != s3.length) {
            return false
        }
        if (s1.isEmpty() || s2.isEmpty()) {
            return s1 == s3 || s2 == s3
        }

        /* dp[i][j]::= isInterleave(s1[i:], s2[j:], s3[k(i, j):])
         * where k(i, j)::= s3.length - (s1.length - i) - (s2.length - j)
         */
        val dp = Array(s1.length + 1) { BooleanArray(s2.length + 1) }

        // Base Cases
        dp[s1.length][s2.length] = true
        for (i in s1.indices.reversed()) {
            val k = s3.length - (s1.length - i)
            dp[i][s2.length] = s1[i] == s3[k] && dp[i + 1][s2.length]
        }
        for (j in s2.indices.reversed()) {
            val k = s3.length - (s2.length - j)
            dp[s1.length][j] = s2[j] == s3[k] && dp[s1.length][j + 1]
        }

        // Propagate Relation and return original problem
        for (i in s1.indices.reversed()) {
            for (j in s2.indices.reversed()) {
                val k = s3.length - (s1.length - i) - (s2.length - j)
                dp[i][j] = when {
                    s1[i] != s3[k] && s2[j] != s3[k] -> false
                    s1[i] == s3[k] && s2[j] == s3[k] -> dp[i + 1][j] || dp[i][j + 1]
                    s1[i] == s3[k] -> dp[i + 1][j]
                    else -> dp[i][j + 1]
                }
            }
        }
        return dp[0][0]
    }
}