package com.hj.leetcode.kotlin.problem1910

/**
 * LeetCode page: [1910. Remove All Occurrences of a Substring](https://leetcode.com/problems/remove-all-occurrences-of-a-substring/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(M+N)
     * where M and N are the length of `s` and `part`, respectively.
     */
    fun removeOccurrences(
        s: String,
        part: String,
    ): String {
        if (s.length < part.length) {
            return s
        }
        // table[i]::= the longest suffix length of part[1..=i] that is a prefix of part
        val table = kmpTable(part)

        val builder = CharArray(s.length)
        val rollingMatched = IntArray(s.length)
        var resultLen = 0

        for (i in s.indices) {
            var k = if (resultLen == 0) 0 else rollingMatched[resultLen - 1]
            while (0 < k && s[i] != part[k]) {
                k = table[k - 1]
            }
            if (s[i] == part[k]) {
                k++
            }
            if (k == part.length) {
                resultLen -= part.length - 1
            } else {
                builder[resultLen] = s[i]
                rollingMatched[resultLen] = k
                resultLen++
            }
        }
        return buildString {
            for (i in 0..<resultLen) {
                append(builder[i])
            }
        }
    }

    private fun kmpTable(pattern: String): IntArray {
        val result = IntArray(pattern.length)
        // For each i,
        // k is the longest suffix length of pattern[1..=i] that is a prefix of pattern
        var k = 0
        for (i in 1..<pattern.length) {
            while (0 < k && pattern[i] != pattern[k]) {
                k = result[k - 1]
            }
            if (pattern[i] == pattern[k]) {
                k++
            }
            result[i] = k
        }
        return result
    }
}
