package com.hj.leetcode.kotlin.problem2348

/**
 * LeetCode page: [2348. Number of Zero-Filled Subarrays](https://leetcode.com/problems/number-of-zero-filled-subarrays/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun zeroFilledSubarray(nums: IntArray): Long {
        var result = 0L
        var numTargetSubarraysEndedAtIndex = 0
        for (num in nums) {
            numTargetSubarraysEndedAtIndex = if (num == 0) numTargetSubarraysEndedAtIndex + 1 else 0
            result += numTargetSubarraysEndedAtIndex
        }
        return result
    }
}