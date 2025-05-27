package com.hj.leetcode.kotlin.problem2894

/**
 * LeetCode page: [2894. Divisible and Non-divisible Sums Difference](https://leetcode.com/problems/divisible-and-non-divisible-sums-difference/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun differenceOfSums(
        n: Int,
        m: Int,
    ): Int {
        val cntDivisible = n / m
        val num2 = m * (cntDivisible + 1) * cntDivisible / 2

        val totalSum = (n + 1) * n / 2
        return totalSum - num2 * 2
    }
}
