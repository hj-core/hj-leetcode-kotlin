package com.hj.leetcode.kotlin.problem1662

/**
 * LeetCode page: [1662. Check If Two String Arrays are Equivalent](https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the min flat length of word1 and word2;
     */
    fun arrayStringsAreEqual(word1: Array<String>, word2: Array<String>): Boolean {
        val iterator1 = CharIterator(word1)
        val iterator2 = CharIterator(word2)

        while (iterator1.hasNext() && iterator2.hasNext()) {
            val char1 = iterator1.next()
            val char2 = iterator2.next()
            if (char1 != char2) return false
        }

        val hasDiffLength = iterator1.hasNext() || iterator2.hasNext()
        if (hasDiffLength) return false

        return true
    }

    private class CharIterator(private val strings: Array<String>) : Iterator<Char> {

        private var nextStrIndex = 0
        private var nextCharIndex = 0

        override fun hasNext(): Boolean {
            return when {
                nextStrIndex > strings.lastIndex -> false
                nextStrIndex < strings.lastIndex -> true
                else -> nextCharIndex <= strings[nextStrIndex].lastIndex
            }
        }

        override fun next(): Char {
            val next = strings[nextStrIndex][nextCharIndex]

            if (nextCharIndex < strings[nextStrIndex].lastIndex) {
                nextCharIndex++
            } else {
                nextStrIndex++
                nextCharIndex = 0
            }

            return next
        }
    }
}