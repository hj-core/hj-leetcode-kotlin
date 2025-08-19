package com.hj.leetcode.kotlin.problem2348

/**
 * LeetCode page: [2348. Number of Zero-Filled Subarrays](https://leetcode.com/problems/number-of-zero-filled-subarrays/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun zeroFilledSubarray(nums: IntArray): Long {
        var result = 0L
        var start = -1
        // We accumulate the number of zero-filled subarrays
        // ending at each index.
        for ((end, num) in nums.withIndex()) {
            if (num == 0) {
                result += end - start
            } else {
                start = end
            }
        }
        return result
    }
}
