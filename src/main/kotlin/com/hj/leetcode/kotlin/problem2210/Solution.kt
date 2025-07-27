package com.hj.leetcode.kotlin.problem2210

/**
 * LeetCode page: [2210. Count Hills and Valleys in an Array](https://leetcode.com/problems/count-hills-and-valleys-in-an-array/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun countHillValley(nums: IntArray): Int {
        var result = 0
        var left = nums[0]
        for (i in 1..<(nums.size - 1)) {
            if (nums[i] == nums[i + 1]) {
                continue
            }

            if (isHillOrValley(left, nums[i], nums[i + 1])) {
                result++
            }
            left = nums[i]
        }
        return result
    }

    private fun isHillOrValley(
        left: Int,
        mid: Int,
        right: Int,
    ): Boolean = (left < mid && mid > right) || (left > mid && mid < right)
}
