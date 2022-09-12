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
        updateReversedDigits(x)
        return getReversedNumber(x < 0)
    }

    private fun updateReversedDigits(number: Int) {
        if (number == 0) reversedDigits.add(0) else updateReversedDigitsOfNonZero(number)
    }

    private fun updateReversedDigitsOfNonZero(number: Int) {
        require(number != 0)
        var num = Math.abs(number)
        while (num > 0) {
            reversedDigits.add(num % 10)
            num /= 10
        }
    }

    private fun getReversedNumber(isNegative: Boolean): Int {
        val abs = if (isReversedOverflow()) 0 else reversedDigits.fold(0) { acc, i -> acc * 10 + i }
        resetState()
        return if (isNegative) -abs else abs
    }

    private fun isReversedOverflow(): Boolean {
        return when {
            reversedDigits.size < digitsOfIntMax.size -> false
            reversedDigits.size > digitsOfIntMax.size -> throw IllegalStateException()
            else -> checkOverflowOfSameNumberOfDigits()
        }
    }

    private fun checkOverflowOfSameNumberOfDigits(): Boolean {
        for (index in reversedDigits.indices) {
            when {
                reversedDigits[index] < digitsOfIntMax[index] -> return false
                reversedDigits[index] > digitsOfIntMax[index] -> return true
            }
        }
        return false
    }

    private fun resetState() {
        reversedDigits.clear()
    }
}