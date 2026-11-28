package com.hj.leetcode.kotlin.problem3947

/**
 * LeetCode page: [3947. Maximum Number of Items From Sale II](https://leetcode.com/problems/maximum-number-of-items-from-sale-ii/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN+MLogM) and Space O(N+M) where N is the length of items
    // and M is the maximum factor of items.
    fun maximumSaleItems(
        items: Array<IntArray>,
        budget: Int,
    ): Int {
        val maxFactor = items.maxOf { item -> item[0] }
        // freeUntil[factor]:= the first purchase without a free copy
        val freeUntil = IntArray(maxFactor + 1)
        for ((factor, _) in items) {
            freeUntil[factor]++
        }
        for (factor in 1..maxFactor) {
            if (freeUntil[factor] > 0) {
                for (multiple in factor * 2..maxFactor step factor) {
                    freeUntil[factor] += freeUntil[multiple]
                }
            }
        }

        val comparePrice = Comparator<IntArray> { o1, o2 -> o1[1] - o2[1] }
        val items = items.sortedArrayWith(comparePrice)
        val minPrice = items[0][1]
        var copies = 0
        var budget = budget
        for ((factor, price) in items) {
            if (minPrice * 2 <= price) {
                break
            }
            val bought = minOf(budget / price, freeUntil[factor] - 1)
            copies += 2 * bought
            budget -= bought * price
        }
        copies += budget / minPrice
        return copies
    }
}
