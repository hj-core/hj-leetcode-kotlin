package com.hj.leetcode.kotlin.problem3090

/**
 * LeetCode page: [3090. Maximum Length Substring With Two Occurrences](https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun maximumLengthSubstring(s: String): Int {
        val charFreq = IntArray(26)
        var breached = 0
        var left = 0

        for (right in s.indices) {
            (s[right] - 'a').let { cIdx ->
                charFreq[cIdx]++
                if (charFreq[cIdx] == 3) {
                    breached++
                }
            }

            if (breached > 0) {
                (s[left] - 'a').let { cIdx ->
                    charFreq[cIdx]--
                    if (charFreq[cIdx] == 2) {
                        breached--
                    }
                }
                left++
            }
        }

        return s.length - left
    }
}
