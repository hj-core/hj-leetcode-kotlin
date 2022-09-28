package com.hj.leetcode.kotlin.problem2180

/**
 * LeetCode page: [2180. Count Integers With Even Digit Sum](https://leetcode.com/problems/count-integers-with-even-digit-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N equals num;
     */
    fun countEven(num: Int): Int {
        /* The idea is by pairing (0, 1), (2, 3), .., (10, 11), (12, 13) and so on, each pair will contain
         * an even and an odd digit sum because the two numbers are different only at the last digit. Also
         * note the exclusion of zero in this problem.
         */
        val numPairsIncludingZero = (num + 1) shr 1

        val isNumOdd = num and 1 == 1
        if (isNumOdd) return numPairsIncludingZero - 1

        val digitSumOfNum = num.sumDigits()
        val isNumEvenDigitSum = digitSumOfNum and 1 == 0
        return if (isNumEvenDigitSum) numPairsIncludingZero else numPairsIncludingZero - 1
    }

    private fun Int.sumDigits(): Int {
        var sum = 0
        var num = this

        while (num > 0) {
            sum += num % 10
            num /= 10
        }
        return sum
    }
}