package com.hj.leetcode.kotlin.problem1848

/**
 * LeetCode page: [1848. Minimum Distance to the Target Element](https://leetcode.com/problems/minimum-distance-to-the-target-element/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun getMinDistance(
        nums: IntArray,
        target: Int,
        start: Int,
    ): Int {
        val left = (start downTo 0).find { nums[it] == target } ?: -nums.size
        val right = (start..<nums.size).find { nums[it] == target } ?: (nums.size * 2)
        return minOf(start - left, right - start)
    }
}
