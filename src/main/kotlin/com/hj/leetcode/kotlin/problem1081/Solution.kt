package com.hj.leetcode.kotlin.problem1081

/**
 * LeetCode page: [1081. Smallest Subsequence of Distinct Characters](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/);
 */
class Solution {
    // Complexity:
    // Time O(ZN) and Space O(ZN) where N is the length of s and Z is the number of
    // lowercase chars.
    fun smallestSubsequence(s: String): String {
        // suffixSeen[i]:= mask of char existence of s[i..]
        val suffixSeen = IntArray(s.length + 1)
        for (i in s.indices.reversed()) {
            suffixSeen[i] = 1 shl (s[i] - 'a') or suffixSeen[i + 1]
        }

        val result = StringBuilder()
        var targets = suffixSeen[0] // Not yet included chars
        var start = -1 // Index of the last added char
        while (targets != 0) {
            var i = start + 1
            var minChar = 'z'
            while (i < s.length && suffixSeen[i] and targets == targets) {
                val inTargets = targets shr (s[i] - 'a') and 1 == 1
                if (inTargets && s[i] < minChar) {
                    start = i
                    minChar = s[i]
                }
                i++
            }

            targets = 1 shl (minChar - 'a') xor targets
            result.append(minChar)
        }

        return result.toString()
    }
}
