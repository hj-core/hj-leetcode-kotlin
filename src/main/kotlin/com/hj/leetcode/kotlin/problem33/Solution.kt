package com.hj.leetcode.kotlin.problem33

/**
 * LeetCode page: [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/);
 */
class Solution {
    // Complexity:
    // Time O(LogN) and Space O(1) where N is the size of nums.
    fun search(
        nums: IntArray,
        target: Int,
    ): Int {
        // is target in the first increasing subarray
        val bit0 = if (target < nums[0]) 0 else 1

        // Binary search for the index over range [left, right]
        var left = 0
        var right = nums.size - 1

        while (left <= right) {
            val mid = (left + right) ushr 1
            if (nums[mid] == target) {
                return mid
            }

            val bit1 = if (nums[mid] < nums[0]) 0 else 2
            val bit2 = if (nums[mid] < target) 0 else 4

            val mask = bit0 or bit1 or bit2
            if (mask == 0 || mask == 3 || mask == 6) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return -1
    }
}
