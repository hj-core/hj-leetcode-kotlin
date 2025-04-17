package com.hj.leetcode.kotlin.problem2176

import kotlin.math.sqrt

/**
 * LeetCode page: [2176. Count Equal and Divisible Pairs in an Array](https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/);
 */
class Solution {
    // Complexity:
    // Time O(N*sqrt(k)) and Space O(N+sqrt(k)) where N is the length of nums.
    fun countPairs(
        nums: IntArray,
        k: Int,
    ): Int {
        var result = 0
        val groupIndices = nums.indices.groupBy { nums[it] }
        val kDivisors = getDivisors(k)

        for ((_, indices) in groupIndices) {
            result += countDivisiblePairs(indices, k, kDivisors)
        }
        return result
    }

    // getDivisors returns the positive integers that divide num.
    private fun getDivisors(num: Int): List<Int> = buildList {
        for (x in 1..sqrt(num.toDouble()).toInt()) {
            if (num % x == 0) {
                add(x)
                (num / x).let { if (it != x) add(it) }
            }
        }
    }

    // countDivisiblePairs returns the number of divisible pairs in nums such that k divide (i*j).
    private fun countDivisiblePairs(
        nums: List<Int>,
        k: Int,
        kDivisors: List<Int>,
    ): Int {
        // entry=(divisor, the number of numbers that are divisible by the divisor)
        val countSuppliers = mutableMapOf<Int, Int>()
        var result = 0
        for (num in nums) {
            result += countSuppliers[k / gcd(num, k)] ?: 0

            for (divisor in kDivisors) {
                if (num % divisor == 0) {
                    countSuppliers.compute(divisor) { _, v -> 1 + (v ?: 0) }
                }
            }
        }
        return result
    }

    private tailrec fun gcd(a: Int, b: Int): Int = if (a == 0) b else gcd(b % a, a)
}
