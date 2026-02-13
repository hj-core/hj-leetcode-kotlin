package com.hj.leetcode.kotlin.problem3713

/**
 * LeetCode page: [3713. Longest Balanced Substring I](https://leetcode.com/problems/longest-balanced-substring-i/);
 */
class Solution {
    // Complexity:
    // Time O((C+N)*N) and Space O(C) where N is the length of s and C
    // is the size of charset (i.e. 26).
    fun longestBalanced(s: String): Int {
        var result = 0
        for (i in s.indices) {
            val freq = IntArray(26)
            var maxFreq = 0
            var maxCount = 0

            for (j in i..<s.length) {
                val cIdx = s[j] - 'a'
                freq[cIdx]++

                if (freq[cIdx] > maxFreq) {
                    maxFreq = freq[cIdx]
                    maxCount = 1
                } else if (freq[cIdx] == maxFreq) {
                    maxCount++
                }

                val len = j - i + 1
                if (len == maxFreq * maxCount && len > result) {
                    result = len
                }
            }
        }

        return result
    }
}
