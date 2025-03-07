package com.hj.leetcode.kotlin.problem2523

import kotlin.math.sqrt

/**
 * LeetCode page: [2523. Closest Prime Numbers in Range](https://leetcode.com/problems/closest-prime-numbers-in-range/);
 */
class Solution {
    // Complexity:
    // Time O((right-left)*sqrt(right)) and Space O(1).
    fun closestPrimes(
        left: Int,
        right: Int,
    ): IntArray {
        val result = intArrayOf(0, right)
        var prevPrime = 0

        for (num in left..right) {
            if (!num.isPrime()) {
                continue
            }
            if (prevPrime != 0 && num - prevPrime < result[1] - result[0]) {
                result[0] = prevPrime
                result[1] = num
            }
            prevPrime = num
        }

        if (result[0] == 0) {
            return intArrayOf(-1, -1)
        }
        return result
    }

    private fun Int.isPrime(): Boolean {
        require(0 < this)
        if (this <= 3) {
            return this != 1
        }
        if (this % 2 == 0 || this % 3 == 0) {
            return false
        }

        val floorSqrt = sqrt(this.toDouble()).toInt()
        for (num in 6..floorSqrt + 6 step 6) {
            if (this % (num - 1) == 0 || this % (num + 1) == 0) {
                return false
            }
        }
        return true
    }
}
