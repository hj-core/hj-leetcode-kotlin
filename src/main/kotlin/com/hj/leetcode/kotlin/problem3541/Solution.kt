package com.hj.leetcode.kotlin.problem3541

/**
 * LeetCode page: [3541. Find Most Frequent Vowel and Consonant](https://leetcode.com/problems/find-most-frequent-vowel-and-consonant/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(M), where N is the length of s
    // and M is the number of possible lowercase (i.e., 26).
    fun maxFreqSum(s: String): Int {
        val freqs = IntArray(26)
        for (c in s) {
            freqs[c - 'a']++
        }

        val mostFreqVowel = maxOf(freqs[0], freqs[4], freqs[8], freqs[14], freqs[20])
        freqs[0] = 0
        freqs[4] = 0
        freqs[8] = 0
        freqs[14] = 0
        freqs[20] = 0
        return mostFreqVowel + freqs.max()
    }
}
