package com.hj.leetcode.kotlin.problem678

/**
 * LeetCode page: [678. Valid Parenthesis String](https://leetcode.com/problems/valid-parenthesis-string/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of s.
    fun checkValidString(s: String): Boolean {
        // We can use stack for stars instead of deque because it is popped
        // only when opens is empty.
        val stars = mutableListOf<Int>()
        val opens = mutableListOf<Int>()
        for ((i, c) in s.withIndex()) {
            when (c) {
                '*' -> {
                    stars.add(i)
                }

                '(' -> {
                    opens.add(i)
                }

                ')' -> {
                    when {
                        opens.isNotEmpty() -> opens.removeLast()
                        stars.isNotEmpty() -> stars.removeLast()
                        else -> return false
                    }
                }
            }
        }

        if (opens.size > stars.size) {
            return false
        }
        while (stars.isNotEmpty() && opens.isNotEmpty()) {
            if (stars.removeLast() < opens.removeLast()) {
                return false
            }
        }
        return opens.isEmpty()
    }
}
