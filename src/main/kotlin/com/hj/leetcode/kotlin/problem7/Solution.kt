package com.hj.leetcode.kotlin.problem7

/**
 * LeetCode page: [7. Reverse Integer](https://leetcode.com/problems/reverse-integer/);
 */
class Solution {
    private val digitsOfIntMax = intArrayOf(2, 1, 4, 7, 4, 8, 3, 6, 4, 7)

    /* Complexity:
     * Time O(LogN) and Space (LogN) where N equals x;
     */
    fun reverse(x: Int): Int {
        val reversedDigits = getDigitsInReversedOrder(x)
        return getNumberFormedByDigits(reversedDigits, x < 0)
    }

    private fun getDigitsInReversedOrder(number: Int): List<Int> {
        if (number == 0) return mutableListOf(0)

        val reversedDigits = mutableListOf<Int>()
        var num = Math.abs(number)
        while (num > 0) {
            reversedDigits.add(num % 10)
            num /= 10
        }
        return reversedDigits
    }

    private fun getNumberFormedByDigits(digits: List<Int>, isNegative: Boolean): Int {
        if (isNumberOverflow(digits)) return 0

        val abs = digits.fold(0) { acc, i -> acc * 10 + i }
        return if (isNegative) -abs else abs
    }

    private fun isNumberOverflow(digits: List<Int>): Boolean {
        if (digits.size < digitsOfIntMax.size) return false

        for (index in digits.indices) {
            when {
                digits[index] < digitsOfIntMax[index] -> return false
                digits[index] > digitsOfIntMax[index] -> return true
            }
        }
        return false
    }
}