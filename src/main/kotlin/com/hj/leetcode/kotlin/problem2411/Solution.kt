package com.hj.leetcode.kotlin.problem2411

/**
 * LeetCode page: [2411. Smallest Subarrays With Maximum Bitwise OR](https://leetcode.com/problems/smallest-subarrays-with-maximum-bitwise-or/);
 */
class Solution {
    // Complexity:
    // Time O(NM) and Space O(N+M) where N is the length
    // of nums and M is the minimum number of bits to represent
    // the maximum value in nums.
    fun smallestSubarrays(nums: IntArray): IntArray {
        // bitCounts[shift]:= the number of numbers in the current
        // window that have the bit at position shift set.
        val bitCounts = IntArray(minBitsToRepresentMax(nums))
        val result = IntArray(nums.size)

        var minEnd = nums.size - 1 // Inclusive
        for (start in nums.indices.reversed()) {
            orNumber(bitCounts, nums[start])
            while (start < minEnd && stillMaxAfterUndoOr(bitCounts, nums[minEnd])) {
                undoOrNumber(bitCounts, nums[minEnd])
                minEnd--
            }
            result[start] = minEnd - start + 1
        }
        return result
    }

    // Returns the minimum number of bits to represent the
    // maximum value in nums.
    private fun minBitsToRepresentMax(nums: IntArray): Int {
        var maxValue = nums.max()
        if (maxValue == 0) {
            return 1
        }

        var result = 0
        while (maxValue > 0) {
            result++
            maxValue = maxValue shr 1
        }
        return result
    }

    private fun orNumber(
        bitCounts: IntArray,
        num: Int,
    ) {
        for (shift in bitCounts.indices) {
            if ((num shr shift) and 1 == 1) {
                bitCounts[shift]++
            }
        }
    }

    private fun stillMaxAfterUndoOr(
        bitCounts: IntArray,
        num: Int,
    ): Boolean {
        for (shift in bitCounts.indices) {
            if ((num shr shift) and 1 == 1 && bitCounts[shift] < 2) {
                return false
            }
        }
        return true
    }

    private fun undoOrNumber(
        bitCounts: IntArray,
        num: Int,
    ) {
        for (shift in bitCounts.indices) {
            if ((num shr shift) and 1 == 1) {
                bitCounts[shift]--
            }
        }
    }
}
