package com.hj.leetcode.kotlin.problem2444

/**
 * LeetCode page: [2444. Count Subarrays With Fixed Bounds](https://leetcode.com/problems/count-subarrays-with-fixed-bounds/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
        var result = 0L
        var lastMinKIndex = -1
        var lastMaxKIndex = -1
        var lastOutOfRangeIndex = -1

        for ((index, num) in nums.withIndex()) {
            val isOutOfRange = num < minK || num > maxK
            if (isOutOfRange) lastOutOfRangeIndex = index

            if (num == minK) lastMinKIndex = index
            if (num == maxK) lastMaxKIndex = index

            val numValidSubArraysEndAtIndex =
                maxOf(0, minOf(lastMinKIndex, lastMaxKIndex) - lastOutOfRangeIndex)
            result += numValidSubArraysEndAtIndex
        }
        return result
    }
}