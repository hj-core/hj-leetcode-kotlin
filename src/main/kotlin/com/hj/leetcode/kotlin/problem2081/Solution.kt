package com.hj.leetcode.kotlin.problem2081

/**
 * LeetCode page: [2081. Sum of k-Mirror Numbers](https://leetcode.com/problems/sum-of-k-mirror-numbers/);
 */
class Solution {
    // Complexity:
    // Time O(???) and Space O(???).
    fun kMirror(
        k: Int,
        n: Int,
    ): Long {
        val gen1 = PalindromeGenerator(k)
        val gen2 = PalindromeGenerator(10)

        var result = 0L
        var count = 0

        while (count < n) {
            var num1 = gen1.next()
            var num2 = gen2.next()

            while (num1 != num2) {
                if (num1 < num2) {
                    num1 = gen1.next()
                } else {
                    num2 = gen2.next()
                }
            }
            count++
            result += num1
        }
        return result
    }
}

private class PalindromeGenerator(
    val base: Int,
) {
    private val digits = IntArray(63) // Increasing significance order
    private var length = 1 // The length of active digits

    // Returns the next base-10 number that are palindrome in the specified base.
    fun next(): Long {
        var carry = 1
        for (i in (length / 2)..<length) {
            digits[i]++
            if (digits[i] == base) {
                digits[i] = 0
            } else {
                carry = 0
            }

            digits[length - 1 - i] = digits[i]
            if (carry == 0) {
                break
            }
        }

        if (carry == 1) {
            digits[0] = 1
            digits[length] = 1
            length++
        }
        return computeValue()
    }

    private fun computeValue(): Long {
        var result = 0L
        var unit = 1L
        for (i in 0..<length) {
            result += digits[i] * unit
            unit *= base
        }
        return result
    }
}
