package com.hj.leetcode.kotlin.problem2615

/**
 * LeetCode page: [2615. Sum of Distances](https://leetcode.com/problems/sum-of-distances/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun distance(nums: IntArray): LongArray {
        val groups = nums.indices.groupBy(nums::get)

        val result = LongArray(nums.size)
        for ((_, indices) in groups) {
            var suffixSum = indices.fold(0L) { acc, index -> acc + index }
            var prefixSum = 0L
            for ((i, index) in indices.withIndex()) {
                suffixSum -= index
                result[index] =
                    index.toLong() * i - prefixSum + suffixSum -
                    index.toLong() * (indices.size - i - 1)
                prefixSum += index
            }
        }
        return result
    }
}
