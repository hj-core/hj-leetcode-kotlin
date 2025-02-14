package com.hj.leetcode.kotlin.problem1352

/**
 * LeetCode page: [1352. Product of the Last K Numbers](https://leetcode.com/problems/product-of-the-last-k-numbers/);
 */
class Solution

class ProductOfNumbers {
    private val buffer = mutableListOf<Int>() // Prefix products starting after the last zero
    private var activeLen = 0
    private val lastIndex get() = activeLen - 1

    // Complexity for N Calls:
    // Time O(N) and Space O(N).
    fun add(num: Int) {
        if (num == 0) {
            activeLen = 0
            return
        }

        if (0 < activeLen && Int.MAX_VALUE / buffer[lastIndex] < num) {
            throw IllegalStateException("Number overflowed")
        }

        val prefixProduct = if (0 < activeLen) num * buffer[lastIndex] else num
        if (activeLen == buffer.size) {
            buffer.add(prefixProduct)
        } else {
            buffer[activeLen] = prefixProduct
        }
        activeLen++
    }

    // Complexity:
    // Time O(1) and Space O(1).
    fun getProduct(k: Int): Int =
        when {
            k > activeLen -> 0
            k < activeLen -> buffer[lastIndex] / buffer[lastIndex - k]
            else -> buffer[lastIndex]
        }
}
