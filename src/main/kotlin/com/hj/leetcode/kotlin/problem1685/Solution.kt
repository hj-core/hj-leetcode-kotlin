package com.hj.leetcode.kotlin.problem1685

/**
 * LeetCode page: [1685. Sum of Absolute Differences in a Sorted Array](https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun getSumAbsoluteDifferences(nums: IntArray): IntArray {
        val totalSum = nums.sum()
        var prefixSum = 0
        val result = IntArray(nums.size)
        for ((index, num) in nums.withIndex()) {
            val suffixSum = totalSum - prefixSum
            result[index] =
                (num * index - prefixSum) + (suffixSum - num * (nums.size - index))
            prefixSum += nums[index]
        }
        return result
    }
}