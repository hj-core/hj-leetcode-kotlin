package com.hj.leetcode.kotlin.problem3612

/**
 * LeetCode page: [3612. Process String with Special Operations I](https://leetcode.com/problems/process-string-with-special-operations-i/);
 */
class Solution {
    // Complexity:
    // Time O(2^N) and Space O(2^N) where N is the length of s.
    fun processStr(s: String): String =
        buildString {
            for (c in s) {
                when (c) {
                    in 'a'..'z' -> append(c)
                    '*' -> if (isNotEmpty()) deleteAt(lastIndex)
                    '#' -> append(this)
                    '%' -> reverse()
                    else -> throw IllegalArgumentException("Invalid character '$c'")
                }
            }
        }
}
