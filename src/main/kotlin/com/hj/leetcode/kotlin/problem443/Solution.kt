package com.hj.leetcode.kotlin.problem443

/**
 * LeetCode page: [443. String Compression](https://leetcode.com/problems/string-compression/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of chars;
     */
    fun compress(chars: CharArray): Int {
        var currGroupStart = 0
        var currResultSize = 0

        while (currGroupStart < chars.size) {
            val groupChar = chars[currGroupStart]
            val groupSize = findGroupSize(chars, currGroupStart)
            currResultSize = currResultSize
                .run { writeGroupCharAndReturnNewResultSize(chars, groupChar, this) }
                .run { writeGroupSizeAndReturnNewResultSize(chars, groupSize, this) }
            currGroupStart += groupSize
        }
        return currResultSize
    }

    private fun findGroupSize(chars: CharArray, groupStart: Int): Int {
        var nextGroupStart = groupStart + 1
        while (nextGroupStart < chars.size && chars[nextGroupStart] == chars[groupStart]) {
            nextGroupStart++
        }
        return nextGroupStart - groupStart
    }

    private fun writeGroupCharAndReturnNewResultSize(chars: CharArray, groupChar: Char, lastSize: Int): Int {
        chars[lastSize] = groupChar
        return lastSize + 1
    }

    private fun writeGroupSizeAndReturnNewResultSize(chars: CharArray, groupSize: Int, lastSize: Int): Int {
        if (groupSize == 1) return lastSize
        var nextIndex = lastSize
        for (char in groupSize.toString()) {
            chars[nextIndex] = char
            nextIndex++
        }
        return nextIndex
    }
}