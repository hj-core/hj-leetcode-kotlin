package com.hj.leetcode.kotlin.problem2529

/**
 * LeetCode page: [2529. Maximum Count of Positive Integer and Negative Integer](https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/);
 */
class Solution {
    // Complexity:
    // Time O(LogN) and Space O(1) where N is the length of `nums`.
    fun maximumCount(nums: IntArray): Int =
        maxOf(
            partition(nums) { it < 0 },
            nums.size - partition(nums) { it <= 0 },
        )

    // `partition` returns the size of the left partition.
    // It requires that `arr` consists of numbers that satisfy `isLeaf` followed by numbers that do not.
    private fun partition(
        arr: IntArray,
        isLeft: (num: Int) -> Boolean,
    ): Int {
        if (arr.isEmpty() || !isLeft(arr[0])) {
            return 0
        }
        if (isLeft(arr.last())) {
            return arr.size
        }
        // The result is within the range [left, right]
        var left = 0
        var right = arr.lastIndex
        while (left < right) {
            val mid = (left + right) ushr 1
            if (isLeft(arr[mid])) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}
