package com.hj.leetcode.kotlin.problem673

/**
 * LeetCode page: [673. Number of Longest Increasing Subsequence](https://leetcode.com/problems/number-of-longest-increasing-subsequence/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the size of nums;
     */
    fun findNumberOfLIS(nums: IntArray): Int {
        /* lengths[i] and numSequences[i] is the longest subsequence length and
         * the number of subsequences with this length;
         */
        val lengths = IntArray(nums.size) { 1 }
        val numSequences = IntArray(nums.size) { 1 }

        for (end in 1 until nums.size) {
            for (oldEnd in 0 until end) {
                val cannotExtend = nums[end] <= nums[oldEnd]
                if (cannotExtend) {
                    continue
                }

                val extendedLength = lengths[oldEnd] + 1
                when {
                    lengths[end] == extendedLength -> {
                        numSequences[end] += numSequences[oldEnd]
                    }

                    lengths[end] < extendedLength -> {
                        lengths[end] = extendedLength
                        numSequences[end] = numSequences[oldEnd]
                    }
                }
            }
        }

        val maxLength = lengths.max()!!
        var result = 0
        for ((end, length) in lengths.withIndex()) {
            if (length == maxLength) {
                result += numSequences[end]
            }
        }
        return result
    }
}