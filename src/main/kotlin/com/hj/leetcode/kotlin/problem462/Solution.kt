package com.hj.leetcode.kotlin.problem462

import kotlin.math.abs

/**
 * LeetCode page: [462. Minimum Moves to Equal Array Elements II](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/);
 */
class Solution {
    // Complexity:
    // Time O(NLogM) and Space O(1) where N is the length of nums and
    // M is the range of Int type.
    fun minMoves2(nums: IntArray): Int {
        var low = Int.MIN_VALUE
        var high = Int.MAX_VALUE
        while (low <= high) {
            val mid = (low + high) / 2
            if (nums.count { it <= mid } * 2 < nums.size) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }

        return nums.sumOf { abs(it - low) }
    }
}
