package com.hj.leetcode.kotlin.problem2390

/**
 * LeetCode page: [2390. Removing Stars From a String](https://leetcode.com/problems/removing-stars-from-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun removeStars(s: String): String {
        val result = StringBuilder()
        for (char in s) {
            if (char == '*') {
                result.deleteLast()
            } else {
                result.append(char)
            }
        }
        return result.toString()
    }

    private fun StringBuilder.deleteLast() {
        this.deleteCharAt(lastIndex)
    }
}