package com.hj.leetcode.kotlin.problem3016

/**
 * LeetCode page: [3016. Minimum Number of Pushes to Type Word II](https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of word.
    fun minimumPushes(word: String): Int {
        val charFreq = IntArray(26)
        for (c in word) {
            charFreq[c - 'a']++
        }
        charFreq.sortDescending()

        return charFreq.withIndex().sumOf { (i, freq) -> freq * (i / 8 + 1) }
    }
}
