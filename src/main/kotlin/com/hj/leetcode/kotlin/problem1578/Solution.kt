package com.hj.leetcode.kotlin.problem1578

/**
 * LeetCode page: [1578. Minimum Time to Make Rope Colorful](https://leetcode.com/problems/minimum-time-to-make-rope-colorful/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of colors;
     */
    fun minCost(colors: String, neededTime: IntArray): Int {
        return neededTime.sum() - timeNotRemoved(neededTime, colors)
    }

    private fun timeNotRemoved(neededTime: IntArray, colors: String): Int {
        var result = 0
        var subRopeMaxCost = neededTime[0]

        for (index in 1..colors.lastIndex) {
            val inSameSubRope = colors[index] == colors[index - 1]
            if (inSameSubRope) {
                subRopeMaxCost = maxOf(subRopeMaxCost, neededTime[index])
            } else {
                result += subRopeMaxCost
                subRopeMaxCost = neededTime[index]
            }
        }
        result += subRopeMaxCost
        return result
    }
}