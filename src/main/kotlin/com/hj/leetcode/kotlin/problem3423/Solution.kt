package com.hj.leetcode.kotlin.problem3423

import kotlin.math.abs

/**
 * LeetCode page: [3423. Maximum Difference Between Adjacent Elements in a Circular Array](https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun maxAdjacentDistance(nums: IntArray): Int {
        require(nums.size > 1)
        if (nums.size == 2) {
            return abs(nums[0] - nums[1])
        }

        var result = abs(nums.first() - nums.last())
        for (i in 1..<nums.size) {
            result = maxOf(result, nums[i] - nums[i - 1])
        }
        return result
    }
}
