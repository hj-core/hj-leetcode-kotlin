package com.hj.leetcode.kotlin.problem678

/**
 * LeetCode page: [678. Valid Parenthesis String](https://leetcode.com/problems/valid-parenthesis-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun checkValidString(s: String): Boolean {
        val remainingOpen = mutableListOf<Int>() // The indices of remaining left parentheses
        val remainingStar = ArrayDeque<Int>() // The indices of remaining asterisk

        for ((i, char) in s.withIndex()) {
            when (char) {
                '(' -> remainingOpen.add(i)
                '*' -> remainingStar.addLast(i)
                ')' -> when {
                    remainingOpen.isNotEmpty() -> remainingOpen.removeLast()
                    remainingStar.isNotEmpty() -> remainingStar.removeFirst()
                    else -> return false
                }
            }
        }

        while (remainingOpen.isNotEmpty() && remainingStar.isNotEmpty()) {
            if (remainingOpen.last() > remainingStar.last()) {
                return false
            }
            remainingOpen.removeLast()
            remainingStar.removeLast()
        }
        return remainingOpen.isEmpty()
    }
}