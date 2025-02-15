package com.hj.leetcode.kotlin.problem2698

/**
 * LeetCode page: [2698. Find the Punishment Number of an Integer](https://leetcode.com/problems/find-the-punishment-number-of-an-integer/);
 */
class Solution {
    // Complexity:
    // Time O(n^(1+log_10(2)) and Space O(log(n)).
    fun punishmentNumber(n: Int): Int {
        var result = 0
        for (i in 1..n) {
            val square = i * i
            if (canPartition(square, i)) {
                result += square
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
