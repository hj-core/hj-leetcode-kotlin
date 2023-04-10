package com.hj.leetcode.kotlin.problem20

/**
 * LeetCode page: [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun isValid(s: String): Boolean {
        val parentheses = hashMapOf<Char, Char>().apply {
            put(')', '(')
            put(']', '[')
            put('}', '{')
        }
        val openingBrackets = parentheses.values
        val closingBrackets = parentheses.keys
        val stack = ArrayDeque<Char>()
        for (char in s) {
            when (char) {
                in openingBrackets -> stack.addLast(char)
                in closingBrackets -> {
                    val itsOpeningPair = parentheses[char]
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