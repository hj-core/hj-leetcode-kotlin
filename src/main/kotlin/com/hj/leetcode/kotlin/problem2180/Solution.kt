package com.hj.leetcode.kotlin.problem2180

/**
 * LeetCode page: [2180. Count Integers With Even Digit Sum](https://leetcode.com/problems/count-integers-with-even-digit-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(LogN) where N equals num;
     */
    fun countEven(num: Int): Int {
        val isNumEvenDigitSum = num.isEvenDigitSum()
        val isNumEven = num and 1 == 0
        return ((num + 1) shr 1) - 1 + (if (isNumEven && isNumEvenDigitSum) 1 else 0)
    }

    private fun Int.isEvenDigitSum(): Boolean = digitSum() and 1 == 0

    private fun Int.digitSum(): Int {
        return this
            .toString()
            .fold(0) { acc, char -> acc + (char - '0') }
    }
}