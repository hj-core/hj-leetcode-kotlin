package com.hj.leetcode.kotlin.problem520

/**
 * LeetCode page: [520. Detect Capital](https://leetcode.com/problems/detect-capital/);
 */
class Solution {
    /* Complexity:
     * Time O(|word|) and Space O(1);
     */
    fun detectCapitalUse(word: String): Boolean {
        return with(word) {
            isAllUppercase() || isAllLowercase() || isFirstLetterUppercaseOnly()
        }
    }

    private fun String.isAllUppercase() = all { it.isUpperCase() }

    private fun String.isAllLowercase() = all { it.isLowerCase() }

    private fun String.isFirstLetterUppercaseOnly(): Boolean {
        if (isEmpty()) return false
        if (first().isLowerCase()) return false
        for (index in 1 until length) {
            if (this[index].isUpperCase()) return false
        }
        return true
    }
}