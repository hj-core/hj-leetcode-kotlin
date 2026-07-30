package com.hj.leetcode.kotlin.problem3014

/**
 * LeetCode page: [3014. Minimum Number of Pushes to Type Word I](https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-i/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun minimumPushes(word: String): Int =
        when (val len = word.length) {
            in 1..<9 -> len
            in 9..<17 -> len * 2 - 8
            in 17..<25 -> len * 3 - 24
            in 25..<27 -> len * 4 - 48
            else -> throw IllegalArgumentException("Invalid word")
        }
}
