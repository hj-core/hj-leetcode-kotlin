package com.hj.leetcode.kotlin.problem20

/**
 * LeetCode page: [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of s.
    fun isValid(s: String): Boolean {
        val opens = charArrayOf('(', '[', '{')
        val stack = mutableListOf<Char>()
        for (c in s) {
            when {
                c in opens -> stack.add(c)
                stack.isEmpty() -> return false
                c - stack.last() !in 1..2 -> return false
                else -> stack.removeLast()
            }
        }
        return stack.isEmpty()
    }
}
