package com.hj.leetcode.kotlin.problem154

/**
 * LeetCode page: [154. Find Minimum in Rotated Sorted Array II](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun findMin(nums: IntArray): Int {
        if (nums[0] < nums.last()) {
            return nums[0]
        }

        // Binary search for the minimum element index over range [left, right]
        var left = 0
        var right = nums.lastIndex
        val threshold = (nums[0] + nums.last()) / 2

        while (left < right) {
            val mid = (left + right) ushr 1
            when {
                nums[mid] > threshold -> left = mid + 1
                nums[mid] < threshold -> right = mid
                nums[left] >= nums[right] -> left++
                else -> break // left has already been the minimum element index
            }
        }

        return nums[left]
    }
}
