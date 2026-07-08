package com.hj.leetcode.kotlin.problem3754

/**
 * LeetCode page: [3754. Concatenate Non-Zero Digits and Multiply by Sum I](https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-i/);
 */
class Solution {
    // Complexity:
    // Time O(Log n) and Space O(Log n).
    fun sumAndMultiply(n: Int): Long {
        val str = n.toString().filter { it != '0' }
        if (str.isEmpty()) {
            return 0L
        }

        val x = str.toLong()
        val sum = str.sumOf { it - '0' }
        return x * sum
    }
}
