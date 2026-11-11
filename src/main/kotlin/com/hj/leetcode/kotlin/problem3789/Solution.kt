package com.hj.leetcode.kotlin.problem3789

/**
 * LeetCode page: [3789. Minimum Cost to Acquire Required Items](https://leetcode.com/problems/minimum-cost-to-acquire-required-items/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun minimumCost(
        cost1: Int,
        cost2: Int,
        costBoth: Int,
        need1: Int,
        need2: Int,
    ): Long {
        val needBoth = minOf(need1, need2).toLong()
        val costBoth = minOf(cost1 + cost2, costBoth)
        val need1 = need1 - needBoth
        val cost1 = minOf(cost1, costBoth)
        val need2 = need2 - needBoth
        val cost2 = minOf(cost2, costBoth)
        return costBoth * needBoth + cost1 * need1 + cost2 * need2
    }
}
