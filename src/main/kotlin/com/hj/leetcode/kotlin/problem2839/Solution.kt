package com.hj.leetcode.kotlin.problem2839

/**
 * LeetCode page: [2839. Check if Strings Can be Made Equal With Operations I](https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-i/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun canBeEqual(
        s1: String,
        s2: String,
    ): Boolean {
        // Check even indices
        if (s1[0] != s2[0] && s1[0] != s2[2]) {
            return false
        }
        if (s1[0].code + s1[2].code != s2[0].code + s2[2].code) {
            return false
        }

        // Check odd indices
        if (s1[1] != s2[1] && s1[1] != s2[3]) {
            return false
        }
        return s1[1].code + s1[3].code == s2[1].code + s2[3].code
    }
}
