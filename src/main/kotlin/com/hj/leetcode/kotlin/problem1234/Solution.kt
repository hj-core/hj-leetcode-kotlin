package com.hj.leetcode.kotlin.problem1234

/**
 * LeetCode page: [1234. Replace the Substring for Balanced String](https://leetcode.com/problems/replace-the-substring-for-balanced-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun balancedString(s: String): Int {
        val chars = charArrayOf('Q', 'W', 'E', 'R')

        val charFrequency = getFrequencyPerChar(s)
        fun readFrequency(uppercase: Char) = charFrequency[uppercase - 'A']
        fun increaseFrequency(uppercase: Char) = charFrequency[uppercase - 'A']++
        fun decreaseFrequency(uppercase: Char) = charFrequency[uppercase - 'A']--

        val balance = s.length shr 2
        fun canBeBalanced() = chars.all { char -> readFrequency(char) <= balance }
        if (canBeBalanced()) return 0

        var minLength = s.length
        var leftIndex = 0
        for (rightIndex in s.indices) {
            val char = s[rightIndex]
            decreaseFrequency(char)

            while (canBeBalanced()) {
                val currLength = rightIndex - leftIndex + 1
                minLength = minOf(minLength, currLength)
                increaseFrequency(s[leftIndex])
                leftIndex++
            }
        }
        return minLength
    }

    private fun getFrequencyPerChar(uppercaseOnly: String): IntArray {
        val frequency = IntArray(26)
        for (char in uppercaseOnly) {
            frequency[char - 'A']++
        }
        return frequency
    }
}