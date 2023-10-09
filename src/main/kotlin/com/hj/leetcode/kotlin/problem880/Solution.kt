package com.hj.leetcode.kotlin.problem880

/**
 * LeetCode page: [880. Decoded String at Index](https://leetcode.com/problems/decoded-string-at-index/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun decodeAtIndex(s: String, k: Int): String {
        return charAtPosition(s, s.lastIndex, decodedLength(s), k.toLong()).toString()
    }

    private tailrec fun charAtPosition(
        encodedString: String,
        endIndex: Int,
        decodedLength: Long,
        targetPosition: Long // 1-indexed index in the decoded string
    ): Char {

        require(targetPosition <= decodedLength)

        if (encodedString[endIndex] in '2'..'9') {
            val prevIndex = endIndex - 1
            val prevDecodedLength = decodedLength / (encodedString[endIndex] - '0')
            val newTargetPosition = (targetPosition % prevDecodedLength).let {
                if (it == 0L) prevDecodedLength else it
            }
            return charAtPosition(encodedString, prevIndex, prevDecodedLength, newTargetPosition)
        }

        if (decodedLength == targetPosition) {
            return encodedString[endIndex]
        }

        val prevIndex = endIndex - 1
        val prevDecodedLength = decodedLength - 1
        return charAtPosition(encodedString, prevIndex, prevDecodedLength, targetPosition)
    }

    private fun decodedLength(encodedString: String): Long {
        return encodedString.fold(0L) { acc: Long, c: Char ->
            if (c in '2'..'9') acc * (c - '0') else acc + 1
        }
    }
}