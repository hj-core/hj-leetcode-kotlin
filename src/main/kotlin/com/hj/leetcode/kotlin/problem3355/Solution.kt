package com.hj.leetcode.kotlin.problem3355

/**
 * LeetCode page: [3355. Zero Array Transformation I](https://leetcode.com/problems/zero-array-transformation-i/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(N) where N is the length of nums and queries,
    // respectively.
    fun isZeroArray(
        nums: IntArray,
        queries: Array<IntArray>,
    ): Boolean {
        val delta = IntArray(nums.size + 1)
        for (query in queries) {
            delta[query[0]]++
            delta[query[1] + 1]--
        }

        var reduction = 0
        for ((i, num) in nums.withIndex()) {
            reduction += delta[i]
            if (reduction < num) {
                return false
            }
        }
        return true
    }
}
