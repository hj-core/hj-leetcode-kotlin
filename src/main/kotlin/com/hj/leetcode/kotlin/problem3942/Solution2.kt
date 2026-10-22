package com.hj.leetcode.kotlin.problem3942

/**
 * LeetCode page: [3942. Minimum Operations to Sort a Permutation](https://leetcode.com/problems/minimum-operations-to-sort-a-permutation/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minOperations(nums: IntArray): Int {
        var downs = if (nums[0] < nums.last()) 1 else 0
        var zero = 0
        for (i in 1..<nums.size) {
            if (nums[i] < nums[i - 1]) {
                downs++
            }
            if (nums[i] == 0) {
                zero = i
            }
        }

        return when (downs) {
            0 -> 0
            1 -> minOf(zero, nums.size - zero + 2)
            nums.size - 1 -> minOf(zero + 2, nums.size - zero)
            else -> -1
        }
    }
}
