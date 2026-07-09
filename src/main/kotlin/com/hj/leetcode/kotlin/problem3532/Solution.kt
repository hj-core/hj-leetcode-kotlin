package com.hj.leetcode.kotlin.problem3532

/**
 * LeetCode page: [3532. Path Existence Queries in a Graph I](https://leetcode.com/problems/path-existence-queries-in-a-graph-i/);
 */
class Solution {
    // Complexity:
    // Time O(n+M) and Space O(n) where M is the length of queries.
    fun pathExistenceQueries(
        n: Int,
        nums: IntArray,
        maxDiff: Int,
        queries: Array<IntArray>,
    ): BooleanArray {
        // maxReachable[i]:= the maximum node reachable from i
        val maxReachable = IntArray(n) { it }
        var j = n - 1
        for (i in n - 2 downTo 0) {
            if (nums[i + 1] - nums[i] > maxDiff) {
                j = i
            }
            maxReachable[i] = j
        }

        return BooleanArray(queries.size) {
            val (u, v) = queries[it]
            maxReachable[u] == maxReachable[v]
        }
    }
}
