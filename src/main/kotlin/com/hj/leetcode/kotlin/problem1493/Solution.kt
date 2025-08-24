package com.hj.leetcode.kotlin.problem1493

/**
 * LeetCode page: [1493. Longest Subarray of 1's After Deleting One Element](https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the size of nums.
    fun longestSubarray(nums: IntArray): Int {
        var result = 0
        var start = -1
        var indexOfZero = -1

        // We use an inclusive end index
        for ((end, value) in nums.withIndex()) {
            if (value == 0) {
                start = indexOfZero + 1
                indexOfZero = end
            }
            result = maxOf(result, end - start)
        }

        val isAllOne = result == nums.size
        if (isAllOne) {
            result-- // We have to delete a '1'
        }
        return result
    }
}
