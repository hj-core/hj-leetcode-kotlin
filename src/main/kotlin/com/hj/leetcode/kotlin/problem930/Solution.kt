package com.hj.leetcode.kotlin.problem930

/**
 * LeetCode page: [930. Binary Subarrays With Sum](https://leetcode.com/problems/binary-subarrays-with-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun numSubarraysWithSum(nums: IntArray, goal: Int): Int {
        var result = 0
        var left = 0
        var currentSum = 0
        var prefixZeros = 0

        for ((right, value) in nums.withIndex()) {
            require(value >= 0)

            currentSum += value
            while (left < right && (nums[left] == 0 || currentSum > goal)) {
                if (nums[left] == 0) {
                    prefixZeros++
                } else {
                    prefixZeros = 0
                }
                currentSum -= nums[left]
                left++
            }

            if (currentSum == goal) {
                result += 1 + prefixZeros
            }
        }
        return result
    }
}