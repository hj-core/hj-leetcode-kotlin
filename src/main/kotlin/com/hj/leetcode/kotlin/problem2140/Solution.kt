package com.hj.leetcode.kotlin.problem2140

/**
 * LeetCode page: [2140. Solving Questions With Brainpower](https://leetcode.com/problems/solving-questions-with-brainpower/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of question.
    fun mostPoints(questions: Array<IntArray>): Long {
        val n = questions.size
        val dp = LongArray(n + 1) // dp[i]::= mostPoints(questions[i..<n])

        for (i in questions.indices.reversed()) {
            val (points, brainpower) = questions[i]
            dp[i] = maxOf(dp[i + 1], points + dp[minOf(i + brainpower + 1, n)])
        }
        return dp[0]
    }
}
