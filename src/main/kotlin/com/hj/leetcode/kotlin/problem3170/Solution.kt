package com.hj.leetcode.kotlin.problem3170

/**
 * LeetCode page: [3170. Lexicographically Minimum String After Removing Stars](https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of s.
    fun clearStars(s: String): String {
        val isDeleted = BooleanArray(s.length)
        val charIndices = CharIndices()
        for ((i, c) in s.withIndex()) {
            if (c == '*') {
                isDeleted[i] = true
                isDeleted[charIndices.popLastMin()] = true
            } else {
                charIndices.add(c, i)
            }
        }

        val result = StringBuilder()
        for ((i, c) in s.withIndex()) {
            if (!isDeleted[i]) {
                result.append(c)
            }
        }
        return result.toString()
    }

    private class CharIndices {
        private val container = Array(26) { mutableListOf<Int>() }
        private var minChar = 'a'

        fun add(
            c: Char,
            index: Int,
        ) {
            container[c - 'a'].add(index)
            if (c < minChar) {
                minChar = c
            }
        }

        fun popLastMin(): Int {
            while (container[minChar - 'a'].isEmpty()) {
                minChar++
            }
            return container[minChar - 'a'].removeLast()
        }
    }
}
