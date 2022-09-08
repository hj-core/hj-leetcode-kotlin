package com.hj.leetcode.kotlin.problem2231

/**
 * LeetCode page: [2231. Largest Number After Digit Swaps by Parity](https://leetcode.com/problems/largest-number-after-digit-swaps-by-parity/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the number of digits that num has;
     */
    fun largestInteger(num: Int): Int {
        val digits = num.toString().map { it.toString().toInt() }
        val (oddDigitsSorted, evenDigitsSorted) = getSortedOddAndEvenDigits(digits)
        var availableLeastOddDigitIndex = 0
        var availableLeastEvenDigitIndex = 0
        var digitValueMultiplier = 1
        var largestInteger = 0
        for (index in digits.indices.reversed()) {
            val digit = digits[index]
            if (digit and 1 == 0) {
                largestInteger += evenDigitsSorted[availableLeastEvenDigitIndex] * digitValueMultiplier
                availableLeastEvenDigitIndex++
            } else {
                largestInteger += oddDigitsSorted[availableLeastOddDigitIndex] * digitValueMultiplier
                availableLeastOddDigitIndex++
            }
            digitValueMultiplier *= 10
        }
        return largestInteger
    }

    // Return Pair(oddDigitsSortedDescending, evenDigitsSortedDescending) of given digits;
    private fun getSortedOddAndEvenDigits(digits: List<Int>): Pair<List<Int>, List<Int>> {
        val oddDigits = mutableListOf<Int>()
        val evenDigits = mutableListOf<Int>()
        for (digit in digits) {
            if (digit and 1 == 1) oddDigits.add(digit) else evenDigits.add(digit)
        }
        oddDigits.sort()
        evenDigits.sort()
        return Pair(oddDigits, evenDigits)
    }
}