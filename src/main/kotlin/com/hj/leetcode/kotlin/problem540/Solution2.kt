package com.hj.leetcode.kotlin.problem540

/**
 * LeetCode page: [540. Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array/);
 */
class Solution2 {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N is the size of nums;
     */
    fun singleNonDuplicate(nums: IntArray): Int {
        var left = 0
        var right = nums.lastIndex

        while (left < right) {
            val mid = (left + right) ushr 1
            val n = mid or 1 // the size of sub-array from left to n is guaranteed to be even
            if (nums[n] == nums[n - 1]) {
                left = n + 1
            } else {
                right = n - 1
            }
        }
        return nums[left]
    }
}