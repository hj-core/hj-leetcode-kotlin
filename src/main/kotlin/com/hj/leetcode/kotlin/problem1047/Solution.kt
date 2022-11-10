package com.hj.leetcode.kotlin.problem1047

/**
 * LeetCode page: [1047. Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/);
 */
class Solution {
    /* Complexity:
     * Time O(L) and Space O(L) where L is the length of s;
     */
    fun removeDuplicates(s: String): String {
        val builder = StringBuilder()

        for (char in s) {
            val peek = builder.lastOrNull()
            val isDuplicated = peek?.let { it == char } ?: false
            if (isDuplicated) builder.deleteLast() else builder.append(char)
        }

        return builder.toString()
    }

    private fun StringBuilder.deleteLast() {
        if (isEmpty()) return
        deleteCharAt(lastIndex)
    }
}