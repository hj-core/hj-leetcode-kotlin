package com.hj.leetcode.kotlin.problem2070

import kotlin.math.max

/**
 * LeetCode page: [2070. Most Beautiful Item for Each Query](https://leetcode.com/problems/most-beautiful-item-for-each-query/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN+MLogM) and Space O(N+M) where N is the size of items
     * and M is the size of queries.
     */
    fun maximumBeauty(
        items: Array<IntArray>,
        queries: IntArray,
    ): IntArray {
        val sortedItems = items.sortedBy { (price, beauty) -> price }
        val sortedQIndices = queries.indices.sortedBy { queries[it] }
        var itemIndex = 0
        var maxBeauty = 0
        val result = IntArray(queries.size)

        for (i in sortedQIndices) {
            val affordablePrice = queries[i]
            while (itemIndex < items.size && sortedItems[itemIndex][0] <= affordablePrice) {
                maxBeauty = max(maxBeauty, sortedItems[itemIndex][1])
                itemIndex += 1
            }
            result[i] = maxBeauty
        }
        return result
    }
}
