package com.hj.leetcode.kotlin.problem2411

/**
 * LeetCode page: [2411. Smallest Subarrays With Maximum Bitwise OR](https://leetcode.com/problems/smallest-subarrays-with-maximum-bitwise-or/);
 */
class Solution2 {
    // Complexity:
    // Time O(NM) and Space O(N+M) where N is the length
    // of nums and M is the minimum number of bits to represent
    // the maximum value in nums.
    fun smallestSubarrays(nums: IntArray): IntArray {
        // minIndices[shift] := the minimum index so far whose
        // number has that bit set.
        val minIndices = IntArray(minBitsToRepresentMax(nums))
        val result = IntArray(nums.size)
        for (start in nums.indices.reversed()) {
            var minEnd = start // Inclusive
            for (shift in minIndices.indices) {
                if ((nums[start] shr shift) and 1 == 1) {
                    minIndices[shift] = start
                } else {
                    minEnd = maxOf(minEnd, minIndices[shift])
                }
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
}
