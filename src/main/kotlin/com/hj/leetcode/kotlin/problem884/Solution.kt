package com.hj.leetcode.kotlin.problem884

/**
 * LeetCode page: [884. Uncommon Words from Two Sentences](https://leetcode.com/problems/uncommon-words-from-two-sentences/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(N+M) where N is the flattened length of s1
     * and M is the flattened length of s2.
     */
    fun uncommonFromSentences(
        s1: String,
        s2: String,
    ): Array<String> {
        val wordCount1 = s1.split(" ").groupingBy { it }.eachCount()
        val wordCount2 = s2.split(" ").groupingBy { it }.eachCountTo(mutableMapOf())

        val result = mutableListOf<String>()
        for ((word, count) in wordCount1) {
            when {
                word in wordCount2 -> wordCount2.remove(word)
                count == 1 -> result.add(word)
            }
        }
        for ((word, count) in wordCount2) {
            if (count == 1) {
                result.add(word)
            }
        }
        return result.toTypedArray()
    }
}
