package com.hj.leetcode.kotlin.problem1190

/**
 * LeetCode page: [1190. Reverse Substrings Between Each Pair of Parentheses](https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the length of s;
     */
    fun reverseParentheses(s: String): String {
        val result = mutableListOf<Char>()
        val leftParentheses = mutableListOf<Int>()
        for (char in s) {
            when (char) {
                '(' -> leftParentheses.add(result.size)
                ')' -> {
                    // Reverse the chars up to the most recent left parentheses
                    var left = leftParentheses.removeLast()
                    var right = result.lastIndex
                    while (left < right) {
                        result[left] = result[right].also { result[right] = result[left] }
                        left++
                        right--
                    }
                }

                else -> result.add(char)
            }
        }
        return result.joinToString(separator = "")
    }
}