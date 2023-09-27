package com.hj.leetcode.kotlin.problem880

/**
 * LeetCode page: [880. Decoded String at Index](https://leetcode.com/problems/decoded-string-at-index/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun decodeAtIndex(s: String, k: Int): String {
        return charAtLength(s, s.lastIndex, decodedLength(s), k.toLong()).toString()
    }

    private fun charAtLength(
        s: String,
        index: Int,
        decodedLength: Long,
        targetLength: Long
    ): Char {
        if (s[index] in '2'..'9') {
            val newDecodedLength = decodedLength / (s[index] - '0')
            val newTargetLength = (targetLength % newDecodedLength).let {
                if (it == 0L) newDecodedLength else it
            }
            return charAtLength(s, index - 1, newDecodedLength, newTargetLength)
        }

        if (targetLength == decodedLength) {
            return s[index]
        }

        return charAtLength(s, index - 1, decodedLength - 1, targetLength)
    }

    private fun decodedLength(s: String): Long {
        return s.fold(0L) { acc: Long, c: Char ->
            if (c in '2'..'9') acc * (c - '0') else acc + 1
        }
    }
}