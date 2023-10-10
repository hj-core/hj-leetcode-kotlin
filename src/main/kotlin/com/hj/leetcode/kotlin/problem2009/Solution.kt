package com.hj.leetcode.kotlin.problem2009

/**
 * LeetCode page: [2009. Minimum Number of Operations to Make Array Continuous](https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun minOperations(nums: IntArray): Int {
        val threshold = nums.size - 1
        val sortedDistinctNums = nums.toHashSet().sorted()
        var result = nums.size
        var left = 0
        var right = 0

        while (right < sortedDistinctNums.size) {
            while (right < sortedDistinctNums.size &&
                sortedDistinctNums[right] - sortedDistinctNums[left] <= threshold
            ) {
                right++
            }

            result = minOf(result, nums.size - right + left)
            left++
        }
        return result
    }
}