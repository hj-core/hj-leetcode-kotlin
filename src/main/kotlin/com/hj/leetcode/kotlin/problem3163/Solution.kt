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
                val prev = word[i - 1]
                if (word[i] != prev) {
                    append(countPrev, prev)
                    countPrev = 1
                } else {
                    countPrev += 1
                    if (countPrev > 9) {
                        append(9, prev)
                        countPrev -= 9
                    }
                }
            }
            append(countPrev, word.last())
        }
}
