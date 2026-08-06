package com.hj.leetcode.kotlin.problem3345

/**
 * LeetCode page: [3345. Smallest Divisible Digit Product I](https://leetcode.com/problems/smallest-divisible-digit-product-i/);
 */
class Solution {
    // Complexity:
    // Time O(Log n) and Space O(Log n).
    fun smallestNumber(
        n: Int,
        t: Int,
    ): Int {
        val lastDigit = n % 10
        if (lastDigit == 0) {
            return n
        }

        val base = digitProduct(n) / lastDigit
        for (d in lastDigit..<10) {
            if (base * d % t == 0) {
                return n - lastDigit + d
            }
        }
        return n - lastDigit + 10
    }

    private fun digitProduct(num: Int): Int {
        var product = 1
        var x = num
        while (x > 0) {
            product *= x % 10
            x /= 10
        }
        return product
    }
}
