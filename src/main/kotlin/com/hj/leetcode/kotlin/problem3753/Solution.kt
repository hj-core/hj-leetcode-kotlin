package com.hj.leetcode.kotlin.problem3753

/**
 * LeetCode page: [3753. Total Waviness of Numbers in Range II](https://leetcode.com/problems/total-waviness-of-numbers-in-range-ii/);
 */
class Solution {
    // Complexity:
    // Time O(D^2 * Log(num2)) and Space O(D * Log(num2)) where D is 10 (i.e., 0 to 9).
    fun totalWaviness(
        num1: Long,
        num2: Long,
    ): Long = computeTotalWaviness(num2) - computeTotalWaviness(num1 - 1)

    private fun computeTotalWaviness(num: Long): Long {
        if (num < 101) {
            return 0L
        }
        // Example: digits of 123 is [3, 2, 1]
        val digitsOfNum = num.toString().map { it.digitToInt() }.reversed()
        val length = digitsOfNum.size
        // dp[i][d0]:= the total waviness of length i+1 numbers starting with d0
        val dp = Array(length) { LongArray(10) }

        // digits = [..., d2, d1, d0]
        var totalWaviness = 0L
        var d2Weight = 1L
        for (i in 2..<length) {
            for (d0 in 0..<10) {
                for (d1 in 0..<10) {
                    dp[i][d0] += dp[i - 1][d1] +
                        when {
                            d0 < d1 -> d2Weight * d1
                            d0 > d1 -> d2Weight * (9 - d1)
                            else -> 0
                        }
                }
                totalWaviness += dp[i][d0]
            }
            totalWaviness -= dp[i][0]
            d2Weight *= 10
        }

        // Deduct the waviness of numbers greater than num
        for (d0 in digitsOfNum.last() + 1..<10) {
            totalWaviness -= dp.last()[d0]
        }

        var suffixWavinessOfNum = 0
        var d0 = digitsOfNum.last()
        for (i in length downTo 2) {
            val d1 = digitsOfNum[i - 1]
            for (d2 in digitsOfNum[i - 2] + 1..<10) {
                totalWaviness -= suffixWavinessOfNum * d2Weight
                if (isWavy(d0, d1, d2)) {
                    totalWaviness -= d2Weight
                }
                totalWaviness -= dp[i - 2][d2] +
                    when {
                        d1 < d2 -> d2Weight / 10 * d2
                        d1 > d2 -> d2Weight / 10 * (9 - d2)
                        else -> 0
                    }
            }

            val d2 = digitsOfNum[i - 2]
            if (isWavy(d0, d1, d2)) {
                suffixWavinessOfNum++
            }

            d0 = d1
            d2Weight /= 10
        }

        return totalWaviness
    }

    private fun isWavy(
        d0: Int,
        d1: Int,
        d2: Int,
    ): Boolean = (d0 < d1 && d1 > d2) || (d0 > d1 && d1 < d2)
}
