package com.hj.leetcode.kotlin.problem3517

/**
 * LeetCode page: [3517. Smallest Palindromic Rearrangement I](https://leetcode.com/problems/smallest-palindromic-rearrangement-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of s.
    fun smallestPalindrome(s: String): String {
        val charCount = IntArray(26)
        for (c in s) {
            charCount[c - 'a']++
        }

        val builder = CharArray(s.length)
        var left = 0
        var right = s.length - 1
        for ((i, freq) in charCount.withIndex()) {
            if (freq == 0) {
                continue
            }
            val c = 'a' + i
            if (freq and 1 == 1) {
                builder[s.length / 2] = c
            }
            repeat(freq / 2) {
                builder[left++] = c
            }
            repeat(freq / 2) {
                builder[right--] = c
            }
        }

        return builder.concatToString()
    }
}
