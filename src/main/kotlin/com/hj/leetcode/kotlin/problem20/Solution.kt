package com.hj.leetcode.kotlin.problem20

import java.lang.IllegalArgumentException

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
        val openBrackets = parentheses.values
        val closedBrackets = parentheses.keys
        val stack = ArrayDeque<Char>()
        for (char in s) {
            when (char) {
                in openBrackets -> stack.addLast(char)
                in closedBrackets -> {
                    val itsOpenPair = parentheses[char]
                    val isInvalid = stack.isEmpty() || stack.last() != itsOpenPair
                    if (isInvalid) return false
                    stack.removeLast()
                }
                else -> throw IllegalArgumentException()
            }
        }
        return stack.isEmpty()
    }
}