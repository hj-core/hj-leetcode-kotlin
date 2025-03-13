package com.hj.leetcode.kotlin.problem3356

/**
 * LeetCode page: [3356. Zero Array Transformation II](https://leetcode.com/problems/zero-array-transformation-ii/);
 */
class Solution2 {
    // Complexity:
    // Time O(N+M) and Space O(N)
    // where N and M are the length of nums and queries, respectively.
    fun minZeroArray(
        nums: IntArray,
        queries: Array<IntArray>,
    ): Int {
        val lineSweep = IntArray(nums.size + 1) { 0 }
        var result = 0

        // Find the shortest prefix of queries for each i such that nums[0..i] can be zeroed
        for ((i, num) in nums.withIndex()) {
            while (lineSweep[i] < num && result < queries.size) {
                val (left, right, decrement) = queries[result]
                result++

                if (right < i) {
                    continue
                }
                lineSweep[maxOf(left, i)] += decrement
                lineSweep[right + 1] -= decrement
            }

            if (lineSweep[i] < num) {
                return -1
            }
            lineSweep[i + 1] += lineSweep[i]
        }
        return result
    }
}
