package com.hj.leetcode.kotlin.problem2843

/**
 * LeetCode page: [2843. Count Symmetric Integers](https://leetcode.com/problems/count-symmetric-integers/);
 */
class Solution {
    // Complexity:
    // The implementation is too specific to the problem and cannot scale.
    fun countSymmetricIntegers(
        low: Int,
        high: Int,
    ): Int {
        require(high <= 10000) { "problem constraints have changed" }

        // Free us from considering the special case where high equals 10000.
        if (high == 10000) {
            return countSymmetricIntegers(low, 9999)
        }

        var result = 0
        // Handle the two-digit cases
        for (num in 11..(minOf(high, 99)) step 11) {
            if (low <= num) {
                result++
            }
        }

        // Handle the four-digit cases
        val maxLeftTwo = high / 100
        if (maxLeftTwo < 10) {
            return result
        }

        val leftSumFreq = IntArray(1 + 9 * 2) // The frequencies of the left two digits' sum
        val rightSumFreq = IntArray(1 + 9 * 2) // The frequencies of the right two digits' sum
        val minLeftTwo = maxOf(low / 100, 10)

        if (minLeftTwo == maxLeftTwo) {
            // Cases where we have only one choice for the left two digits
            val minRightTwo = low % 100
            val maxRightTwo = high % 100

            for (rightTwo in minRightTwo..maxRightTwo) {
                rightSumFreq[rightTwo % 10 + rightTwo / 10]++
            }
            result += rightSumFreq[maxLeftTwo % 10 + maxLeftTwo / 10]
        } else {
            // If there are multiple choices for the left two digits,
            // we first handle the case where these two digits are the same as high.
            for (rightTwo in 0..(high % 100)) {
                rightSumFreq[rightTwo % 10 + rightTwo / 10]++
            }
            result += rightSumFreq[maxLeftTwo % 10 + maxLeftTwo / 10]

            // For the remaining choices for the left two digits,
            // the right two digits can go up to 99.
            for (rightTwo in (1 + high % 100)..<100) {
                rightSumFreq[rightTwo % 10 + rightTwo / 10]++
            }

            for (leftTwo in minLeftTwo..<maxLeftTwo) {
                leftSumFreq[leftTwo % 10 + leftTwo / 10]++
            }

            for (sum in 1..18) {
                result += leftSumFreq[sum] * rightSumFreq[sum]
            }
        }

        return result
    }
}
