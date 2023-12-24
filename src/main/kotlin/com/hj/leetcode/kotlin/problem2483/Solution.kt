package com.hj.leetcode.kotlin.problem2483

/**
 * LeetCode page: [2483. Minimum Penalty for a Shop](https://leetcode.com/problems/minimum-penalty-for-a-shop/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of customers;
     */
    fun bestClosingTime(customers: String): Int {
        val totalY = customers.count { it == 'Y' }
        var nCountBeforeStop = 0
        var result = 0
        var minPenalty = totalY

        for (stop in 0..customers.length) {
            val yCountBeforeStop = stop - nCountBeforeStop
            val penalty = nCountBeforeStop + (totalY - yCountBeforeStop)
            if (penalty < minPenalty) {
                minPenalty = penalty
                result = stop
            }

            if (stop < customers.length && customers[stop] == 'N') {
                nCountBeforeStop++
            }
        }
        return result
    }
}