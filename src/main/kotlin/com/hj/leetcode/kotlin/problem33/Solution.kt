package com.hj.leetcode.kotlin.problem33

/**
 * LeetCode page: [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N is the size of nums;
     */
    fun search(nums: IntArray, target: Int): Int {
        val indexOfMin = indexOfMin(nums)
        val (fromIndex, toIndex) =
            if (target > nums.last()) Pair(0, indexOfMin) else Pair(indexOfMin, nums.size)
        return nums
            .binarySearch(target, fromIndex, toIndex)
            .let { if (it < 0) -1 else it }
    }

    /**
     * [nums] should be a sorted array with distinct values, and it may be rotated.
     *
     * Return the index of the minimum value in [nums].
     */
    private fun indexOfMin(nums: IntArray): Int {
        if (nums.size == 1 || nums[0] < nums.last()) {
            return 0
        }

        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = left + (right - left) / 2

            when {
                nums[mid] > nums[mid + 1] -> return mid + 1
                nums[mid] < nums[0] -> right = mid
                else -> left = mid + 1
            }
        }
        throw IllegalStateException()
    }
}