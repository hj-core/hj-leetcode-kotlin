package com.hj.leetcode.kotlin.problem2348

/**
 * LeetCode page: [2348. Number of Zero-Filled Subarrays](https://leetcode.com/problems/number-of-zero-filled-subarrays/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun zeroFilledSubarray(nums: IntArray): Long {
        var result = 0L
        var start = 0
        // We accumulate the number of zero-filled subarrays
        // ending at each index.
        while (start < nums.size) {
            if (nums[start] != 0) {
                start++
            } else {
                var end = start + 1
                while (end < nums.size && nums[end] == 0) {
                    end++
                }
                val size = end - start
                result += (size + 1L) * size / 2
                start = end + 1
            }
        }
        return result
    }
}
