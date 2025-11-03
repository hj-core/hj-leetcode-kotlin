package com.hj.leetcode.kotlin.problem1578

/**
 * LeetCode page: [1578. Minimum Time to Make Rope Colorful](https://leetcode.com/problems/minimum-time-to-make-rope-colorful/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of colors.
    fun minCost(
        colors: String,
        neededTime: IntArray,
    ): Int {
        var totalCost = neededTime[0]
        var maxCostInGroup = neededTime[0]

        for (i in 1..<colors.length) {
            totalCost += neededTime[i]

            if (colors[i] == colors[i - 1]) {
                maxCostInGroup =
                    maxOf(maxCostInGroup, neededTime[i])
            } else {
                totalCost -= maxCostInGroup
                maxCostInGroup = neededTime[i]
            }
        }

        totalCost -= maxCostInGroup
        return totalCost
    }
}
