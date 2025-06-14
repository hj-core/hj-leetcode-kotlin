package com.hj.leetcode.kotlin.problem2566

/**
 * LeetCode page: [2566. Maximum Difference by Remapping a Digit](https://leetcode.com/problems/maximum-difference-by-remapping-a-digit/);
 */
class Solution {
    // Complexity:
    // Time O(Log(n)) and Space O(Log(n)).
    fun minMaxDifference(num: Int): Int {
        val digits = getDigits(num)
        val mapToZero = digits.last()
        val mapToNine = digits.lastOrNull { it < 9 } ?: 9

        var digitUnit = 1
        var result = 0
        for (digit in digits) {
            if (digit == mapToZero) {
                result += digit * digitUnit
            }
            if (digit == mapToNine) {
                result += (9 - digit) * digitUnit
            }
            digitUnit *= 10
        }
        return result
    }

    private fun getDigits(num: Int): List<Int> {
        if (num == 0) {
            return listOf(0)
        }

        val result = mutableListOf<Int>()
        var n = num
        while (n > 0) {
            result.add(n % 10)
            n /= 10
        }
        return result
    }
}
