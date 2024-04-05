package com.hj.leetcode.kotlin.problem1544

/**
 * LeetCode page: [1544. Make The String Great](https://leetcode.com/problems/make-the-string-great/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun makeGood(s: String): String {
        val stack = StringBuilder(s.length)
        for (char in s) {
            val isBad = (stack.isNotEmpty()
                    && char != stack.last()
                    && char.lowercaseChar() == stack.last().lowercaseChar())

            if (isBad) {
                stack.apply { deleteAt(lastIndex) }
            } else {
                stack.append(char)
            }
        }
        return stack.toString()
    }
}