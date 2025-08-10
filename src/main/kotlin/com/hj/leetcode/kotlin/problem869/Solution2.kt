package com.hj.leetcode.kotlin.problem869

class Solution2 {
    // Starting from i=30, 2^i has 10 or more digits.
    // It is impossible to reorder a number n <= 10^9
    // to form a 2^i with 10 or more digits.
    private val power2Freqs = LongArray(30) { countDigits(1 shl it) }

    // Complexity:
    // Time O(Log(n)+LogM) and Space O(1) where M is the
    // maximum allowed value of n. This excludes the
    // precomputation overhead of Time O((LogM)^2) and
    // Space O(LogM) required to compute the power2Freqs.
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
