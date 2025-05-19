package com.hj.leetcode.kotlin.problem3024

/**
 * LeetCode page: [3024. Type of Triangle](https://leetcode.com/problems/type-of-triangle/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun triangleType(nums: IntArray): String =
        when {
            nums.max() * 2 >= nums.sum() -> "none"
            nums[0] == nums[1] && nums[1] == nums[2] -> "equilateral"
            nums[0] == nums[1] || nums[1] == nums[2] || nums[0] == nums[2] -> "isosceles"
            else -> "scalene"
        }
}
