package com.hj.leetcode.kotlin.problem2064

import java.util.*

/**
 * LeetCode page: [2064. Minimized Maximum of Products Distributed to Any Store](https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/);
 */
class Solution2 {
    /* Complexity:
     * Time O((n-M)LogM) and Space O(M) where M is the size of quantities.
     */
    fun minimizedMaximum(
        n: Int,
        quantities: IntArray,
    ): Int {
        require(quantities.size <= n)
        if (quantities.size == n) {
            return quantities.max()
        }
        val pq = PriorityQueue<Distribution>(compareBy { -1 * it.minMaxPerStore() })
        for (quantity in quantities) {
            pq.offer(Distribution(quantity, 1))
        }

        repeat(n - quantities.size) {
            val top = pq.poll()
            top.stores += 1
            pq.offer(top)
        }
        return pq.peek().minMaxPerStore()
    }

    private class Distribution(
        val quantity: Int,
        var stores: Int,
    ) {
        fun minMaxPerStore(): Int = (quantity + stores - 1) / stores
    }
}
