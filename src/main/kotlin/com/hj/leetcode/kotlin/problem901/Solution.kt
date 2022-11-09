package com.hj.leetcode.kotlin.problem901

/**
 * LeetCode page: [901. Online Stock Span](https://leetcode.com/problems/online-stock-span/);
 */
class StockSpanner() {
    private val recordStack = mutableListOf<Record>()

    private class Record(val price: Int, val span: Int)

    /* Complexity of N calls:
     * Time O(N) and Space O(N);
     */
    fun next(price: Int): Int {
        var span = 1

        while (shouldPop(price)) {
            val popRecord = recordStack.removeAt(recordStack.lastIndex)
            span += popRecord.span
        }

        recordStack.add(Record(price, span))
        return span
    }

    private fun shouldPop(newPrice: Int): Boolean {
        return recordStack
            .lastOrNull()
            ?.let { it.price <= newPrice }
            ?: false
    }
}