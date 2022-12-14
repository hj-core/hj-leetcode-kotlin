package com.hj.leetcode.kotlin.problem151

/**
 * LeetCode page: [151. Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(|s|) and Space O(|s|);
     */
    fun reverseWords(s: String): String {
        val result = StringBuilder()
        val reversedWord = StringBuilder()

        for (index in s.indices.reversed()) {
            if (s[index] == ' ') {
                appendAndClearWord(result, reversedWord)
            } else {
                reversedWord.append(s[index])
            }
        }

        appendAndClearWord(result, reversedWord)

        return result
            .removeTrailingSpace()
            .toString()
    }

    private fun appendAndClearWord(accumulatedWords: StringBuilder, reversedWord: StringBuilder) {
        if (reversedWord.isEmpty()) return

        for (index in reversedWord.indices.reversed()) {
            accumulatedWords.append(reversedWord[index])
        }

        accumulatedWords.append(' ')
        reversedWord.clear()
    }

    private fun StringBuilder.removeTrailingSpace(): StringBuilder {
        while (lastOrNull() == ' ') {
            deleteCharAt(lastIndex)
        }
        return this
    }
}