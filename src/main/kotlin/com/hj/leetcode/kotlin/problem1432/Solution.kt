package com.hj.leetcode.kotlin.problem1432

/**
 * LeetCode page: [1432. Max Difference You Can Get From Changing an Integer](https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/);
 */
class Solution {
    // Complexity:
    // Time O(Log(num)) and Space O(Log(num)).
    fun maxDiff(num: Int): Int {
        val digits = getDigits(num)

        // Mapping to produce the maximum number after changing digits
        val mapToNine = digits.lastOrNull { it < 9 } ?: 9

        // Mapping to produce the minimum number after changing digits
        val mapToOne = digits.last().let { if (it != 1) it else 1 }
        val mapToZero = if (mapToOne != 1) 0 else (digits.lastOrNull { it > 1 } ?: 0)

        // Compute the result by accumulating differences
        var digitUnit = 1
        var result = 0
        for (digit in digits) {
            if (digit == mapToNine) {
                result += (9 - digit) * digitUnit
            }

            if (digit == mapToOne) {
                result += (digit - 1) * digitUnit
            } else if (digit == mapToZero) {
                result += digit * digitUnit
            }
            digitUnit *= 10
        }
        return result
    }

    // Returns the digits of num from the least significant digit to the
    // most significant digit.
    private fun getDigits(num: Int): List<Int> {
        require(num > 0) { "num must be positive according to the problem statement" }
        val result = mutableListOf<Int>()
        var x = num
        while (x > 0) {
            result.add(x % 10)
            x /= 10
        }
        return result
    }
}
