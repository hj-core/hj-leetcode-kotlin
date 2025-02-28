package com.hj.leetcode.kotlin.problem1092

/**
 * LeetCode page: [1092. Shortest Common Supersequence](https://leetcode.com/problems/shortest-common-supersequence/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(MN) where M and N are the length of `str1` and `str2`, respectively.
    fun shortestCommonSupersequence(
        str1: String,
        str2: String,
    ): String = buildResult(str1, str2, buildDp(str1, str2))

    // dp[i][j]::= the length of longest common subsequence between str1[i:] and str2[j:]
    private fun buildDp(
        str1: String,
        str2: String,
    ): Array<IntArray> {
        val result = Array(str1.length + 1) { IntArray(str2.length + 1) }
        for (i in str1.indices.reversed()) {
            for (j in str2.indices.reversed()) {
                result[i][j] =
                    when {
                        str1[i] == str2[j] -> 1 + result[i + 1][j + 1]
                        result[i + 1][j] < result[i][j + 1] -> result[i][j + 1]
                        else -> result[i + 1][j]
                    }
            }
        }
        return result
    }

    // Build the shortest common supersequence based on the dp array
    private fun buildResult(
        str1: String,
        str2: String,
        dp: Array<IntArray>,
    ): String =
        buildString {
            var i = 0
            var j = 0
            while (i < str1.length && j < str2.length) {
                when {
                    str1[i] == str2[j] -> {
                        append(str1[i])
                        i++
                        j++
                    }

                    dp[i + 1][j] < dp[i][j + 1] -> {
                        append(str2[j])
                        j++
                    }

                    else -> {
                        append(str1[i])
                        i++
                    }
                }
            }
            while (i < str1.length) {
                append(str1[i])
                i++
            }
            while (j < str2.length) {
                append(str2[j])
                j++
            }
        }
}
