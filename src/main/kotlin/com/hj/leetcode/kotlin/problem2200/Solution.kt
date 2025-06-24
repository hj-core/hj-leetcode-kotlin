package com.hj.leetcode.kotlin.problem2200

/**
 * LeetCode page: [2200. Find All K-Distant Indices in an Array](https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Auxiliary Space O(1) where N is the length of nums.
    fun findKDistantIndices(
        nums: IntArray,
        key: Int,
        k: Int,
    ): List<Int> {
        val result = mutableListOf<Int>()
        var right = 0 // The first index not covered by the previous key

        for ((i, num) in nums.withIndex()) {
            if (num != key) {
                continue
            }

            val left = maxOf(i - k, right)
            right = minOf(i + k + 1, nums.size)

            for (j in left..<right) {
                result.add(j)
            }
        }
        return result
    }
}
