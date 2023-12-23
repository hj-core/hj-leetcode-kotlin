package com.hj.leetcode.kotlin.problem2483

/**
 * LeetCode page: [2483. Minimum Penalty for a Shop](https://leetcode.com/problems/minimum-penalty-for-a-shop/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of customers;
     */
    fun bestClosingTime(customers: String): Int {
        var relativePenalty = 0
        var minRelativePenalty = 0
        var result = 0

        for (stop in 1..customers.length) {
            when (customers[stop - 1]) {
                'Y' -> relativePenalty--
                'N' -> relativePenalty++
                else -> throw IllegalArgumentException()
            }

            if (relativePenalty < minRelativePenalty) {
                minRelativePenalty = relativePenalty
                result = stop
            }
        }
        return result
    }
}