package com.hj.leetcode.kotlin.problem392

/**
 * LeetCode page: [392. Is Subsequence](https://leetcode.com/problems/is-subsequence/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of t;
     */
    fun isSubsequence(s: String, t: String): Boolean {
        if (s.isEmpty()) {
            return true
        }
        if (s.length > t.length) {
            return false
        }

        var sIndex = 0

        for (tChar in t) {
            if (tChar == s[sIndex]) {
                sIndex++
            }
            if (sIndex == s.length) {
                return true
            }
        }
        return false
    }
}