package com.hj.leetcode.kotlin.problem2401

/**
 * LeetCode page: [2401. Longest Nice Subarray](https://leetcode.com/problems/longest-nice-subarray/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun longestNiceSubarray(nums: IntArray): Int {
        var minLeft = 0
        var windowXor = 0
        var result = 0

        for ((right, num) in nums.withIndex()) {
            while (windowXor and num != 0) {
                windowXor = windowXor xor nums[minLeft]
                minLeft++
            }
            windowXor = windowXor xor num
            result = maxOf(result, right - minLeft + 1)
        }
        return result
    }
}
