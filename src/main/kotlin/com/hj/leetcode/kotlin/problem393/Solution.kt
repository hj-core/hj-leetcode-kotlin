package com.hj.leetcode.kotlin.problem393

/**
 * LeetCode page: [393. UTF-8 Validation](https://leetcode.com/problems/utf-8-validation/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of data;
     */
    fun validUtf8(data: IntArray): Boolean {
        var leadingByteIndex = 0
        while (leadingByteIndex <= data.lastIndex) {
            val leadingType = Utf8ByteType(data[leadingByteIndex])
            if (leadingType !is Utf8LeadingByte) return false

            val byteLength = leadingType.utf8length
            val hasEnoughBodyLength = leadingByteIndex + byteLength <= data.size
            if (!hasEnoughBodyLength) return false

            val bodyIndicesRange = leadingByteIndex + 1 until leadingByteIndex + byteLength
            for (bodyByteIndex in bodyIndicesRange) {
                val bodyType = Utf8ByteType(data[bodyByteIndex])
                if (bodyType !is Utf8BodyByte) return false
            }

            leadingByteIndex += byteLength
        }
        return true
    }

    private interface Utf8ByteType

    private class Utf8LeadingByte(val utf8length: Int) : Utf8ByteType

    private object Utf8BodyByte : Utf8ByteType

    private object InvalidUtf8Byte : Utf8ByteType

    private fun Utf8ByteType(utf8Byte: Int): Utf8ByteType {
        return when (utf8Byte) {
            in 0..127 -> Utf8LeadingByte(1)
            in 128..191 -> Utf8BodyByte
            in 192..223 -> Utf8LeadingByte(2)
            in 224..239 -> Utf8LeadingByte(3)
            in 240..247 -> Utf8LeadingByte(4)
            else -> InvalidUtf8Byte
        }
    }
}