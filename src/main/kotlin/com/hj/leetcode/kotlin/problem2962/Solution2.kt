package com.hj.leetcode.kotlin.problem2962

/**
 * LeetCode page: [2962. Count Subarrays Where Max Element Appears at Least K Times](https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun countSubarrays(
        nums: IntArray,
        k: Int,
    ): Long {
        var maxElem = nums[0]
        var maxCount = 0
        var start = 0
        var result = 0L

        for (end in nums.indices) {
            if (nums[end] > maxElem) {
                maxElem = nums[end]
                maxCount = 1
                start = end
                result = 0
            } else if (nums[end] == maxElem) {
                maxCount++
            }

            while (maxCount > k || nums[start] != maxElem) {
                if (nums[start] == maxElem) {
                    maxCount--
                }
                start++
            }
            if (maxCount == k) {
                result += start + 1
            }
        }
        return result
    }
}
