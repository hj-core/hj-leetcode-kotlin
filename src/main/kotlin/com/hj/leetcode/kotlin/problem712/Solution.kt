package com.hj.leetcode.kotlin.problem712

/**
 * LeetCode page: [712. Minimum ASCII Delete Sum for Two Strings](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(min(M,N)) where M and N are the lengths of
    // s1 and s2, respectively.
    fun minimumDeleteSum(
        s1: String,
        s2: String,
    ): Int {
        if (s1.length < s2.length) {
            return minimumDeleteSum(s2, s1)
        }

        val m = s1.length
        val n = s2.length

        // dp[j]@i:= minimumDeleteSum(s1[i..], s2[j..])
        val dp = IntArray(n + 1)

        // Base cases where i equals m
        for (j in n - 1 downTo 0) {
            dp[j] = s2[j].code + dp[j + 1]
        }

        for (i in m - 1 downTo 0) {
            var oldNext: Int // dp[i+1][j+1]

            dp[n] = (s1[i].code + dp[n]).also { oldNext = dp[n] }
            for (j in n - 1 downTo 0) {
                dp[j] =
                    if (s1[i] == s2[j]) {
                        oldNext
                    } else {
                        minOf(
                            // s1[i].code + dp[i+1][j]
                            s1[i].code + dp[j],
                            // s2[j].code + dp[i][j+1]
                            s2[j].code + dp[j + 1],
                        )
                    }.also { oldNext = dp[j] }
            }
        }

        return dp[0]
    }
}
