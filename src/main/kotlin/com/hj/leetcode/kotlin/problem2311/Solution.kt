package com.hj.leetcode.kotlin.problem2311

/**
 * LeetCode page: [2311. Longest Binary Subsequence Less Than or Equal to K](https://leetcode.com/problems/longest-binary-subsequence-less-than-or-equal-to-k/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun longestSubsequence(
        s: String,
        k: Int,
    ): Int {
        var remainingK = k
        var result = 0

        for (i in s.indices.reversed()) {
            if (s[i] == '0') {
                result++
            } else if (remainingK > 0) {
                remainingK -= 1 shl result
                result++
            }

            if (remainingK > 0 && (1 shl result) > remainingK) {
                remainingK = 0
            }
        }
        return result
    }
}
