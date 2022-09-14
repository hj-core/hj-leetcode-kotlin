package com.hj.leetcode.kotlin.problem1317

/**
 * LeetCode page: [1317. Convert Integer to the Sum of Two No-Zero Integers](https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1) since n is in 2..10000 thus at most 5 digits;
     */
    fun getNoZeroIntegers(n: Int): IntArray {
        val firstNoZero = constructFirstNoZero(n)
        val secondNoZero = n - firstNoZero
        return intArrayOf(firstNoZero, secondNoZero)
    }

    private fun constructFirstNoZero(n: Int): Int {
        val digits = n.getDigits()
        for (index in digits.indices.reversed()) {
            if (digits[index] < 2 && index != 0) {
                digits[index] = 5
                digits[index - 1] -= 1
            } else {
                digits[index] = digits[index] shr 1
            }
        }
        return digits.fold(0) { acc, i -> acc * 10 + i }
    }

    private fun Int.getDigits(): IntArray = this
        .toString()
        .map { it - '0' }
        .toIntArray()
}