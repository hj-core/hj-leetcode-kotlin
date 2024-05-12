package com.hj.leetcode.kotlin.problem1657

/**
 * LeetCode page: [1657. Determine if Two Strings Are Close](https://leetcode.com/problems/determine-if-two-strings-are-close/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of word1;
     */
    fun closeStrings(word1: String, word2: String): Boolean {
        if (word1.length != word2.length) {
            return false
        }
        val count1 = word1.groupingBy { it }.eachCount()
        val count2 = word2.groupingBy { it }.eachCount()
        return (count1.keys == count2.keys
                && count1.values.sorted() == count2.values.sorted())
    }
}