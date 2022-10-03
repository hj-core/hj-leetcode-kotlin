package com.hj.leetcode.kotlin.problem1578

/**
 * LeetCode page: [1578. Minimum Time to Make Rope Colorful](https://leetcode.com/problems/minimum-time-to-make-rope-colorful/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of colors;
     */
    fun minCost(colors: String, neededTime: IntArray): Int {
        var minCost = 0

        var costSumOfSameColorInterval = neededTime[0]
        var maxCostInSameColorInterval = neededTime[0]

        for (index in 1..colors.lastIndex) {
            val isSameColorAsPrev = colors[index] == colors[index - 1]
            if (isSameColorAsPrev) {
                costSumOfSameColorInterval += neededTime[index]
                maxCostInSameColorInterval = maxOf(maxCostInSameColorInterval, neededTime[index])
            } else {
                minCost += costSumOfSameColorInterval - maxCostInSameColorInterval
                costSumOfSameColorInterval = neededTime[index]
                maxCostInSameColorInterval = neededTime[index]
            }
        }

        minCost += costSumOfSameColorInterval - maxCostInSameColorInterval
        return minCost
    }
}