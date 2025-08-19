package com.hj.leetcode.kotlin.problem2348

/**
 * LeetCode page: [2348. Number of Zero-Filled Subarrays](https://leetcode.com/problems/number-of-zero-filled-subarrays/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun zeroFilledSubarray(nums: IntArray): Long {
        var result = 0L
        // We accumulate the number of zero-filled subarrays
        // ending at each index.
        var count = 0
        for (num in nums) {
            if (num == 0) {
                count++
                result += count
            } else {
                count = 0
            }
        }
        return result
    }
}
