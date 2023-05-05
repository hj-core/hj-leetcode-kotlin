package com.hj.leetcode.kotlin.problem1456

/**
 * LeetCode page: [1456. Maximum Number of Vowels in a Substring of Given Length](https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun maxVowels(s: String, k: Int): Int {
        var maxVowels = 0

        slidingWindow(s, k) { windowVowels ->
            if (maxVowels < windowVowels) {
                maxVowels = windowVowels
            }
        }
        return maxVowels
    }

    private fun slidingWindow(
        s: String,
        windowSize: Int,
        onEachWindow: (numVowels: Int) -> Unit
    ) {
        val vowels = charArrayOf('a', 'e', 'i', 'o', 'u')
        var windowVowels = 0

        // Update the vowels count for the first window and call onEachWindow
        for (index in 0 until windowSize) {
            val char = s[index]
            if (char in vowels) {
                windowVowels++
            }
        }
        onEachWindow(windowVowels)

        /* Slide the window, update the vowels count and call onEachWindow for each window, until reach
         * the end of s.
         */
        for (windowEnd in windowSize until s.length) {
            val newChar = s[windowEnd]
            if (newChar in vowels) {
                windowVowels++
            }

            val popChar = s[windowEnd - windowSize]
            if (popChar in vowels) {
                windowVowels--
            }

            onEachWindow(windowVowels)
        }
    }
}