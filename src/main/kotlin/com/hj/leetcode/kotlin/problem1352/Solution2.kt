package com.hj.leetcode.kotlin.problem1352

/**
 * LeetCode page: [1352. Product of the Last K Numbers](https://leetcode.com/problems/product-of-the-last-k-numbers/);
 */
class Solution2

class ProductOfNumbers2 {
    private val mod = Int.MAX_VALUE
    private var lastIndex = 0 // index starting from the last zero
    private val products = mutableListOf(1L) // prefix products since last zero
    private val invProducts = mutableListOf(1L) // modular inverses of the prefix produces

    // Complexity:
    // Time O(NLogM) and Space O(N) where N is the number of calls and
    // M is Int.MAX_VALUE.
    fun add(num: Int) {
        if (num == 0) {
            lastIndex = 0
            return
        }

        val product = (products[lastIndex] * num) % mod
        val invProduct = modInverse(product)

        lastIndex++
        if (lastIndex == products.size) {
            products.add(1L)
            invProducts.add(1L)
        }
        products[lastIndex] = product
        invProducts[lastIndex] = invProduct
    }

    private fun modInverse(product: Long): Long {
        var inverse = 1L
        var base = product
        var exp = mod - 2
        while (exp > 0) {
            if (exp and 1 == 1) {
                inverse = (inverse * base) % mod
            }
            base = (base * base) % mod
            exp = exp shr 1
        }
        return inverse
    }

    // Complexity:
    // Time O(1) and Space O(1).
    fun getProduct(k: Int): Int {
        if (k > lastIndex) {
            return 0
        }
        return (products[lastIndex] * invProducts[lastIndex - k]).mod(mod)
    }
}
