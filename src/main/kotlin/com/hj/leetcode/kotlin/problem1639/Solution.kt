package com.hj.leetcode.kotlin.problem1639

/**
 * LeetCode page: [1639. Number of Ways to Form a Target String Given a Dictionary](https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/);
 */
class Solution {
    /* Complexity:
     * Time O(MN+KN) and Space O(N)
     * where M is the number of words, N is the length of each word,
     * and K is the length of target.
     */
    fun numWays(
        words: Array<String>,
        target: String,
    ): Int {
        if (words[0].length < target.length) {
            return 0
        }
        val modulus = 1_000_000_007

        // charFreq[k][c-'a']::= the number of words with character c at index k
        val charFreq = charFreq(words)

        // dp[i][k]::=
        // the number of ways to form target [i..<target.len]
        // using characters from index k
        val dp = IntArray(target.length + 1) // dp@k=words[0].len
        dp[dp.lastIndex] = 1

        for (k in words[0].indices.reversed()) {
            for ((i, c) in target.withIndex()) {
                val pickK = ((charFreq[k][c - 'a'] * dp[i + 1].toLong()) % modulus).toInt()
                val skipK = dp[i]
                dp[i] = (pickK + skipK) % modulus
            }
        }
        return dp[0]
    }

    private fun charFreq(words: Array<String>): Array<IntArray> {
        val result = Array(words[0].length) { IntArray(26) }
        for (word in words) {
            for ((k, c) in word.withIndex()) {
                result[k][c - 'a']++
            }
        }
        return result
    }
}
