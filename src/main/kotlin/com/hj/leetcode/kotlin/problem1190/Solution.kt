package com.hj.leetcode.kotlin.problem1190

/**
 * LeetCode page: [1190. Reverse Substrings Between Each Pair of Parentheses](https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of s.
    fun reverseParentheses(s: String): String {
        val (teleport, count) = buildTeleport(s)

        val result = CharArray(s.length - 2 * count)
        var next = 0
        var i = 0
        var step = 1
        while (i < s.length) {
            if (s[i] == '(' || s[i] == ')') {
                i = teleport[i]
                step = -step
            } else {
                result[next] = s[i]
                next++
            }

            i += step
        }

        return String(result)
    }

    private fun buildTeleport(s: String): Pair<IntArray, Int> {
        val teleport = IntArray(s.length) { -128 }
        var count = 0
        val opens = mutableListOf<Int>()
        for ((i, c) in s.withIndex()) {
            when (c) {
                '(' -> {
                    opens.add(i)
                    count++
                }

                ')' -> {
                    val open = opens.removeLast()
                    teleport[open] = i
                    teleport[i] = open
                }
            }
        }

        return Pair(teleport, count)
    }
}
