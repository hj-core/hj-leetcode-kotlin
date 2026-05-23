package com.hj.leetcode.kotlin.problem1752

/**
 * LeetCode page: [1752. Check if Array Is Sorted and Rotated](https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun check(nums: IntArray): Boolean {
        // nums[..<i]:= the first non-decreasing subarray
        val i = (1..<nums.size).firstOrNull { nums[it] < nums[it - 1] }
        if (i == null) {
            return true
        }

        // nums[i..]:= the second non-decreasing subarray
        if (nums[0] < nums.last()) {
            return false
        }
        return (i + 1..<nums.size).all { nums[it - 1] <= nums[it] }
    }
}
