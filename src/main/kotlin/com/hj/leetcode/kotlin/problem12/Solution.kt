package com.hj.leetcode.kotlin.problem12

/**
 * LeetCode page: [12. Integer to Roman](https://leetcode.com/problems/integer-to-roman/);
 */
class Solution {

    private val romanSymbols = hashMapOf<Int, String>().apply {
        this[1] = "I"
        this[5] = "V"
        this[10] = "X"
        this[50] = "L"
        this[100] = "C"
        this[500] = "D"
        this[1000] = "M"
        this[5000] = "???"
        this[10000] = "???"
    }

    /* Complexity:
     * Time O(LogN) and Space O(LogN) where N equals num;
     */
    fun intToRoman(num: Int): String {
        require(num in 1..3999) // Constraint from problem;

        val sortedUnits = listOf(DigitUnit.Thousand, DigitUnit.Hundred, DigitUnit.Ten, DigitUnit.One)

        val roman = sortedUnits.joinToString("") { digitUnit ->
            val digit = num.digitAt(digitUnit)
            getRomanNumeral(digit, digitUnit)
        }

        return roman
    }

    private enum class DigitUnit(val value: Int) {
        One(1),
        Ten(10),
        Hundred(100),
        Thousand(1000)
    }

    private fun Int.digitAt(digitUnit: DigitUnit) = (this / digitUnit.value) % 10

    private fun getRomanNumeral(digit: Int, digitUnit: DigitUnit): String {
        require(digit in 0..9)

        val romanOneUnit = checkNotNull(romanSymbols[digitUnit.value])
        val romanFiveUnits = checkNotNull(romanSymbols[digitUnit.value * 5])
        val romanTenUnits = checkNotNull(romanSymbols[digitUnit.value * 10])

        return when {
            digit == 0 -> ""
            digit <= 3 -> romanOneUnit.repeat(digit)
            digit == 4 -> romanOneUnit + romanFiveUnits
            digit <= 8 -> romanFiveUnits + romanOneUnit.repeat(digit - 5)
            digit == 9 -> romanOneUnit + romanTenUnits
            else -> throw IllegalArgumentException()
        }
    }
}