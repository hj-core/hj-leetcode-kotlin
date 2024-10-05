package com.hj.leetcode.kotlin.problem567

/**
 * LeetCode page: [567. Permutation in String](https://leetcode.com/problems/permutation-in-string/);
 */
class Solution {
    /* Complexity:
     * Time O(KN) and Space O(K) where K is the size of char set (26 in this problem)
     * and N is the length of s2.
     */
    fun checkInclusion(
        s1: String,
        s2: String,
    ): Boolean {
        if (s2.length < s1.length) {
            return false
        }
        val charCount = IntArray(26)
        // Check the first window
        for (i in s1.indices) {
            charCount[s1[i] - 'a'] -= 1
            charCount[s2[i] - 'a'] += 1
        }
        if (charCount.all { it == 0 }) {
            return true
        }
        // Check the remaining windows
        for (windowEnd in s1.length..<s2.length) {
            charCount[s2[windowEnd] - 'a'] += 1
            charCount[s2[windowEnd - s1.length] - 'a'] -= 1

            if (charCount.all { it == 0 }) {
                return true
            }
        }
        return false
    }
}
