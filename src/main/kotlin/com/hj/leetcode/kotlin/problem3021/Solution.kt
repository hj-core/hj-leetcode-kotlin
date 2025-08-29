package com.hj.leetcode.kotlin.problem3021

/**
 * LeetCode page: [3021. Alice and Bob Playing Flower Game](https://leetcode.com/problems/alice-and-bob-playing-flower-game/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun flowerGame(
        n: Int,
        m: Int,
    ): Long {
        val oddNs = (n + 1) / 2L
        val oddMs = (m + 1) / 2L
        return oddNs * (m - oddMs) + (n - oddNs) * oddMs
    }
}
