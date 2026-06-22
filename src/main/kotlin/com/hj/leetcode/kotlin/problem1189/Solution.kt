package com.hj.leetcode.kotlin.problem1189

/**
 * LeetCode page: [1189. Maximum Number of Balloons](https://leetcode.com/problems/maximum-number-of-balloons/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of text.
    fun maxNumberOfBalloons(text: String): Int {
        val charFreq = IntArray(26)
        for (c in text) {
            charFreq[c - 'a']++
        }

        charFreq['l' - 'a'] /= 2
        charFreq['o' - 'a'] /= 2
        return "balon".minOf { c -> charFreq[c - 'a'] }
    }
}
