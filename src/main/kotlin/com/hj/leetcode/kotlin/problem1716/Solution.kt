package com.hj.leetcode.kotlin.problem1716

/**
 * LeetCode page: [1716. Calculate Money in Leetcode Bank](https://leetcode.com/problems/calculate-money-in-leetcode-bank/);
 */
class Solution {
    // Complexity:
    // Time O(n) and Space O(1).
    fun totalMoney(n: Int): Int {
        val fullWeeks = n / 7
        val extraDays = n - fullWeeks * 7

        return arithSeqSum(1, fullWeeks) * 7 +
            arithSeqSum(1, 6) * fullWeeks +
            arithSeqSum(fullWeeks + 1, fullWeeks + extraDays)
    }

    // Computes the sum of an arithmetic sequence from first
    // to last (inclusive) with step 1.
    private fun arithSeqSum(
        first: Int,
        last: Int,
    ): Int = (last + first) * (last - first + 1) / 2
}
