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

        var currWindowValue = getFirstWindowValue(digits, k)
        if (currWindowValue.isBeauty(num)) countValid++

        val multiplierOfLeadingDigit = getMultiplierOfLeadingDigit(k)
        for (index in digits.lastIndex - k downTo 0) {
            currWindowValue = currWindowValue / 10 + digits[index] * multiplierOfLeadingDigit
            if (currWindowValue.isBeauty(num)) countValid++
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

    private fun getMultiplierOfLeadingDigit(size: Int): Int {
        var multiplier = 1
        repeat(size - 1) { multiplier *= 10 }
        return multiplier
    }
}