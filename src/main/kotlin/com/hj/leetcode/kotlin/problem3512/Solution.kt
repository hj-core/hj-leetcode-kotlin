package com.hj.leetcode.kotlin.problem3512

/**
 * LeetCode page: [3512. Minimum Operations to Make Array Sum Divisible by K](https://leetcode.com/problems/minimum-operations-to-make-array-sum-divisible-by-k/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minOperations(
        nums: IntArray,
        k: Int,
    ): Int = nums.sum() % k
}
