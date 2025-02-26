package com.hj.leetcode.kotlin.problem1749

/**
 * LeetCode page: [1749. Maximum Absolute Sum of Any Subarray](https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of `num`.
    fun maxAbsoluteSum(nums: IntArray): Int {
        var maxPrefixSum = 0
        var minPrefixSum = 0
        var prefixSum = 0
        for (num in nums) {
            prefixSum += num
            maxPrefixSum = maxOf(maxPrefixSum, prefixSum)
            minPrefixSum = minOf(minPrefixSum, prefixSum)
        }
        return maxPrefixSum - minPrefixSum
    }
}
