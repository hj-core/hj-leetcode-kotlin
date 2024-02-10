package com.hj.leetcode.kotlin.problem647

/**
 * LeetCode page: [647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N^2) and Space O(1) where N is the length of s;
     */
    fun countSubstrings(s: String): Int {
        var result = 0
        for (center in s.indices) {
            // check odd length palindromic substring with the center by expansion
            var left = center
            var right = center
            while (0 <= left && right < s.length && s[left] == s[right]) {
                result++
                left--
                right++
            }
            // check even length palindromic substring with the center by expansion
            left = center
            right = center + 1
            while (0 <= left && right < s.length && s[left] == s[right]) {
                result++
                left--
                right++
            }
        }
        return result
    }
}