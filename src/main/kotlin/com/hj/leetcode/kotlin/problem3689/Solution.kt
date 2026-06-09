package com.hj.leetcode.kotlin.problem3689

/**
 * LeetCode page: [3689. Maximum Total Subarray Value I](https://leetcode.com/problems/maximum-total-subarray-value-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun maxTotalValue(
        nums: IntArray,
        k: Int,
    ): Long = (nums.max() - nums.min()) * k.toLong()
}
