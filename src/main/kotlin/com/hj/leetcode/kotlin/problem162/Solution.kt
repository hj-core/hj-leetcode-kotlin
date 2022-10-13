package com.hj.leetcode.kotlin.problem162

/**
 * LeetCode page: [162. Find Peak Element](https://leetcode.com/problems/find-peak-element/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N is the size of nums;
     */
    fun findPeakElement(nums: IntArray): Int {
        var leftIndex = 0
        var rightIndex = nums.lastIndex

        while (leftIndex < rightIndex) {
            val midIndex = (leftIndex + rightIndex) ushr 1
            when {
                nums[midIndex] > nums[midIndex + 1] -> rightIndex = midIndex
                nums[midIndex] < nums[midIndex + 1] -> leftIndex = midIndex + 1
            }
        }
        return leftIndex
    }
}