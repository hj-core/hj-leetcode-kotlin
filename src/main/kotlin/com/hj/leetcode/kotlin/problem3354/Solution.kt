package com.hj.leetcode.kotlin.problem3354

import kotlin.math.abs

/**
 * LeetCode page: [3354. Make Array Elements Equal to Zero](https://leetcode.com/problems/make-array-elements-equal-to-zero/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun countValidSelections(nums: IntArray): Int {
        var prefixSum = 0
        var suffixSum = nums.sum()
        var result = 0

        for (num in nums) {
            if (num != 0) {
                prefixSum += num
                suffixSum -= num
                continue
            }

            val diff = abs(prefixSum - suffixSum)
            if (diff < 2) {
                result += 2 - diff
            }
        }
        return result
    }
}
