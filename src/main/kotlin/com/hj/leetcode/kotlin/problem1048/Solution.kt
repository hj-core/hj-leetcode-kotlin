package com.hj.leetcode.kotlin.problem1048

/**
 * LeetCode page: [1048. Longest String Chain](https://leetcode.com/problems/longest-string-chain/);
 */
class Solution {
    fun longestStrChain(words: Array<String>): Int {
        var result = 0
        val wordsByLength = wordsByLength(words)
        for (word in words) {
            val chainLength = longestStrChain(word, wordsByLength, hashMapOf())
            result = maxOf(result, chainLength)
        }
        return result
    }

    private fun longestStrChain(
        word: String,
        wordsByLength: Map<Int, List<String>>,
        memoization: MutableMap<String, Int>
    ): Int {

        if (word in memoization) {
            return checkNotNull(memoization[word])
        }

        var result = 1
        for (nextWord in (wordsByLength[word.length + 1] ?: emptyList())) {
            if (isPredecessor(word, nextWord)) {
                val chainLength =
                    1 + longestStrChain(nextWord, wordsByLength, memoization)
                result = maxOf(result, chainLength)
            }
        }
        memoization[word] = result
        return result
    }

    private fun wordsByLength(words: Array<String>): Map<Int, List<String>> {
        return words.groupBy { it.length }
    }

    private fun isPredecessor(a: String, b: String): Boolean {
        if (a.length + 1 != b.length) {
            return false
        }
        if (a.isEmpty()) {
            return true
        }

        var aIndex = 0
        for (bChar in b) {
            if (a[aIndex] == bChar) {
                aIndex++
            }
            if (aIndex == a.length) {
                return true
            }
        }
        return false
    }
}