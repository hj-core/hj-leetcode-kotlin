package com.hj.leetcode.kotlin.problem3753

/**
 * LeetCode page: [3753. Total Waviness of Numbers in Range II](https://leetcode.com/problems/total-waviness-of-numbers-in-range-ii/);
 */
class Solution2 {
    // Complexity:
    // Time O(D * Log(num2)) and Space O(D) where D is 10 (i.e., 0 to 9).
    fun totalWaviness(
        num1: Long,
        num2: Long,
    ): Long = computeTotalWaviness(num2) - computeTotalWaviness(num1 - 1)

    private fun computeTotalWaviness(toNum: Long): Long {
        if (toNum < 101) {
            return 0L
        }
        // Example: digits of 123 is [3, 2, 1]
        val digitsOfNum = toNum.toString().map { it.digitToInt() }.reversed()
        val length = digitsOfNum.size
        var suffixWavinessOfNum = countWaviness(digitsOfNum)
        // dp[i][d0]:= the total waviness of length i+1 numbers starting with d0;
        // space-optimized to only keep the most recent two sub results.
        val dp = Array(2) { LongArray(10) }

        // digits = [..., d2, d1, d0]
        var totalWaviness = 0L
        var d2Weight = 1L

        for (i in 2..<length) {
            val prevDp = dp[i and 1]
            val currDp = dp[i and 1 xor 1]

            val d0 = digitsOfNum[i]
            val d1 = digitsOfNum[i - 1]
            val d2 = digitsOfNum[i - 2]

            for (d in 0..<10) {
                currDp[d] = d2Weight * (45 + 9 * d - d * d)
            }

            val prevTotal = prevDp.sum()
            for (d in 0..<10) {
                currDp[d] += prevTotal
            }

            val maxD0 = if (i == digitsOfNum.lastIndex) digitsOfNum.last() else 9
            for (d in 1..maxD0) {
                totalWaviness += currDp[d]
            }

            for (d in d1 + 1..<10) {
                totalWaviness -= prevDp[d]
            }

            val a = minOf(d1, d2 + 1)
            totalWaviness -= d2Weight / 10 * (19 - d1 - a) * (d1 - a) / 2

            val b = maxOf(d1, d2) + 1
            totalWaviness -= d2Weight / 10 * (b + 9) * (10 - b) / 2

            totalWaviness -=
                when {
                    d0 < d1 -> d2Weight * (d1 - a)
                    d0 > d1 -> d2Weight * (10 - b)
                    else -> 0
                }

            if (isWavy(d0, d1, d2)) {
                suffixWavinessOfNum--
            }
            totalWaviness -= suffixWavinessOfNum * d2Weight * (9 - d2)

            d2Weight *= 10
        }

        val d1 = digitsOfNum[length - 1]
        val d2 = digitsOfNum[length - 2]

        val a = minOf(d1, d2 + 1)
        totalWaviness -= d2Weight / 10 * (19 - d1 - a) * (d1 - a) / 2

        val b = maxOf(d1, d2) + 1
        totalWaviness -= d2Weight / 10 * (b + 9) * (10 - b) / 2

        return totalWaviness
    }

    private fun countWaviness(digits: List<Int>): Int {
        var count = 0
        for (i in 2..<digits.size) {
            if (isWavy(digits[i], digits[i - 1], digits[i - 2])) {
                count++
            }
        }

        return count
    }

    private fun isWavy(
        d0: Int,
        d1: Int,
        d2: Int,
    ): Boolean = (d0 < d1 && d1 > d2) || (d0 > d1 && d1 < d2)
}
