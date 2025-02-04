package com.hj.leetcode.kotlin.problem1800

/**
 * LeetCode page: [1800. Maximum Ascending Subarray Sum](https://leetcode.com/problems/maximum-ascending-subarray-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of `nums`.
     */
    fun maxAscendingSum(nums: IntArray): Int {
        var result = nums[0]
        var sum = nums[0]

        for (i in 1..<nums.size) {
            if (nums[i - 1] < nums[i]) {
                sum += nums[i]
            } else {
                result = maxOf(result, sum)
                sum = nums[i]
            }
        }
        result = maxOf(result, sum)
        return result
    }
}
