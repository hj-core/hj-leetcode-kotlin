package com.hj.leetcode.kotlin.problem3350

/**
 * LeetCode page: [3350. Adjacent Increasing Subarrays Detection II](https://leetcode.com/problems/adjacent-increasing-subarrays-detection-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun maxIncreasingSubarrays(nums: List<Int>): Int {
        var result = 0
        var len1 = 0
        var len2 = 1

        for (i in 1..<nums.size) {
            if (nums[i] > nums[i - 1]) {
                len2++
            } else {
                result = maxOf(result, minOf(len1, len2), len2 / 2)
                len1 = len2
                len2 = 1
            }
        }
        result = maxOf(result, minOf(len1, len2), len2 / 2)
        return result
    }
}
