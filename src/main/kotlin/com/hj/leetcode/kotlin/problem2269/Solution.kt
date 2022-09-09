package com.hj.leetcode.kotlin.problem2269

/**
 * LeetCode page: [2269. Find the K-Beauty of a Number](https://leetcode.com/problems/find-the-k-beauty-of-a-number/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(LogN) where N equals num;
     */
    fun divisorSubstrings(num: Int, k: Int): Int {
        val digits = num.getDigits()
        var countValid = 0

        var windowValue = getFirstWindowValue(digits, k)
        if (windowValue.isBeauty(num)) countValid++

        val multiplierToLeadingDigit = getMultiplierToLeadingDigit(k)
        for (index in digits.lastIndex - k downTo 0) {
            windowValue = updateWindowValue(windowValue, digits, index, multiplierToLeadingDigit)
            if (windowValue.isBeauty(num)) countValid++
        }

        return countValid
    }

    private fun Int.getDigits(): List<Int> {
        return this
            .toString()
            .map { it - '0' }
    }

    private fun Int.isBeauty(num: Int) = this != 0 && num % this == 0

    private fun getFirstWindowValue(digits: List<Int>, size: Int): Int {
        var windowValue = 0
        for (index in digits.size - size..digits.lastIndex) {
            windowValue = windowValue * 10 + digits[index]
        }
        return windowValue
    }

    private fun getMultiplierToLeadingDigit(size: Int): Int {
        var multiplier = 1
        repeat(size - 1) { multiplier *= 10 }
        return multiplier
    }

    private fun updateWindowValue(
        previousValue: Int,
        digits: List<Int>,
        index: Int,
        multiplierToLeadingDigit: Int
    ): Int {
        return previousValue / 10 + digits[index] * multiplierToLeadingDigit
    }
}