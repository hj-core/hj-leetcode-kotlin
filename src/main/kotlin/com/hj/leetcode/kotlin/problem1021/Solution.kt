package com.hj.leetcode.kotlin.problem1021

/**
 * LeetCode page: [1021. Remove Outermost Parentheses](https://leetcode.com/problems/remove-outermost-parentheses/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of s.
    fun removeOuterParentheses(s: String): String {
        val result = StringBuilder()
        var depth = 0
        for (c in s) {
            val isRight = c.code and 1
            depth += 1 - (isRight shl 1)
            if (depth xor isRight != 1) {
                result.append(c)
            }
        }
        return result.toString()
    }
}
