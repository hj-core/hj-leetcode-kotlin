package com.hj.leetcode.kotlin.problem3105

/**
 * LeetCode page: [3105. Longest Strictly Increasing or Strictly Decreasing Subarray](https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun longestMonotonicSubarray(nums: IntArray): Int {
        var result = 1
        var incLen = 1
        var decLen = 1
        for (i in 1..<nums.size) {
            val diff = nums[i] - nums[i - 1]
            incLen = if (diff > 0) incLen + 1 else 1
            decLen = if (diff < 0) decLen + 1 else 1
            result = maxOf(result, incLen, decLen)
        }

        return result
    }
}
