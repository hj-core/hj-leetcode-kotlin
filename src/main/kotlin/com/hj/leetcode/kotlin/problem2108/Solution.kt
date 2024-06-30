package com.hj.leetcode.kotlin.problem2108

/**
 * LeetCode page: [2108. Find First Palindromic String in the Array](https://leetcode.com/problems/find-first-palindromic-string-in-the-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the flattened length of words;
     */
    fun firstPalindrome(words: Array<String>): String {
        return words.firstOrNull { it.isPalindromic() } ?: ""
    }

    private fun String.isPalindromic(): Boolean {
        var left = 0
        var right = lastIndex
        while (left < right) {
            if (this[left] != this[right]) {
                return false
            }
            left++
            right--
        }
        return true
    }
}