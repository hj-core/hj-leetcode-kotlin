package com.hj.leetcode.kotlin.problem81

/**
 * LeetCode page: [81. Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun search(nums: IntArray, target: Int): Boolean {
        return search(nums, target, 0, nums.lastIndex)
    }

    /**
     * Return if there is [target] in [nums] from [fromIndex] to [toIndex] (both ends inclusive).
     *
     * [nums] should be a sorted array or a rotated sorted array.
     */
    private tailrec fun search(nums: IntArray, target: Int, fromIndex: Int, toIndex: Int): Boolean {
        if (fromIndex == toIndex) {
            return nums[fromIndex] == target
        }

        val noRotation = nums[fromIndex] < nums[toIndex]
        if (noRotation) {
            return nums.binarySearch(target, fromIndex, toIndex + 1) >= 0
        }

        // Guarantee that value >= nums[fromIndex] is always to the left of the pivot
        if (nums[fromIndex] == nums[toIndex]) {
            return search(nums, target, fromIndex, toIndex - 1)
        }

        val mid = fromIndex + (toIndex - fromIndex) / 2
        val isMidLeftSide = nums[fromIndex] <= nums[mid]
        val isTargetLeftSide = nums[fromIndex] <= target

        if (isMidLeftSide && !isTargetLeftSide) {
            return search(nums, target, mid + 1, toIndex)
        }

        if (!isMidLeftSide && isTargetLeftSide) {
            return search(nums, target, fromIndex, mid - 1)
        }

        return when {
            target < nums[mid] -> search(nums, target, fromIndex, mid - 1)
            target > nums[mid] -> search(nums, target, mid + 1, toIndex)
            else -> true
        }
    }
}