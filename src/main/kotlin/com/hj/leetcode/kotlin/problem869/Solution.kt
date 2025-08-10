package com.hj.leetcode.kotlin.problem869

/**
 * LeetCode page: [869. Reordered Power of 2](https://leetcode.com/problems/reordered-power-of-2/);
 */
class Solution {
    // power2Freqs[L] := the digit frequencies for each
    // power of two with L digits.
    private val power2Freqs = Array(11) { mutableListOf<IntArray>() }

    init {
        // Starting from i=30, 2^i has 10 or more digits.
        // It is impossible to reorder a number n <= 10^9
        // to form a 2^i with 10 or more digits.
        for (shift in 0..<30) {
            val (length, freqs) = countDigits(1 shl shift)
            power2Freqs[length].add(freqs)
        }
    }

    // Complexity:
    // Time O(Log(n)+LogM) and Space O(1). This excludes
    // the precomputation overhead of Time O((LogM)^2) and
    // Space O(LogM) required to compute the power2Digits.
    //
    // Here, we treat the number of different digits
    // (i.e., 10) as constant, and M is the maximum allowed
    // value of n.
    fun reorderedPowerOf2(n: Int): Boolean {
        val (length, freqs) = countDigits(n)
        return power2Freqs[length].any { it.contentEquals(freqs) }
    }

    // Returns the total number of digits in n and the
    // frequency of each digit in n.
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
