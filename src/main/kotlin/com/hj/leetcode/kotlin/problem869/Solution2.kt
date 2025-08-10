package com.hj.leetcode.kotlin.problem869

class Solution2 {
    // max n = 10^9 < 2^30
    private val power2Freqs = LongArray(30) { countDigits(1 shl it) }

    // Complexity:
    // Time O(1) and Space O(1) if we treat the number of
    // different digits (i.e., 0–9) and the number of powers
    // of two within 10^9 (i.e., 30) as constants.
    fun reorderedPowerOf2(n: Int): Boolean = countDigits(n) in power2Freqs

    // Returns the frequency of each digit in n in a way
    // that each digit get 4 bits for its frequency.
    private fun countDigits(n: Int): Long {
        require(n > 0) { "n is positive by problem constraints" }
        var result = 0L
        var x = n
        while (x > 0) {
            result += 1L shl ((x % 10) shl 2)
            x /= 10
        }
        return result
    }
}
