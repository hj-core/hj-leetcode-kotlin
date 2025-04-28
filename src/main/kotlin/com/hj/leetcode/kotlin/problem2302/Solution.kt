package com.hj.leetcode.kotlin.problem2302

/**
 * LeetCode page: [2302. Count Subarrays With Score Less Than K](https://leetcode.com/problems/count-subarrays-with-score-less-than-k/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun countSubarrays(
        nums: IntArray,
        k: Long,
    ): Long {
        // We use the sliding window technique to compute the number of valid subarrays
        // for each end index.
        var windowSize = 0
        var windowSum = 0L
        var result = 0L

        for (end in nums.indices) {
            windowSize++
            windowSum += nums[end]

            while (windowSum * windowSize >= k) {
                windowSize--
                windowSum -= nums[end - windowSize]
            }
            result += windowSize
        }
        return result
    }
}
