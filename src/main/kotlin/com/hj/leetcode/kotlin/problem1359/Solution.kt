package com.hj.leetcode.kotlin.problem1359

/**
 * LeetCode page: [1359. Count All Valid Pickup and Delivery Options](https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(1);
     */
    fun countOrders(n: Int): Int {
        val mod = 1_000_000_007
        var result = 1L
        for (numOrders in 1 until n) {
            val waysToInsertNewOrder = (2 * numOrders + 1) * (numOrders + 1)
            result = (result * waysToInsertNewOrder) % mod
        }
        return result.toInt()
    }
}