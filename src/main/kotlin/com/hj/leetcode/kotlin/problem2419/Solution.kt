package com.hj.leetcode.kotlin.problem2419

import kotlin.math.max

/**
 * LeetCode page: [2419. Longest Subarray With Maximum Bitwise AND](https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and/);
 */
class Solution {
 /* Complexity:
  * Time O(N) and Space O(1) where N is the size of nums.
  */
    fun longestSubarray(nums: IntArray): Int {
        var result = 0
        var maxElement = nums[0]
        var streak = 0

        for (num in nums) {
            when {
                num < maxElement -> {
                    streak = 0
                }

                num > maxElement -> {
                    result = 1
                    streak = 1
                    maxElement = num
                }

                else -> {
                    streak += 1
                    result = max(result, streak)
                }
            }
        }
        return result
    }
}
