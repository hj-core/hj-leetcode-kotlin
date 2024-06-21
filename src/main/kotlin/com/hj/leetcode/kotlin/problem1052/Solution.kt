package com.hj.leetcode.kotlin.problem1052

import kotlin.math.max

/**
 * LeetCode page: [1052. Grumpy Bookstore Owner](https://leetcode.com/problems/grumpy-bookstore-owner/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of customers and grumpy;
     */
    fun maxSatisfied(customers: IntArray, grumpy: IntArray, minutes: Int): Int {
        var windowBenefit = (0..<minutes)
            .sumOf { t -> if (grumpy[t] == 1) customers[t] else 0 }
        var maxBenefit = windowBenefit

        for (windowStart in 1..(customers.size - minutes)) {
            val windowEnd = windowStart + minutes - 1
            val lastStart = windowStart - 1
            val gain = if (grumpy[windowEnd] == 1) customers[windowEnd] else 0
            val loss = if (grumpy[lastStart] == 1) customers[lastStart] else 0
            windowBenefit += gain - loss
            maxBenefit = max(maxBenefit, windowBenefit)
        }
        return countSatisfied(customers, grumpy) + maxBenefit
    }

    private fun countSatisfied(customers: IntArray, grumpy: IntArray): Int {
        return customers.indices.sumOf { t ->
            if (grumpy[t] == 0) customers[t] else 0
        }
    }
}