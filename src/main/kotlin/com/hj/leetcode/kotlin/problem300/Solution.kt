package com.hj.leetcode.kotlin.problem300

/**
 * LeetCode page: [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun lengthOfLIS(nums: IntArray): Int {
        /* minTails[i]::= the minimum tail among the strictly increasing
         * subsequences with a length of i+1;
         */
        val minTails = mutableListOf<Int>()
        for (num in nums) {
            val insertionIndex = minTails
                .binarySearch(num)
                .let { if (it < 0) -(it + 1) else it }

            if (insertionIndex == minTails.size) {
                minTails.add(num)
            } else {
                minTails[insertionIndex] = num
            }
        }
        return minTails.size
    }
}