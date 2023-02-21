package com.hj.leetcode.kotlin.problem540

import java.lang.IllegalStateException

/**
 * LeetCode page: [540. Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N is the size of nums;
     */
    fun singleNonDuplicate(nums: IntArray): Int {
        var left = 0
        var right = nums.lastIndex

        while (left < right) {
            val mid = (left + right) ushr 1
            val midValue = nums[mid]
            val isOddSizeFromLeftUntilMid = (mid - left) and 1 == 1

            if (isOddSizeFromLeftUntilMid) {
                when (midValue) {
                    nums[mid - 1] -> left = mid + 1
                    nums[mid + 1] -> right = mid - 1
                    else -> throw IllegalStateException()
                }
            } else {
                when (midValue) {
                    nums[mid - 1] -> right = mid - 2
                    nums[mid + 1] -> left = mid + 2
                    else -> return midValue
                }
            }
        }

        return nums[left]
    }
}