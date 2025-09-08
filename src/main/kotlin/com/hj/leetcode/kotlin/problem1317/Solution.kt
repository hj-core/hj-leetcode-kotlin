package com.hj.leetcode.kotlin.problem1317

/**
 * LeetCode page: [1317. Convert Integer to the Sum of Two No-Zero Integers](https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/);
 */
class Solution {
    // Complexity:
    // Time O(Log n) and Space O(1).
    fun getNoZeroIntegers(n: Int): IntArray {
        var a = 0
        var remain = n
        var unit = 1

        while (remain > 1) {
            val digit = remain % 10
            remain /= 10

            if (digit < 2) {
                a += unit shl 1
                remain--
            } else {
                a += unit
            }

            unit *= 10
        }
        return intArrayOf(a, n - a)
    }
}
