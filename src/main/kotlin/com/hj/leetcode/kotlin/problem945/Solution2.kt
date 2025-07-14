package com.hj.leetcode.kotlin.problem945

/**
 * LeetCode page: [945. Minimum Increment to Make Array Unique](https://leetcode.com/problems/minimum-increment-to-make-array-unique/);
 */
class Solution2 {
    // Complexity:
    // Time O(N+M) and Space O(M) where N is the length of nums
    // and M is the maximum value in nums.
    fun minIncrementForUnique(nums: IntArray): Int {
        val maxElem = nums.max()
        val freq = IntArray(maxElem + 1)
        for (num in nums) {
            freq[num]++
        }

        var result = 0L
        for (num in 0..<maxElem) {
            if (freq[num] > 1) {
                val exceed = freq[num] - 1
                result += exceed
                freq[num + 1] += exceed
            }
        }
        result += 1L * (freq[maxElem] - 1) * freq[maxElem] / 2
        return result.toInt()
    }
}
