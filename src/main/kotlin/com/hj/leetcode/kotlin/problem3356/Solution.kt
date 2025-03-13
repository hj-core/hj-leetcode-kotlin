package com.hj.leetcode.kotlin.problem3356

/**
 * LeetCode page: [3356. Zero Array Transformation II](https://leetcode.com/problems/zero-array-transformation-ii/);
 */
class Solution {
    // Complexity:
    // Time O((N+M)LogN) and Space O(N)
    // where N and M are the length of nums and queries, respectively.
    fun minZeroArray(
        nums: IntArray,
        queries: Array<IntArray>,
    ): Int {
        val fenwickTree = FenwickTree(nums.size + 1)
        var result = 0

        // Find the shortest prefix of queries for each i such that nums[0..i] can be zeroed
        for ((i, num) in nums.withIndex()) {
            var remaining = num - fenwickTree.query(i)
            while (0 < remaining && result < queries.size) {
                val (left, right, decrement) = queries[result]
                result++

                if (i in left..right) {
                    remaining -= decrement
                }
                fenwickTree.update(left, decrement)
                fenwickTree.update(right + 1, -decrement)
            }
            if (remaining > 0) {
                return -1
            }
        }
        return result
    }

    private class FenwickTree(
        size: Int,
    ) {
        private val tree = IntArray(size + 1) { 0 }

        fun update(
            index: Int,
            increment: Int,
        ) {
            var i = index + 1
            while (i < tree.size) {
                tree[i] += increment
                i += i and -i
            }
        }

        fun query(index: Int): Int {
            var i = index + 1
            var sum = 0
            while (i > 0) {
                sum += tree[i]
                i -= i and -i
            }
            return sum
        }
    }
}
