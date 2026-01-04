package com.hj.leetcode.kotlin.problem1390

import kotlin.math.sqrt

/**
 * LeetCode page: [1390. Four Divisors](https://leetcode.com/problems/four-divisors/);
 */
class Solution2 {
    // Complexity:
    // Time O(N*Sqrt(M)) and Space O(1) where N is the length of
    // nums and M is the maximum value in nums.
    fun sumFourDivisors(nums: IntArray): Int =
        nums.sumOf(this::sumFourDivisorsForNum)

    // Returns the sum of divisors if num has exactly four divisors,
    // and 0 otherwise.
    private fun sumFourDivisorsForNum(num: Int): Int {
        val checkPrimeDivisor: (Int) -> Int = { p ->
            val q = num / p
            if ((q != p && isPrime(q)) || q == p * p) {
                1 + p + q + num
            } else {
                0
            }
        }

        if (num and 1 == 0) {
            return checkPrimeDivisor(2)
        }

        if (num % 3 == 0) {
            return checkPrimeDivisor(3)
        }

        val kBound = sqrt(num.toDouble()).toInt() + 2
        for (k in 6..<kBound step 6) {
            // No need to check isPrime(p), as we cannot reach a
            // composite p that divides num.
            var p = k - 1
            if (num % p == 0) {
                return checkPrimeDivisor(p)
            }

            p = k + 1
            if (num % p == 0) {
                return checkPrimeDivisor(p)
            }
        }

        return 0
    }

    private fun isPrime(num: Int): Boolean {
        if (num <= 3) {
            return num > 1
        }

        if (num and 1 == 0 || num % 3 == 0) {
            return false
        }

        val kBound = sqrt(num.toDouble()).toInt() + 2
        for (k in 6..<kBound step 6) {
            // k-1 and k+1 can be non-prime, but it doesn't matter
            if (num % (k - 1) == 0 || num % (k + 1) == 0) {
                return false
            }
        }

        return true
    }
}
