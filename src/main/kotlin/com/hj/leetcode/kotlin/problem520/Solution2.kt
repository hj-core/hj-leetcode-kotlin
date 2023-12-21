package com.hj.leetcode.kotlin.problem520

/**
 * LeetCode page: [520. Detect Capital](https://leetcode.com/problems/detect-capital/);
 */
class Solution2 {
    /* Complexity:
     * Time O(|word|) and Space O(1);
     */
    fun detectCapitalUse(word: String): Boolean {
        val numCapitals = word.count { it.isUpperCase() }
        return numCapitals == 0 || numCapitals == word.length || (numCapitals == 1 && word[0].isUpperCase())
    }
}