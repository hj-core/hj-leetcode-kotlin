package com.hj.leetcode.kotlin.problem1695

/**
 * LeetCode page: [1695. Maximum Erasure Value](https://leetcode.com/problems/maximum-erasure-value/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun maximumUniqueSubarray(nums: IntArray): Int {
        var result = nums[0]
        var left = 0
        var windowSum = 0
        val inWindow = mutableSetOf<Int>()
        for (right in nums.indices) {
            while (nums[right] in inWindow) {
                inWindow.remove(nums[left])
                windowSum -= nums[left]
                left++
            }
            inWindow.add(nums[right])
            windowSum += nums[right]
            result = maxOf(result, windowSum)
        }
        return result
    }
}
