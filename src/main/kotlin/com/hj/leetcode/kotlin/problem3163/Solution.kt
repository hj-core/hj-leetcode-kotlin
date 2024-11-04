package com.hj.leetcode.kotlin.problem3163

/**
 * LeetCode page: [3163. String Compression III](https://leetcode.com/problems/string-compression-iii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of word.
     */
    fun compressedString(word: String): String =
        buildString {
            var countPrev = 1
            for (i in 1..<word.length) {
                if (word[i] == word[i - 1] && countPrev < 9) {
                    countPrev += 1
                } else {
                    append(countPrev, word[i - 1])
                    countPrev = 1
                }
            }
            append(countPrev, word.last())
        }
}
