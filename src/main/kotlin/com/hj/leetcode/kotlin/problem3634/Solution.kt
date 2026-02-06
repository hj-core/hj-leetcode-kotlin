package com.hj.leetcode.kotlin.problem3634

/**
 * LeetCode page: [3634. Minimum Removals to Balance Array](https://leetcode.com/problems/minimum-removals-to-balance-array/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums.
    fun minRemoval(
        nums: IntArray,
        k: Int,
    ): Int {
        val nums = nums.sortedArray()

        var left = 0
        for (right in nums.indices) {
            if (nums[left] * k.toLong() < nums[right]) {
                left++
            }
        }

        return left
    }
}
