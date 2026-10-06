package com.hj.leetcode.kotlin.problem921

/**
 * LeetCode page: [921. Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun minAddToMakeValid(s: String): Int {
        var add = 0
        var bal = 0
        for (c in s) {
            bal += 1 - (c.code and 1 shl 1)
            add += bal ushr 31
            bal += bal ushr 31
        }
        return add + bal
    }
}
