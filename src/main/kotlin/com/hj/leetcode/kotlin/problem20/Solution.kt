package com.hj.leetcode.kotlin.problem20

/**
 * LeetCode page: [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun isValid(s: String): Boolean {
        val brackets = hashMapOf<Char, Char>().apply {
            put(')', '(')
            put(']', '[')
            put('}', '{')
        }
        val openingBrackets = brackets.values
        val closingBrackets = brackets.keys
        val stack = ArrayDeque<Char>()
        for (char in s) {
            when (char) {
                in openingBrackets -> stack.addLast(char)
                in closingBrackets -> {
                    val itsOpeningPair = brackets[char]
                    val isInvalid = stack.isEmpty() || stack.last() != itsOpeningPair
                    if (isInvalid) return false
                    stack.removeLast()
                }
                else -> return false
            }
        }
        return stack.isEmpty()
    }
}