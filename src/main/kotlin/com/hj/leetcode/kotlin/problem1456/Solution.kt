package com.hj.leetcode.kotlin.problem1456

/**
 * LeetCode page: [1456. Maximum Number of Vowels in a Substring of Given Length](https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun maxVowels(s: String, k: Int): Int {
        val vowels = charArrayOf('a', 'e', 'i', 'o', 'u')

        // (By sliding window)
        // Store the max number of vowels with an initial value 0.
        var maxVowels = 0

        // Count the number of vowels in the first window and update the max number of vowels
        var windowVowels = 0
        for (index in 0 until k) {
            val char = s[index]
            if (char in vowels) {
                windowVowels++
            }
        }
        maxVowels = windowVowels

        /* Slide the window, update the vowel count and the max number of vowels, until reach
         * the end of s.
         */
        for (windowEnd in k until s.length) {
            val newChar = s[windowEnd]
            if (newChar in vowels) {
                windowVowels++
            }

            val popChar = s[windowEnd - k]
            if (popChar in vowels) {
                windowVowels--
            }

            if (maxVowels < windowVowels) {
                maxVowels = windowVowels
            }
        }

        // return the max number of vowels
        return maxVowels
    }
}