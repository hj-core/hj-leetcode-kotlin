package com.hj.leetcode.kotlin.problem34

/**
 * LeetCode page: [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N is the size of nums;
     */
    fun searchRange(nums: IntArray, target: Int): IntArray {
        return intArrayOf(
            nums.binarySearchFirst(target),
            nums.binarySearchLast(target)
        )
    }

    private fun IntArray.binarySearchFirst(target: Int): Int {
        if (this.isEmpty() || target < first() || target > last()) {
            return -1
        }

        var left = 0
        var right = lastIndex

        while (left < right) {
            val mid = (left + right) ushr 1
            if (this[mid] < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return if (this[left] == target) left else -1
    }

    private fun IntArray.binarySearchLast(target: Int): Int {
        if (this.isEmpty() || target < first() || target > last()) {
            return -1
        }

        var left = 0
        var right = lastIndex

        while (left <= right) {
            val mid = (left + right) ushr 1
            if (this[mid] <= target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return if (this[right] == target) right else -1
    }
}