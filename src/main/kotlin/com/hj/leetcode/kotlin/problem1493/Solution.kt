package com.hj.leetcode.kotlin.problem1493

/**
 * LeetCode page: [1493. Longest Subarray of 1's After Deleting One Element](https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun longestSubarray(nums: IntArray): Int {
        var result = 0
        var start = -1
        var indexOfZero = -1

        for (end in -1 until nums.size) {
            val nextIndex = end + 1
            if (nextIndex == nums.size || nums[nextIndex] == 0) {
                result = maxOf(result, end - start)
                start = indexOfZero + 1
                indexOfZero = nextIndex
            }
        }

        val isAllOnes = result == nums.size
        if (isAllOnes) {
            result--
        }
        return result
    }
}