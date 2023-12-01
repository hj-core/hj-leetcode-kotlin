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
        val iterator1 = word1.flattenedIterator()
        val iterator2 = word2.flattenedIterator()

        while (iterator1.hasNext() && iterator2.hasNext()) {
            if (iterator1.next() != iterator2.next()) {
                return false
            }
        }
        return iterator1.hasNext() == iterator2.hasNext()
    }

    private fun Array<String>.flattenedIterator(): Iterator<Char> = object : Iterator<Char> {
        private var arrayIterator = this@flattenedIterator.iterator()
        private var stringIterator: Iterator<Char>? = null

        override fun hasNext(): Boolean {
            return arrayIterator.hasNext() || (stringIterator?.hasNext() == true)
        }

        override fun next(): Char {
            if (stringIterator?.hasNext() != true) {
                stringIterator = arrayIterator.next().iterator()
            }
            return checkNotNull(stringIterator).next()
        }
    }
}