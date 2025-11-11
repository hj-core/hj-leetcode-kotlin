package com.hj.leetcode.kotlin.problem474

/**
 * LeetCode page: [474. Ones and Zeroes](https://leetcode.com/problems/ones-and-zeroes/);
 */
class Solution {
    // Complexity:
    // Time O(mnN+M) and Space O(mn) where N and M are the length
    // and the flattened length of strs, respectively.
    fun findMaxForm(
        strs: Array<String>,
        m: Int,
        n: Int,
    ): Int {
        // dp[i][j]@k:= the maximum subset size of strs[0..<k]
        // that has no more than i 0s and no more than j 1s.
        val dp = Array(m + 1) { IntArray(n + 1) }

        for (str in strs) {
            val zeros = str.count { it == '0' }
            val ones = str.length - zeros
            for (i in m downTo zeros) {
                for (j in n downTo ones) {
                    dp[i][j] =
                        maxOf(
                            dp[i][j],
                            dp[i - zeros][j - ones] + 1,
                        )
                }
            }
        }
        return dp[m][n]
    }
}
