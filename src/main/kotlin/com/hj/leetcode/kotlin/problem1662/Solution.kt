package com.hj.leetcode.kotlin.problem1662

/**
 * LeetCode page: [1662. Check If Two String Arrays are Equivalent](https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(1) where N and M are the flattened length of
     * word1 and word2, respectively;
     */
    fun arrayStringsAreEqual(word1: Array<String>, word2: Array<String>): Boolean {
        val iterator1 = CharIterator(word1)
        val iterator2 = CharIterator(word2)

        while (iterator1.hasNext() && iterator2.hasNext()) {
            if (iterator1.next() != iterator2.next()) {
                return false
            }
        }
        return iterator1.hasNext() == iterator2.hasNext()
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