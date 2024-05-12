package com.hj.leetcode.kotlin.problem1347

/**
 * LeetCode page: [1347. Minimum Number of Steps to Make Two Strings Anagram](https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun minSteps(s: String, t: String): Int {
        val netCount = IntArray(26)
        for (i in s.indices) {
            netCount[s[i] - 'a']++
            netCount[t[i] - 'a']--
        }
        return netCount.sumOf { it.coerceAtLeast(0) }
    }
}