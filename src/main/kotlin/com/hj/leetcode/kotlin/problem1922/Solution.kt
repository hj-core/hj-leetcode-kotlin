package com.hj.leetcode.kotlin.problem1922

/**
 * LeetCode page: [1922. Count Good Numbers](https://leetcode.com/problems/count-good-numbers/);
 */
class Solution {
    // Complexity:
    // Time O(Log(n)) and Space O(1).
    fun countGoodNumbers(n: Long): Int {
        val mod = 1_000_000_007L
        var result = modPow(5L * 4, n / 2, mod)

        if (n and 1L == 1L) {
            result = (result * 5) % mod
        }
        return result.toInt()
    }

    // modPow computes (base^exp) % mod.
    private fun modPow(
        base: Long,
        exp: Long,
        mod: Long,
    ): Long {
        var result = 1L
        var newBase = base
        var newExp = exp

        while (newExp > 0) {
            if (newExp % 2 == 1L) {
                result = (result * newBase) % mod
            }
            newBase = (newBase * newBase) % mod
            newExp /= 2
        }
        return result
    }
}
