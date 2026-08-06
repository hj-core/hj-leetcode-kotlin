package com.hj.leetcode.kotlin.problem3345

/**
 * LeetCode page: [3345. Smallest Divisible Digit Product I](https://leetcode.com/problems/smallest-divisible-digit-product-i/);
 */
class Solution {
    // Complexity:
    // Time O(Log n) and Space O(Log n).
    fun smallestNumber(
        n: Int,
        t: Int,
    ): Int {
        val digits = n.toString().map { it.digitToInt() }
        val base = (0..<digits.lastIndex).fold(1) { acc, i -> acc * digits[i] }
        for (d in digits.last()..<10) {
            if (base * d % t == 0) {
                return n - digits.last() + d
            }
        }
        return n - digits.last() + 10
    }
}
