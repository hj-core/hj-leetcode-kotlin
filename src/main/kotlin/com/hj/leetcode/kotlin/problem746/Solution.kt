package com.hj.leetcode.kotlin.problem746

/**
 * LeetCode page: [746. Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of cost;
     */
    fun minCostClimbingStairs(cost: IntArray): Int {
        var minCostPenultimate = cost[0]
        var minCostLast = cost[1]

        for (step in 2 until cost.size) {
            val minCost = cost[step] + minOf(minCostPenultimate, minCostLast)
            minCostPenultimate = minCostLast
            minCostLast = minCost
        }
        return minOf(minCostPenultimate, minCostLast)
    }
}