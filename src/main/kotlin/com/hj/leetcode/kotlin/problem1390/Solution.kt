package com.hj.leetcode.kotlin.problem1390

import kotlin.math.ceil
import kotlin.math.sqrt

/**
 * LeetCode page: [1390. Four Divisors](https://leetcode.com/problems/four-divisors/);
 */
class Solution {
    // Complexity:
    // Time O(N*Sqrt(M)) and Space O(1) where N is the length of
    // nums and M is the maximum value in nums.
    fun sumFourDivisors(nums: IntArray): Int =
        nums.sumOf(this::sumFourDivisorsForNum)

    // Returns the sum of divisors if num has exactly four divisors,
    // and 0 otherwise.
    private fun sumFourDivisorsForNum(num: Int): Int {
        // not true for num=1 but is masked by divisorCnt check
        var sum = 1 + num
        var divisorCount = 2

        val pBound = ceil(sqrt(num.toDouble())).toInt()
        if (num == pBound * pBound) {
            return 0
        }

        for (p in 2..<pBound) {
            if (num % p == 0) {
                if (divisorCount == 4) {
                    return 0
                }

                sum += p + num / p
                divisorCount += 2
            }
        }

        return if (divisorCount == 4) sum else 0
    }
}
