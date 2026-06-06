package com.hj.leetcode.kotlin.problem2574

import kotlin.math.abs

/**
 * LeetCode page: [2574. Left and Right Sum Differences](https://leetcode.com/problems/left-and-right-sum-differences/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun leftRightDifference(nums: IntArray): IntArray {
        val diffArray = IntArray(nums.size)
        var leftSum = 0
        var rightSum = nums.sum()
        for (i in nums.indices) {
            rightSum -= nums[i]
            diffArray[i] = abs(leftSum - rightSum)
            leftSum += nums[i]
        }

        return diffArray
    }
}
