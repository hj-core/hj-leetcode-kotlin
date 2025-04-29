package com.hj.leetcode.kotlin.problem2962

/**
 * LeetCode page: [2962. Count Subarrays Where Max Element Appears at Least K Times](https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun countSubarrays(
        nums: IntArray,
        k: Int,
    ): Long {
        // For each possible end index of a subarray, we consider the earliest start index
        // such that nums[start..=end] becomes invalid.
        val max = nums.max()
        var result = 0L
        var start = 0
        var maxCount = 0

        for (end in nums.indices) {
            if (nums[end] == max) {
                maxCount++
            }
            while (maxCount >= k) {
                if (nums[start] == max) {
                    maxCount--
                }
                start++
            }
            result += start
        }
        return result
    }
}
