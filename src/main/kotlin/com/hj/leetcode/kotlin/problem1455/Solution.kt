package com.hj.leetcode.kotlin.problem1455

/**
 * LeetCode page: [1455. Check If a Word Occurs As a Prefix of Any Word in a Sentence](https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of sentence.
     */
    fun isPrefixOfWord(
        sentence: String,
        searchWord: String,
    ): Int {
        var wordCount = 0
        var wordStart = 0

        for (wordEnd in 0..sentence.length) {
            if (wordEnd < sentence.length && sentence[wordEnd] != ' ') {
                continue
            }

            wordCount++
            if (searchWord.length <= wordEnd - wordStart &&
                sentence.regionMatches(wordStart, searchWord, 0, searchWord.length)
            ) {
                return wordCount
            }
            wordStart = wordEnd + 1
        }
        return -1
    }
}
