package com.hj.leetcode.kotlin.problem2185

/**
 * LeetCode page: [2185. Counting Words With a Given Prefix](https://leetcode.com/problems/counting-words-with-a-given-prefix/);
 */
class Solution {
    /* Complexity:
     * Time O(NL) and Space O(1)
     * where N and L are the length of words and pref, respectively.
     */
    fun prefixCount(
        words: Array<String>,
        pref: String,
    ): Int = words.count { it.startsWith(pref) }
}
