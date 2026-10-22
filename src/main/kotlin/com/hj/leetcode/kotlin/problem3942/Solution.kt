package com.hj.leetcode.kotlin.problem3942

/**
 * LeetCode page: [3942. Minimum Operations to Sort a Permutation](https://leetcode.com/problems/minimum-operations-to-sort-a-permutation/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minOperations(nums: IntArray): Int {
        val ascPivot = findPivot(nums, false)
        if (ascPivot != -1) {
            return minOf(ascPivot, 2 + nums.size - ascPivot)
        }

        val descPivot = findPivot(nums, true)
        if (descPivot != -1) {
            return minOf(2 + descPivot, nums.size - descPivot)
        }

        return -1
    }

    private fun findPivot(
        nums: IntArray,
        backward: Boolean,
    ): Int {
        val indices = if (backward) nums.indices.reversed() else nums.indices
        var pivot = if (backward) nums.size - 1 else 0
        var prev = nums[pivot]
        var turned = false
        for (i in indices) {
            if (nums[i] < prev) {
                if (turned || nums[indices.last] > nums[indices.first]) {
                    return -1
                }
                turned = true
                pivot = i
            }
            prev = nums[i]
        }
        return pivot
    }
}
