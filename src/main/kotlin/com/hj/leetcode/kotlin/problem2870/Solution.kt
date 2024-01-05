package com.hj.leetcode.kotlin.problem2870

/**
 * LeetCode page: [2870. Minimum Number of Operations to Make Array Empty](https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun minOperations(nums: IntArray): Int {
        val counts = nums
            .asIterable()
            .groupingBy { it }
            .eachCount()
            .values

        var result = 0
        for (count in counts) {
            if (count < 2) {
                return -1
            }

            result += when (count % 3) {
                0 -> count / 3
                1, 2 -> count / 3 + 1
                else -> throw NoWhenBranchMatchedException()
            }
        }
        return result
    }
}