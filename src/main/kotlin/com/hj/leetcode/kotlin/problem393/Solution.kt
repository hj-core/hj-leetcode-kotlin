package com.hj.leetcode.kotlin.problem393

/**
 * LeetCode page: [393. UTF-8 Validation](https://leetcode.com/problems/utf-8-validation/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of data;
     */
    fun validUtf8(data: IntArray): Boolean {
        var headByteIndex = 0
        while (headByteIndex <= data.lastIndex) {
            val headType = Utf8ByteType(data[headByteIndex])
            if (headType !is Utf8HeadByte) return false

            val bodyLength = headType.bytesOfBody
            val hasEnoughBody = headByteIndex + bodyLength < data.size
            if (!hasEnoughBody) return false

            val bodyByteIndices = headByteIndex + 1..headByteIndex + bodyLength
            for (bodyByteIndex in bodyByteIndices) {
                val bodyType = Utf8ByteType(data[bodyByteIndex])
                if (bodyType !is Utf8BodyByte) return false
            }

            headByteIndex += bodyLength + 1
        }
        return true
    }

    private interface Utf8ByteType

    private enum class Utf8HeadByte(val bytesOfBody: Int) : Utf8ByteType {
        ZeroBodyByte(0),
        OneBodyByte(1),
        TwoBodyBytes(2),
        ThreeBodyBytes(3);
    }

    private object Utf8BodyByte : Utf8ByteType

    private object InvalidUtf8Byte : Utf8ByteType

    private fun Utf8ByteType(utf8Byte: Int): Utf8ByteType {
        return when (utf8Byte) {
            in 0..127 -> Utf8HeadByte.ZeroBodyByte
            in 128..191 -> Utf8BodyByte
            in 192..223 -> Utf8HeadByte.OneBodyByte
            in 224..239 -> Utf8HeadByte.TwoBodyBytes
            in 240..247 -> Utf8HeadByte.ThreeBodyBytes
            else -> InvalidUtf8Byte
        }
    }
}