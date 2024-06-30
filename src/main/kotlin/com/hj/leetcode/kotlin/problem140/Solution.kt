package com.hj.leetcode.kotlin.problem140

/**
 * LeetCode page: [140. Word Break II](https://leetcode.com/problems/word-break-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N * (2^N) + L) and Space O(N * (2^N) + L) where N is the length of s
     * and L is the flattened length of wordDict;
     */
    fun wordBreak(s: String, wordDict: List<String>): List<String> = buildList {
        dfsAllSentences(s, 0, mutableListOf(), wordDict.toSet()) {
            add(it)
        }
    }

    private fun dfsAllSentences(
        s: String,
        index: Int,
        breakPoints: MutableList<Int>,
        dict: Set<String>,
        onEachSentence: (sentence: String) -> Unit,
    ) {
        if (index == s.length) {
            if (s.substring(breakPoints.last()) in dict) {
                onEachSentence(sentence(s, breakPoints))
            }
            return
        }
        if (breakPoints.isNotEmpty() && s.substring(breakPoints.last(), index) in dict) {
            breakPoints.add(index)
            dfsAllSentences(s, index + 1, breakPoints, dict, onEachSentence)
            breakPoints.removeLast()
        }
        if (breakPoints.isEmpty()) {
            breakPoints.add(index)
        }
        dfsAllSentences(s, index + 1, breakPoints, dict, onEachSentence)
    }

    private fun sentence(s: String, breakPoints: List<Int>): String = buildString {
        for (i in 0..<breakPoints.lastIndex) {
            append(s.substring(breakPoints[i], breakPoints[i + 1]))
            append(" ")
        }
        append(s.substring(breakPoints.last()))
    }
}