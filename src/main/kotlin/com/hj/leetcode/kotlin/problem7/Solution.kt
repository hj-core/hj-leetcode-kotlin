package com.hj.leetcode.kotlin.problem7

/**
 * LeetCode page: [7. Reverse Integer](https://leetcode.com/problems/reverse-integer/);
 */
class Solution {
    private val digitsOfIntMax = intArrayOf(2, 1, 4, 7, 4, 8, 3, 6, 4, 7)
    private val reversedDigits = mutableListOf<Int>()

    /* Complexity:
     * Time O(LogN) and Space (LogN) where N equals x;
     */
    fun reverse(x: Int): Int {
        resetState()
        updateReversedDigits(x)
        return if (isOverflowAfterReversed()) 0 else getReversedNumber(x < 0)
    }

    private fun resetState() {
        reversedDigits.clear()
    }

    private fun updateReversedDigits(number: Int) {
        if (number == 0) reversedDigits.add(0) else updateReversedDigitsForNonZero(number)
    }

    private fun updateReversedDigitsForNonZero(number: Int) {
        require(number != 0)
        var num = Math.abs(number)
        while (num > 0) {
            reversedDigits.add(num % 10)
            num /= 10
        }
    }

    private fun isOverflowAfterReversed(): Boolean {
        return when {
            reversedDigits.size < digitsOfIntMax.size -> false
            reversedDigits.size > digitsOfIntMax.size -> throw IllegalStateException()
            else -> checkOverflowWhenSameNumberOfDigits()
        }
    }

    private fun checkOverflowWhenSameNumberOfDigits(): Boolean {
        for (index in reversedDigits.indices) {
            when {
                reversedDigits[index] < digitsOfIntMax[index] -> return false
                reversedDigits[index] > digitsOfIntMax[index] -> return true
            }
        }
        return false
    }

    private fun getReversedNumber(isNegative: Boolean): Int {
        val abs = reversedDigits.fold(0) { acc, i -> acc * 10 + i }
        return if (isNegative) -abs else abs
    }
}