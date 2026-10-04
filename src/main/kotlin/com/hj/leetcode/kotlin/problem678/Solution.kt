package com.hj.leetcode.kotlin.problem678

/**
 * LeetCode page: [678. Valid Parenthesis String](https://leetcode.com/problems/valid-parenthesis-string/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of s.
    fun checkValidString(s: String): Boolean {
        val opens = mutableListOf<Int>()
        val stars = ArrayDeque<Int>()
        for ((i, c) in s.withIndex()) {
            when (c) {
                '(' -> {
                    opens.add(i)
                }

                '*' -> {
                    stars.add(i)
                }

                ')' -> {
                    if (opens.isNotEmpty()) {
                        opens.removeLast()
                    } else if (stars.isNotEmpty()) {
                        stars.removeFirst()
                    } else {
                        return false
                    }
                }
            }
        }

        if (opens.isEmpty()) {
            return true
        }
        if (opens.size > stars.size) {
            return false
        }
        var i = 0
        var j = 0
        while (i < opens.size && j < stars.size) {
            if (opens[i] < stars[j]) {
                i++
            }
            j++
        }
        return i == opens.size
    }
}
