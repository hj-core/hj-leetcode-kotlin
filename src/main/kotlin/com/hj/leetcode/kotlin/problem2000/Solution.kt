package com.hj.leetcode.kotlin.problem2000

/**
 * LeetCode page: [2000. Reverse Prefix of Word](https://leetcode.com/problems/reverse-prefix-of-word/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of word;
     */
    fun reversePrefix(word: String, ch: Char): String {
        val indexOfCh = word.indexOf(ch)
        return (word.substring(0..indexOfCh).reversed()
                + word.substring(indexOfCh + 1))
    }
}