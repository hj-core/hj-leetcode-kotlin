package com.hj.leetcode.kotlin.problem904

/**
 * LeetCode page: [904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of fruits.
    fun totalFruit(fruits: IntArray): Int {
        var indexL = 0
        val freqs = IntArray(fruits.size)
        var types = 0

        for (fruit in fruits) {
            freqs[fruit]++
            if (freqs[fruit] == 1) {
                types++
            }

            if (types > 2) {
                val oldFruit = fruits[indexL]
                freqs[oldFruit]--
                if (freqs[oldFruit] == 0) {
                    types--
                }
                indexL++
            }
        }
        return fruits.size - indexL
    }
}
