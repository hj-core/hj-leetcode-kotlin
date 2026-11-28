package com.hj.leetcode.kotlin.problem3947

class Solution2 {
    // Complexity:
    // Time O(NLogN+M*sqrt(M)) and Space O(N+M) where N is the length of items
    // and M is the maximum factor of items.
    fun maximumSaleItems(
        items: Array<IntArray>,
        budget: Int,
    ): Int {
        val maxFactor = items.maxOf { item -> item[0] }
        // freeUntil[factor]:= the first purchase without a free copy
        val freeUntil = IntArray(maxFactor + 1)
        for ((factor, _) in items) {
            var i = 1
            while (i * i < factor) {
                val j = factor / i
                if (i * j == factor) {
                    freeUntil[i]++
                    freeUntil[j]++
                }
                i++
            }
            if (i * i == factor) {
                freeUntil[i]++
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
