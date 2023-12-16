package com.hj.leetcode.kotlin.problem242

/**
 * LeetCode page: [242. Valid Anagram](https://leetcode.com/problems/valid-anagram/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the shorter length of s and t;
     */
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) {
            return false
        }

        val count = IntArray(26)
        for (char in s) {
            count[char - 'a']++
        }

        for (char in t) {
            count[char - 'a']--
        }
        return count.all { it == 0 }
    }
}