package com.hj.leetcode.kotlin.problem409

/**
 * LeetCode page: [409. Longest Palindrome](https://leetcode.com/problems/longest-palindrome/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(K) where N is the length of s
     * and K is the size of charset;
     */
    fun longestPalindrome(s: String): Int {
        val countChars = s
            .groupingBy { it }
            .eachCount()

        val numOldFreqChars = countChars
            .values
            .count { it and 1 != 0 }
        return if (numOldFreqChars > 0) s.length - numOldFreqChars + 1 else s.length
    }
}