package com.hj.leetcode.kotlin.problem1287

/**
 * LeetCode page: [1287. Element Appearing More Than 25% In Sorted Array](https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N is the size of arr;
     */
    fun findSpecialInteger(arr: IntArray): Int {
        val threshold = arr.size / 4 + 1
        for (index in arr.indices step threshold) {
            if (count(arr, arr[index]) >= threshold) {
                return arr[index]
            }
        }
        throw NoSuchElementException()
    }

    private fun count(sorted: IntArray, element: Int): Int {
        val left = leftInsertionIndex(sorted, element)
        if (left == sorted.size || sorted[left] != element) {
            return 0
        }

        val right = rightInsertionIndex(sorted, element)
        return right - left
    }

    private fun leftInsertionIndex(sorted: IntArray, target: Int): Int {
        if (target <= sorted.first()) {
            return 0
        }
        if (target > sorted.last()) {
            return sorted.size
        }

        var left = 0
        var right = sorted.lastIndex
        while (left < right) {
            val mid = (left + right) ushr 1
            val value = sorted[mid]

            if (value < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }

    private fun rightInsertionIndex(sorted: IntArray, target: Int): Int {
        if (target >= sorted.last()) {
            return sorted.size
        }
        if (target < sorted.first()) {
            return 0
        }

        var left = 0
        var right = sorted.lastIndex
        while (left < right) {
            val mid = (left + right) ushr 1
            val value = sorted[mid]

            if (value <= target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}