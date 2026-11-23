package com.hj.leetcode.kotlin.problem2124

/**
 * LeetCode page: [2124. Check if All A's Appears Before All B's](https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun checkString(s: String): Boolean {
        var seenB = false
        for (c in s) {
            seenB = seenB || c == 'b'
            if (seenB && c == 'a') {
                return false
            }
        }

        return true
    }
}
