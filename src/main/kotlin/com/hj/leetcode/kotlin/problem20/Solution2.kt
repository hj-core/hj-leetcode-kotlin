package com.hj.leetcode.kotlin.problem20

class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun isValid(s: String): Boolean {
        val pendingBrackets = ArrayDeque<Char>()
        for (char in s) {
            when (char) {
                '(' -> pendingBrackets.addLast(')')
                '[' -> pendingBrackets.addLast(']')
                '{' -> pendingBrackets.addLast('}')
                else -> {
                    val isInvalid = pendingBrackets.isEmpty() || char != pendingBrackets.last()
                    if (isInvalid) return false
                    pendingBrackets.removeLast()
                }
            }
        }
        return pendingBrackets.isEmpty()
    }
}