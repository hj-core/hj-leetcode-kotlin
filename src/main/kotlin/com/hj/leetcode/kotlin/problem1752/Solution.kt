package com.hj.leetcode.kotlin.problem1752

/**
 * LeetCode page: [1752. Check if Array Is Sorted and Rotated](https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of nums.
     */
    fun check(nums: IntArray): Boolean {
        // Check if `nums` consists of a single sorted segment or two compatible sorted segments.
        val firstEnd = (0..<nums.lastIndex).firstOrNull { nums[it] > nums[it + 1] }
        if (firstEnd == null) {
            return true
        }
        return nums.last() <= nums[0] && (firstEnd + 1..<nums.lastIndex).all { nums[it] <= nums[it + 1] }
    }
}
