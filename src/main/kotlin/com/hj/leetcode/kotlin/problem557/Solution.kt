package com.hj.leetcode.kotlin.problem557

/**
 * LeetCode page: [557. Reverse Words in a String III](https://leetcode.com/problems/reverse-words-in-a-string-iii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun reverseWords(s: String): String {
        val ans = StringBuilder(s.length)
        val currWord = StringBuilder()
        for (char in s) {
            if (char == ' ') {
                ans.append(currWord.reverse())
                ans.append(char)
                currWord.clear()
            } else {
                currWord.append(char)
            }
        }
        ans.append(currWord.reverse())
        return ans.toString()
    }
}