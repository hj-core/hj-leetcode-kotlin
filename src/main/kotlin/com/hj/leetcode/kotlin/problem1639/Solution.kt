package com.hj.leetcode.kotlin.problem1639

/**
 * LeetCode page: [1639. Number of Ways to Form a Target String Given a Dictionary](https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/);
 */
class Solution {
    /* Complexity:
     * Time O(MK+KN) and Space O(N)
     * where M is the number of words, K is the length of each word,
     * and N is the length of target.
     */
    fun numWays(
        words: Array<String>,
        target: String,
    ): Int {
        if (words[0].length < target.length) {
            return 0
        }
        val modulus = 1_000_000_007

        // dp[i][k]::=
        // the number of ways to form target [i..<target.len]
        // using characters starting from index k
        val dp = IntArray(target.length + 1) // dp@k=words[0].len
        dp[dp.lastIndex] = 1

        for (k in words[0].indices.reversed()) {
            val charFreq = charFreq(words, k)
            for ((i, c) in target.withIndex()) {
                val pickK = ((charFreq[c - 'a'] * dp[i + 1].toLong()) % modulus).toInt()
                val skipK = dp[i]
                dp[i] = (pickK + skipK) % modulus
            }
        }
        return dp[0]
    }

    private fun charFreq(
        words: Array<String>,
        k: Int,
    ): IntArray {
        val result = IntArray(26)
        for (word in words) {
            result[word[k] - 'a']++
        }
        return result
    }
}
