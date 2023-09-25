package com.hj.leetcode.kotlin.problem389

/**
 * LeetCode page: [389. Find the Difference](https://leetcode.com/problems/find-the-difference/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(1) where M is the length of s and
     * N is the length of t;
     */
    fun findTheDifference(s: String, t: String): Char {
        val charFrequencies = IntArray(26)

        for (char in t) {
            charFrequencies[char - 'a']++
        }
        for (char in s) {
            charFrequencies[char -'a']--
        }
        return 'a' + charFrequencies.indexOf(1)
    }
}