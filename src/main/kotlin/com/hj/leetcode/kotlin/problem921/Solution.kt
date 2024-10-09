package com.hj.leetcode.kotlin.problem921

/**
 * LeetCode page: [921. Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s.
     */
    fun minAddToMakeValid(s: String): Int {
        var lonelyLefts = 0
        var lonelyRights = 0
        for (c in s) {
            when {
                c == '(' -> lonelyLefts += 1
                c != ')' -> throw IllegalArgumentException("Unexpected character.")
                lonelyLefts > 0 -> lonelyLefts -= 1
                else -> lonelyRights += 1
            }
        }
        return lonelyLefts + lonelyRights
    }
}
