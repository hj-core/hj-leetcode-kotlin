package com.hj.leetcode.kotlin.problem3105

/**
 * LeetCode page: [3105. Longest Strictly Increasing or Strictly Decreasing Subarray](https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of nums.
     */
    fun longestMonotonicSubarray(nums: IntArray): Int {
        if (nums.size < 2) {
            return 1
        }
        var prevCmp = compare(nums[0], nums[1])
        var prevLen = if (prevCmp == 0) 1 else 2
        var result = prevLen

        for (i in 2..<nums.size) {
            val cmp = compare(nums[i - 1], nums[i])
            when {
                cmp == 0 -> prevLen = 1
                prevCmp == cmp -> prevLen++
                else -> prevLen = 2
            }
            prevCmp = cmp
            result = maxOf(result, prevLen)
        }
        return result
    }

    private fun compare(
        x: Int,
        y: Int,
    ): Int =
        when {
            x > y -> 1
            x < y -> -1
            else -> 0
        }
}
