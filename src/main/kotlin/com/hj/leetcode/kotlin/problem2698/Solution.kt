package com.hj.leetcode.kotlin.problem2698

/**
 * LeetCode page: [2698. Find the Punishment Number of an Integer](https://leetcode.com/problems/find-the-punishment-number-of-an-integer/);
 */
class Solution {
    // Complexity:
    // Time O(n^(1+log10(2))) and Space O(log10(n)).
    fun punishmentNumber(n: Int): Int {
        var result = 0
        // Modulo 9 optimization (10^i mod 9 = 1):
        // We only consider those x where (x mod 9) equals (x^2 mod 9),
        // which means x mod 9 equals 0 or 1.
        for (x1 in 0..n step 9) {
            val square1 = x1 * x1
            if (canPartition(square1, x1)) {
                result += square1
            }

            if (x1 == n) {
                break
            }
            val x2 = x1 + 1
            val square2 = x2 * x2
            if (canPartition(square2, x2)) {
                result += square2
            }
        }
        return result
    }

    private fun canPartition(
        value: Int,
        targetSum: Int,
    ): Boolean {
        if (value == targetSum) {
            return true
        }

        var firstCut = 10 // Position of the rightmost split
        while (firstCut < value) {
            val leftTarget = targetSum - value % firstCut
            if (leftTarget < 0) {
                break
            }

            val leftValue = value / firstCut
            if (canPartition(leftValue, leftTarget)) {
                return true
            }
            firstCut *= 10
        }
        return false
    }
}
