package com.hj.leetcode.kotlin.problem1897

/**
 * LeetCode page: [1897. Redistribute Characters to Make All Strings Equal](https://leetcode.com/problems/redistribute-characters-to-make-all-strings-equal/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the flattened length of words;
     */
    fun makeEqual(words: Array<String>): Boolean {
        val charFrequency = IntArray(26)
        for (word in words) {
            for (char in word) {
                charFrequency[char - 'a']++
            }
        }
        return charFrequency.all { it % words.size == 0 }
    }
}