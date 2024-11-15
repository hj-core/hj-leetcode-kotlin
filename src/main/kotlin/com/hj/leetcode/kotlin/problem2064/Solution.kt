package com.hj.leetcode.kotlin.problem2064

/**
 * LeetCode page: [2064. Minimized Maximum of Products Distributed to Any Store](https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/);
 */
class Solution {
    /* Complexity:
     * Time O(MLogK) and Space O(1) where M is the length of quantities
     * and K is the maximum possible quantities for a product type.
     */
    fun minimizedMaximum(
        n: Int,
        quantities: IntArray,
    ): Int {
        require(quantities.size <= n)
        if (quantities.size == n) {
            return quantities.max()
        }
        val maxQuantityPerType = 100_000
        var lower = 1
        var upper = maxQuantityPerType

        while (lower <= upper) {
            val mid = lower + (upper - lower) / 2
            if (n < minStores(quantities, mid)) {
                lower = mid + 1
            } else {
                upper = mid - 1
            }
        }
        return lower
    }

    private fun minStores(
        quantities: IntArray,
        maxPerStore: Int,
    ): Int =
        quantities.fold(0) { acc, quantity ->
            acc + (quantity + maxPerStore - 1) / maxPerStore
        }
}
