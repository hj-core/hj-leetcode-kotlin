package com.hj.leetcode.kotlin.problem3302

/**
 * LeetCode page: [3302. Find the Lexicographically Smallest Valid Sequence](https://leetcode.com/problems/find-the-lexicographically-smallest-valid-sequence/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(N+M) where N is the length of word1 and
    // M is the length of word2.
    fun validSequence(
        word1: String,
        word2: String,
    ): IntArray {
        val word1 = word1.toCharArray()
        val word2 = word2.toCharArray()

        val sequence = IntArray(word2.size)
        var i1 = word1.size
        for (i2 in word2.indices.reversed()) {
            i1--
            while (0 <= i1 && word1[i1] != word2[i2]) {
                i1--
            }
            sequence[i2] = i1
        }

        var skipped = false
        i1 = 0
        var i2 = 0
        while (i1 < word1.size && i2 < word2.size) {
            if (word1[i1] == word2[i2]) {
                sequence[i2] = i1
                i2++
            } else if (!skipped && (i2 == word2.lastIndex || i1 < sequence[i2 + 1])) {
                skipped = true
                sequence[i2] = i1
                i2++
            }
            i1++
        }

        return if (i2 < word2.size) intArrayOf() else sequence
    }
}
