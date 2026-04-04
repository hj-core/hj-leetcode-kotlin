package com.hj.leetcode.kotlin.problem2075

/**
 * LeetCode page: [2075. Decode the Slanted Ciphertext](https://leetcode.com/problems/decode-the-slanted-ciphertext/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of encodedText.
    fun decodeCiphertext(
        encodedText: String,
        rows: Int,
    ): String {
        if (encodedText.isEmpty()) {
            return ""
        }
        val columns = encodedText.length / rows

        val sb = StringBuilder(encodedText.length)
        for (start in 0..<columns) {
            var r = 0
            var c = start
            while (r < rows && c < columns) {
                sb.append(encodedText[r * columns + c])
                r++
                c++
            }
        }

        return sb.trimEnd().toString()
    }
}
