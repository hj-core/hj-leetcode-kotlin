package com.hj.leetcode.kotlin.problem2958

import kotlin.math.max

/**
 * LeetCode page: [2958. Length of Longest Subarray With at Most K Frequency](https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun maxSubarrayLength(
        nums: IntArray,
        k: Int,
    ): Int {
        val freq = hashMapOf<Int, Int>()
        var left = 0
        var breachCount = 0

        for (right in nums.indices) {
            freq.compute(nums[right]) { _, v -> (v ?: 0) + 1 }?.let {
                if (it == k + 1) {
                    breachCount++
                }
            }

            if (breachCount > 0) {
                freq.compute(nums[left]) { _, v -> (v ?: 0) - 1 }?.let {
                    if (it == k) {
                        breachCount--
                    }
                }
                left++
            }
        }

        return nums.size - left
    }
}
