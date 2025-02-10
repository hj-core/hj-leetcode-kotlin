package com.hj.leetcode.kotlin.problem3174

/**
 * LeetCode page: [3174. Clear Digits](https://leetcode.com/problems/clear-digits/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of `s`.
    fun clearDigits(s: String): String =
        buildString {
            for (c in s) {
                when (c) {
                    in 'a'..'z' -> append(c)
                    in '0'..'9' -> if (this.isNotEmpty()) deleteAt(this.lastIndex)
                    else -> throw IllegalArgumentException("Unexpected character '$c'")
                }
            }
        }
}
