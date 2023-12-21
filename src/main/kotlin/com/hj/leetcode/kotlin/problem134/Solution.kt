package com.hj.leetcode.kotlin.problem134

/**
 * LeetCode page: [134. Gas Station](https://leetcode.com/problems/gas-station/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of gas;
     */
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        if (gas.sum() < cost.sum()) return -1

        var startStation = 0
        var remainingGas = 0

        for (index in gas.indices) {
            remainingGas += gas[index] - cost[index]
            if (remainingGas < 0) {
                startStation = index + 1
                remainingGas = 0
            }
        }
        return startStation
    }
}