package com.hj.leetcode.kotlin.problem153

/**
 * LeetCode page: [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/);
 */
class Solution {
    // Complexity:
    // Time O(Log N) and Space O(1) where N is the length of nums.
    fun findMin(nums: IntArray): Int {
        // Binary search for the pivot i over range [left, right]
        var left = 0
        var right = nums.lastIndex

        while (nums[right] < nums[left]) {
            val mid = (left + right) ushr 1

            if (nums[left] <= nums[mid]) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return nums[left]
    }
}
