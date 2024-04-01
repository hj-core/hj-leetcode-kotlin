package com.hj.leetcode.kotlin.problem58

/**
 * LeetCode page: [58. Length of Last Word](https://leetcode.com/problems/length-of-last-word/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun lengthOfLastWord(s: String): Int {
        val end = s.indexOfLast { it != ' ' }
        val start = (0..<end)
            .findLast { s[it] == ' ' }
            ?.let { it + 1 }
            ?: 0
        return end - start + 1
    }
}