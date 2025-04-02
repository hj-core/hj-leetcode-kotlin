package com.hj.leetcode.kotlin.problem2873

/**
 * LeetCode page: [2873. Maximum Value of an Ordered Triplet I](https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun maximumTripletValue(nums: IntArray): Long {
        val suffixMaxes = IntArray(nums.size + 1)
        for (k in nums.indices.reversed()) {
            suffixMaxes[k] = maxOf(nums[k], suffixMaxes[k + 1])
        }

        var prefixMax = nums[0].toLong()
        var result = 0L

        for (j in 1..<nums.size) {
            result = maxOf(result, (prefixMax - nums[j]) * suffixMaxes[j + 1])
            prefixMax = maxOf(prefixMax, nums[j].toLong())
        }
        return result
    }
}
