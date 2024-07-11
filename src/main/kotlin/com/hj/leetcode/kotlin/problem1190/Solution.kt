package com.hj.leetcode.kotlin.problem1190

/**
 * LeetCode page: [1190. Reverse Substrings Between Each Pair of Parentheses](https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the length of s;
     */
    fun reverseParentheses(s: String): String {
        val stack = mutableListOf<Char>()
        for (char in s) {
            if (char != ')') {
                stack.add(char)
            } else {
                // Reverse the chars up to the most recent '(' and pop the '('
                var left = stack.lastIndexOf('(')
                var right = stack.lastIndex
                while (left < right) {
                    stack[left] = stack[right].also { stack[right] = stack[left] }
                    left++
                    right--
                }
                stack.removeLast()
            }
        }
        return stack.joinToString(separator = "")
    }
}