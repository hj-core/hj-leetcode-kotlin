package com.hj.leetcode.kotlin.problem1877

/**
 * LeetCode page: [1877. Minimize Maximum Pair Sum in Array](https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums.
    fun minPairSum(nums: IntArray): Int {
        val nums = nums.sortedArray()
        val n = nums.size
        return (0..<n / 2).maxOf { nums[it] + nums[n - 1 - it] }
    }
}
