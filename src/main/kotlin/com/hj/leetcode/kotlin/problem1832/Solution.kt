package com.hj.leetcode.kotlin.problem1832

/**
 * LeetCode page: [1832. Check if the Sentence Is Pangram](https://leetcode.com/problems/check-if-the-sentence-is-pangram/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of sentence;
     */
    fun checkIfPangram(sentence: String): Boolean {
        val hasLowercase = BooleanArray(26)

        for (char in sentence) {
            if (char.isLowerCase()) hasLowercase[char - 'a'] = true
        }
        return hasLowercase.all { isTrue -> isTrue }
    }
}