package com.hj.leetcode.kotlin.problem930

/**
 * LeetCode page: [930. Binary Subarrays With Sum](https://leetcode.com/problems/binary-subarrays-with-sum/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun numSubarraysWithSum(nums: IntArray, goal: Int): Int {
        require(nums.all { it >= 0 })
        return (numSubArraysWithSumAtMost(nums, goal)
                - numSubArraysWithSumAtMost(nums, goal - 1))
    }

    private fun numSubArraysWithSumAtMost(naturalNums: IntArray, goal: Int): Int {
        var result = 0
        var currentSum = 0
        var left = 0

        for ((right, value) in naturalNums.withIndex()) {
            currentSum += value
            while (left <= right && currentSum > goal) {
                currentSum -= naturalNums[left]
                left++
            }
            result += right - left + 1
        }
        return result
    }
}