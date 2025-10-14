package com.hj.leetcode.kotlin.problem3349

/**
 * LeetCode page: [3349. Adjacent Increasing Subarrays Detection I](https://leetcode.com/problems/adjacent-increasing-subarrays-detection-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun hasIncreasingSubarrays(
        nums: List<Int>,
        k: Int,
    ): Boolean {
        var currLen = 1
        var isFirst = true

        for (i in 1..<nums.size) {
            if (nums[i] > nums[i - 1]) {
                currLen++
            } else if (currLen < k) {
                currLen = 1
                isFirst = true
            } else if (isFirst && currLen < 2 * k) {
                currLen = 1
                isFirst = false
            } else {
                return true
            }
        }
        return currLen >= 2 * k || (!isFirst && currLen >= k)
    }
}
