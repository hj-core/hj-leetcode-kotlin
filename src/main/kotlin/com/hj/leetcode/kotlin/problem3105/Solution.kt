package com.hj.leetcode.kotlin.problem3105

/**
 * LeetCode page: [3105. Longest Strictly Increasing or Strictly Decreasing Subarray](https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of nums.
     */
    fun longestMonotonicSubarray(nums: IntArray): Int {
        var result = 1
        var incLength = 1
        var decLength = 1

        for (i in 1..<nums.size) {
            val diff = nums[i] - nums[i - 1]
            incLength = if (diff > 0) incLength + 1 else 1
            decLength = if (diff < 0) decLength + 1 else 1
            result = maxOf(result, incLength, decLength)
        }
        return result
    }
}
