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
        val reversedDigits = getReversedDigits(x)
        return getReversedNumber(reversedDigits, x < 0)
    }

    private fun getReversedDigits(number: Int): List<Int> {
        val reversedDigits = mutableListOf<Int>()
        if (number == 0) reversedDigits.add(0) else updateReversedDigitsForNonZero(reversedDigits, number)
        return reversedDigits
    }

    private fun updateReversedDigitsForNonZero(reversedDigits: MutableList<Int>, number: Int) {
        require(number != 0)
        var num = Math.abs(number)
        while (num > 0) {
            reversedDigits.add(num % 10)
            num /= 10
        }
    }

    private fun getReversedNumber(reversedDigits: List<Int>, isNegative: Boolean): Int {
        if (isReversedOverflow(reversedDigits)) return 0

        val abs = reversedDigits.fold(0) { acc, i -> acc * 10 + i }
        return if (isNegative) -abs else abs
    }

    private fun isReversedOverflow(reversedDigits: List<Int>): Boolean {
        return when {
            reversedDigits.size < digitsOfIntMax.size -> false
            reversedDigits.size > digitsOfIntMax.size -> throw IllegalStateException()
            else -> checkOverflowForEqualNumberOfDigits(reversedDigits)
        }
    }

    private fun checkOverflowForEqualNumberOfDigits(reversedDigits: List<Int>): Boolean {
        for (index in reversedDigits.indices) {
            when {
                reversedDigits[index] < digitsOfIntMax[index] -> return false
                reversedDigits[index] > digitsOfIntMax[index] -> return true
            }
        }
        return false
    }
}