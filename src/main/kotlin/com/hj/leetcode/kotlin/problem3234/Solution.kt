package com.hj.leetcode.kotlin.problem3234

import kotlin.math.sqrt

/**
 * LeetCode page: [3234. Count the Number of Substrings With Dominant Ones](https://leetcode.com/problems/count-the-number-of-substrings-with-dominant-ones/);
 */
class Solution {
    // Complexity:
    // Time O(N*sqrt(N)) and Space O(N) where N is the length of s.
    fun numberOfSubstrings(s: String): Int {
        val zeroIndices = IntArray(s.length + 1)
        var iZero = 0
        for ((i, c) in s.withIndex()) {
            if (c == '0') {
                zeroIndices[iZero] = i
                iZero++
            }
        }
        zeroIndices[iZero] = s.length

        val totalZeros = iZero
        val maxZeros =
            minOf(
                totalZeros,
                sqrt((s.length - totalZeros).toDouble()).toInt(),
            )

        var result = 0
        iZero = 0
        for (start in s.indices) {
            if (zeroIndices[iZero] < start) {
                iZero++
            }

            // Substring without zero
            result += zeroIndices[iZero] - start

            // Substring with zeros
            for (zeros in 1..(
                minOf(
                    maxZeros,
                    totalZeros - iZero,
                )
            )) {
                val ones =
                    zeroIndices[iZero + zeros] - start - zeros
                val minOnes = zeros * zeros
                val extraOnes = ones - minOnes
                if (extraOnes >= 0) {
                    val trailingOnes =
                        zeroIndices[iZero + zeros] -
                            zeroIndices[iZero + zeros - 1] -
                            1
                    result += 1 + minOf(extraOnes, trailingOnes)
                }
            }
        }
        return result
    }
}
