package com.hj.leetcode.kotlin.problem2942

/**
 * LeetCode page: [2942. Find Words Containing Character](https://leetcode.com/problems/find-words-containing-character/);
 */
class Solution {
    // Complexity:
    // Time O(L) and Space O(N) where N and L are the length and the flattened
    // length of words, respectively.
    fun findWordsContaining(
        words: Array<String>,
        x: Char,
    ): List<Int> = words.indices.filter { words[it].contains(x) }
}
