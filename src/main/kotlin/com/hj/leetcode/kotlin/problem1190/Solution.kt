package com.hj.leetcode.kotlin.problem1190

/**
 * LeetCode page: [1190. Reverse Substrings Between Each Pair of Parentheses](https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun reverseParentheses(s: String): String {
        val parenthesesIndices = parenthesesIndices(s)
        val leftToRight = parenthesesIndices.associate { it.first to it.second }
        val rightToLeft = parenthesesIndices.associate { it.second to it.first }
        val builder = StringBuilder()
        var i = 0
        var direction = 1

        while (builder.length < s.length - 2 * parenthesesIndices.size) {
            when (s[i]) {
                '(' -> {
                    i = checkNotNull(leftToRight[i])
                    direction *= -1
                }

                ')' -> {
                    i = checkNotNull(rightToLeft[i])
                    direction *= -1
                }

                else -> builder.append(s[i])
            }
            i += direction
        }
        return builder.toString()
    }

    private fun parenthesesIndices(s: String): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        val lefts = mutableListOf<Int>() // Indices of pending left parentheses
        for ((i, char) in s.withIndex()) {
            when (char) {
                '(' -> lefts.add(i)
                ')' -> result.add(Pair(lefts.removeLast(), i))
            }
        }
        return result
    }
}
