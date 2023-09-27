package com.hj.leetcode.kotlin.problem880

/**
 * LeetCode page: [880. Decoded String at Index](https://leetcode.com/problems/decoded-string-at-index/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun decodeAtIndex(s: String, k: Int): String {
        return charAtLength(s, k.toLong(), s.lastIndex, decodedLength(s)).toString()
    }

    private fun charAtLength(
        s: String,
        targetLength: Long,
        endIndex: Int,
        decodedLength: Long
    ): Char {

        require(targetLength <= decodedLength)

        if (s[endIndex] in '2'..'9') {
            val prevIndex = endIndex - 1
            val prevDecodedLength = decodedLength / (s[endIndex] - '0')
            val newTargetLength = (targetLength % prevDecodedLength).let {
                if (it == 0L) prevDecodedLength else it
            }
            return charAtLength(s, newTargetLength, prevIndex, prevDecodedLength)
        }

        if (decodedLength == targetLength) {
            return s[endIndex]
        }
        return charAtLength(s, targetLength, endIndex - 1, decodedLength - 1)
    }

    private fun decodedLength(s: String): Long {
        return s.fold(0L) { acc: Long, c: Char ->
            if (c in '2'..'9') acc * (c - '0') else acc + 1
        }
    }
}