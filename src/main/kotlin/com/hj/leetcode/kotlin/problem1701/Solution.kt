package com.hj.leetcode.kotlin.problem1701

import kotlin.math.max

/**
 * LeetCode page: [1701. Average Waiting Time](https://leetcode.com/problems/average-waiting-time/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of customers;
     */
    fun averageWaitingTime(customers: Array<IntArray>): Double {
        var totalWaitTime = 0.0
        var lastServeTime = -1
        for ((arrivalTime, prepareTime) in customers) {
            lastServeTime = max(lastServeTime, arrivalTime) + prepareTime
            totalWaitTime += lastServeTime - arrivalTime
        }
        return totalWaitTime / customers.size
    }
}