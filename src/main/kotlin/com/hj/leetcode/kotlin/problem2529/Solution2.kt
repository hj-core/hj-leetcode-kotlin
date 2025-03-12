package com.hj.leetcode.kotlin.problem2529

/**
 * LeetCode page: [2529. Maximum Count of Positive Integer and Negative Integer](https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of `nums`.
    fun maximumCount(nums: IntArray): Int {
        for (i in nums.indices) {
            if (nums[i] > 0 || nums[nums.lastIndex - i] < 0) {
                return nums.size - i
            }
        }
        return 0
    }
}
