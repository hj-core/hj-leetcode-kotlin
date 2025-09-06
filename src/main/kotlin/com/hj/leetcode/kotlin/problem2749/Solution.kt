package com.hj.leetcode.kotlin.problem2749

/**
 * LeetCode page: [2749. Minimum Operations to Make the Integer Zero](https://leetcode.com/problems/minimum-operations-to-make-the-integer-zero/);
 */
class Solution {
    // Complexity:
    // Time O(64^2) and Space O(1).
    fun makeTheIntegerZero(
        num1: Int,
        num2: Int,
    ): Int {
        var result = 0
        var remain = num1.toLong() // num1 - num2*result
        while (remain >= num2 + result + 1) {
            result++
            remain -= num2
            if (remain.countOneBits() <= result) {
                return result
            }
        }
        return -1
    }
}
