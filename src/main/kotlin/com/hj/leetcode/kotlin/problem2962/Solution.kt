package com.hj.leetcode.kotlin.problem2962

/**
 * LeetCode page: [2962. Count Subarrays Where Max Element Appears at Least K Times](https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun countSubarrays(nums: IntArray, k: Int): Long {
        val max = nums.max()
        var result = 0L
        var start = 0
        var countMax = 0

        // For each end, try to find the latest start that contains k maxes
        for (end in nums.indices) {
            if (nums[end] == max) {
                countMax += 1
            }

            while (start <= end && (nums[start] != max || countMax > k)) {
                if (nums[start] == max) {
                    countMax -= 1
                }
                start += 1
            }

            if (countMax == k) {
                result += start + 1
            }
        }
        return result
    }
}