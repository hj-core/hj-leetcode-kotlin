package com.hj.leetcode.kotlin.problem1255

import kotlin.math.max

/**
 * LeetCode page: [1255. Maximum Score Words Formed by Letters](https://leetcode.com/problems/maximum-score-words-formed-by-letters/);
 */
class Solution {
    /* Complexity:
     * Time O(L + A * 2^N) and Space O(A * N) where L is the flattened length of words,
     * A is the number of possible letters, and N is the size of words;
     */
    fun maxScoreWords(words: Array<String>, letters: CharArray, score: IntArray): Int {
        return dfsMaxScore(
            words,
            wordScores(words, score),
            wordLetters(words),
            0,
            0,
            availableLetters(letters)
        )
    }

    private fun dfsMaxScore(
        words: Array<String>,
        wordScores: IntArray,
        wordLetters: List<IntArray>,
        index: Int,
        pathScore: Int,
        availableLetters: IntArray,
    ): Int {
        if (index == words.size) {
            return pathScore
        }

        var result = 0
        if (tryAllocate(availableLetters, wordLetters[index])) {
            result = dfsMaxScore(
                words,
                wordScores,
                wordLetters,
                index + 1,
                pathScore + wordScores[index],
                availableLetters,
            )
            deallocate(availableLetters, wordLetters[index])
        }

        result = max(
            result,
            dfsMaxScore(words, wordScores, wordLetters, index + 1, pathScore, availableLetters)
        )
        return result
    }

    private fun tryAllocate(availableLetters: IntArray, wordLetters: IntArray): Boolean {
        if (availableLetters.indices.any { availableLetters[it] < wordLetters[it] }) {
            return false
        }

        for (i in availableLetters.indices) {
            availableLetters[i] -= wordLetters[i]
        }
        return true
    }

    private fun deallocate(availableLetters: IntArray, wordLetters: IntArray) {
        for (i in availableLetters.indices) {
            availableLetters[i] += wordLetters[i]
        }
    }

    private fun wordScores(words: Array<String>, letterScores: IntArray): IntArray {
        return IntArray(words.size) { wordScore(words[it], letterScores) }
    }

    private fun wordScore(word: String, letterScores: IntArray): Int {
        return word.fold(0) { acc, c -> acc + letterScores[c - 'a'] }
    }

    private fun wordLetters(words: Array<String>): List<IntArray> {
        return words.map { countLetters(it) }
    }

    private fun countLetters(word: String): IntArray {
        val result = IntArray(26)
        for (c in word) {
            result[c - 'a']++
        }
        return result
    }

    private fun availableLetters(letters: CharArray): IntArray {
        val result = IntArray(26)
        for (c in letters) {
            result[c - 'a']++
        }
        return result
    }
}