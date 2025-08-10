package com.hj.leetcode.kotlin.problem869

/**
 * LeetCode page: [869. Reordered Power of 2](https://leetcode.com/problems/reordered-power-of-2/);
 */
class Solution {
    // power2Digits[i] := the digit frequencies for each
    // power of two with i digits.
    private val power2Digits = Array(11) { mutableListOf<IntArray>() }

    init {
        // Starting from i=30, 2^i has 10 or more digits.
        // It is impossible to reorder a number n (where n <= 10^9)
        // to form a 2^i for i >= 30. This is because 10^9 is not
        // a power of two, and for other values of n, there are not
        // enough digits.
        for (shift in 0..<30) {
            val (length, freqs) = countDigits(1 shl shift)
            power2Digits[length].add(freqs)
        }
    }

    // Complexity:
    // Time O(Log n) and Space O(1) if we treat the number
    // of different digits (i.e., 10) and the number of
    // powers of two candidates (i.e., 30) as constants.
    fun reorderedPowerOf2(n: Int): Boolean {
        val (length, freqs) = countDigits(n)
        return power2Digits[length].any { it.contentEquals(freqs) }
    }

    // Returns the total number of digits in n and the count
    // of each digit in n.
    private fun countDigits(n: Int): Pair<Int, IntArray> {
        require(n > 0) { "n is positive by problem constraints" }
        var x = n
        var length = 0
        val freqs = IntArray(10)
        while (x > 0) {
            length++
            freqs[x % 10]++
            x /= 10
        }
        return length to freqs
    }
}
