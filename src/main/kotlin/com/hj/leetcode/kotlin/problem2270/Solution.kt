package com.hj.leetcode.kotlin.problem2270

/**
 * LeetCode page: [2270. Number of Ways to Split Array](https://leetcode.com/problems/number-of-ways-to-split-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of nums.
     */
    fun waysToSplitArray(nums: IntArray): Int {
        val totalSum = nums.fold(0L) { acc, i -> acc + i }
        var result = 0
        var leftSum = 0L

        for (i in 0..<nums.lastIndex) {
            leftSum += nums[i]
            if (leftSum * 2 >= totalSum) {
                result++
            }
        }
        return result
    }
}
