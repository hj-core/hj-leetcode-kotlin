package com.hj.leetcode.kotlin.problem852

/**
 * LeetCode page: [852. Peak Index in a Mountain Array](https://leetcode.com/problems/peak-index-in-a-mountain-array/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N is the size of arr;
     */
    fun peakIndexInMountainArray(arr: IntArray): Int {
        var left = 0
        var right = arr.lastIndex
        while (left <= right) {
            val mid = left + (right - left) / 2
            when {
                arr[mid] < arr[mid + 1] -> left = mid + 1
                arr[mid] < arr[mid - 1] -> right = mid - 1
                else -> return mid
            }
        }
        throw IllegalStateException()
    }
}