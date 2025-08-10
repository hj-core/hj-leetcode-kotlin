package com.hj.leetcode.kotlin.problem869

/**
 * LeetCode page: [869. Reordered Power of 2](https://leetcode.com/problems/reordered-power-of-2/);
 */
class Solution {
    // power2Digits[i] := the digit frequencies for each
    // power of two with i digits.
    private val power2Digits = Array(11) { mutableListOf<IntArray>() }

    init {
        // max n = 10^9 < 2^30
        for (shift in 0..<30) {
            val (length, freqs) = countDigits(1 shl shift)
            power2Digits[length].add(freqs)
        }
    }

    // Complexity:
    // Time O(1) and Space O(1) if we treat the number of
    // different digits (i.e., 0–9) and the number of powers
    // of two within 10^9 (i.e., 30) as constants.
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
