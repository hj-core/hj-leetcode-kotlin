package com.hj.leetcode.kotlin.problem962

import kotlin.math.max

/**
 * LeetCode page: [962. Maximum Width Ramp](https://leetcode.com/problems/maximum-width-ramp/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of nums.
     */
    fun maxWidthRamp(nums: IntArray): Int {
        // Indices of strictly increasing values start at last and backward
        val rights = mutableListOf(nums.lastIndex)
        for (right in nums.indices.reversed()) {
            if (nums[rights.last()] < nums[right]) {
                rights.add(right)
            }
        }

        // Working with indices of strictly decreasing values start at first and forward
        var result = 0
        var lastLeftValue = nums[0] + 1
        var iRights = rights.lastIndex
        for ((left, value) in nums.withIndex()) {
            if (value < lastLeftValue) {
                while (iRights > 0 && nums[rights[iRights - 1]] >= value) {
                    iRights -= 1
                }
                lastLeftValue = value
                result = max(result, rights[iRights] - left)
            }
        }
        return result
    }
}
