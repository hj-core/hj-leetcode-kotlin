package com.hj.leetcode.kotlin.problem214

/**
 * LeetCode page: [214. Shortest Palindrome](https://leetcode.com/problems/shortest-palindrome/);
 */
class Solution {
 /* Complexity:
  * Time O(N) and Space O(N) where N is the length of s.
  */
    fun shortestPalindrome(s: String): String {
        if (s.isEmpty()) {
            return s
        }
        // Search s against s.reversed() using KMP algorithm
        val prefixFunction = computePrefixFunction(s)
        var j = -1
        for (i in s.indices.reversed()) {
            while (s[i] != s[j + 1] && j != -1) {
                j = prefixFunction[j]
            }
            if (s[i] == s[j + 1]) {
                j += 1
            }
        }
        // Reverse the inadequate portion and append to the front of s
        return buildString {
            for (i in ((j + 1)..<s.length).reversed()) {
                append(s[i])
            }
            append(s)
        }
    }

    private fun computePrefixFunction(pattern: String): IntArray {
        val result = IntArray(pattern.length)
        result[0] = -1
        var k = -1
        for (i in 1..<pattern.length) {
            while (pattern[i] != pattern[k + 1] && k != -1) {
                k = result[k]
            }
            if (pattern[i] == pattern[k + 1]) {
                k += 1
            }
            result[i] = k
        }
        return result
    }
}
