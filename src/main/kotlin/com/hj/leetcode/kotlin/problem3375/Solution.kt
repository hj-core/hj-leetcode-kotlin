package com.hj.leetcode.kotlin.problem3375

/**
 * LeetCode page: [3375. Minimum Operations to Make Array Values Equal to K](https://leetcode.com/problems/minimum-operations-to-make-array-values-equal-to-k/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun minOperations(
        nums: IntArray,
        k: Int,
    ): Int {
        val greaterThanK = mutableSetOf<Int>()

        for (num in nums) {
            if (num < k) {
                return -1
            }

            if (k < num) {
                greaterThanK.add(num)
            }
        }
        return greaterThanK.size
    }
}
