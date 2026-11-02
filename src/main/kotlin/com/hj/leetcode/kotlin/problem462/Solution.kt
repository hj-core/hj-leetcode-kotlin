package com.hj.leetcode.kotlin.problem462

import kotlin.math.abs

/**
 * LeetCode page: [462. Minimum Moves to Equal Array Elements II](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/);
 */
class Solution {
    // Complexity:
    // Time O(NLogM) and Space O(1) where N is the length of nums and
    // M is the maximum allowed range of nums.
    fun minMoves2(nums: IntArray): Int {
        // Binary search for T in [low, high] where count(nums <= T) - count(nums > T)
        // first becomes non-negative.
        var low = -1_000_000_000
        var high = 1_000_000_000
        while (low < high) {
            val mid = low + (high - low) / 2
            if (nums.count { it <= mid } * 2 < nums.size) {
                low = mid + 1
            } else {
                high = mid
            }
        }

        return nums.sumOf { abs(it - low) }
    }
}
