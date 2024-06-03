package com.hj.leetcode.kotlin.problem2486

/**
 * LeetCode page: [2486. Append Characters to String to Make Subsequence](https://leetcode.com/problems/append-characters-to-string-to-make-subsequence/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun appendCharacters(s: String, t: String): Int {
        var sIndex = 0
        var tIndex = 0
        while (tIndex < t.length && sIndex < s.length) {
            if (t[tIndex] == s[sIndex]) {
                tIndex++
            }
            sIndex++
        }
        return t.length - tIndex
    }
}