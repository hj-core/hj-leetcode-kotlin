package com.hj.leetcode.kotlin.problem2016

/**
 * LeetCode page: [2016. Maximum Difference Between Increasing Elements](https://leetcode.com/problems/maximum-difference-between-increasing-elements/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun maximumDifference(nums: IntArray): Int {
        var prevMin = nums[0]
        var result = -1
        for (num in nums) {
            if (num > prevMin) {
                result = maxOf(result, num - prevMin)
            } else {
                prevMin = num
            }
        }
        return result
    }
}
