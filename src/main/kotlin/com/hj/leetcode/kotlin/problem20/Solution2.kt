package com.hj.leetcode.kotlin.problem20

class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun isValid(s: String): Boolean {
        val demandStack = ArrayDeque<Char>()
        for (char in s) {
            when (char) {
                '(' -> demandStack.addLast(')')
                '[' -> demandStack.addLast(']')
                '{' -> demandStack.addLast('}')
                else -> {
                    val isInvalid = demandStack.isEmpty() || char != demandStack.last()
                    if (isInvalid) return false
                    demandStack.removeLast()
                }
            }
        }
        return demandStack.isEmpty()
    }
}