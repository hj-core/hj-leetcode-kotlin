package com.hj.leetcode.kotlin.problem2243

/**
 * LeetCode page: [2243. Calculate Digit Sum of a String](https://leetcode.com/problems/calculate-digit-sum-of-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the length of s;
     */
    fun digitSum(s: String, k: Int): String {
        var currString = s
        while (currString.length > k) {
            currString = currString.toNextRound(k)
        }
        return currString
    }

    private fun String.toNextRound(chunkSize: Int): String {
        return this
            .asSequence()
            .chunked(chunkSize)
            .joinToString(separator = "") { chars -> chars.sumDigits().toString() }
    }

    private fun List<Char>.sumDigits(): Int {
        return this.fold(0) { acc, char ->
            require(char.isDigit())
            acc + (char - '0')
        }
    }
}