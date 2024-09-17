package com.hj.leetcode.kotlin.problem884

/**
 * LeetCode page: [884. Uncommon Words from Two Sentences](https://leetcode.com/problems/uncommon-words-from-two-sentences/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(N+M) where N is the length of s1
     * and M is the length of s2.
     */
    fun uncommonFromSentences(
        s1: String,
        s2: String,
    ): Array<String> {
        val wordCount = mutableMapOf<String, Int>()
        s1.split(" ").groupingBy { it }.eachCountTo(wordCount)
        s2.split(" ").groupingBy { it }.eachCountTo(wordCount)

        return wordCount
            .asSequence()
            .filter { (_, count) -> count == 1 }
            .map { (word, _) -> word }
            .toList()
            .toTypedArray()
    }
}
