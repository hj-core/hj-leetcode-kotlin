package com.hj.leetcode.kotlin.problem2924

/**
 * LeetCode page: [2924. Find Champion II](https://leetcode.com/problems/find-champion-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(n+M) and Space O(n) where M is the size of edges.
     */
    fun findChampion(
        n: Int,
        edges: Array<IntArray>,
    ): Int {
        val hasZeroInDegree = BooleanArray(n) { true }
        for ((_, v) in edges) {
            hasZeroInDegree[v] = false
        }

        val candidate = (0..<n).first { hasZeroInDegree[it] }
        return if ((candidate + 1..<n).any { hasZeroInDegree[it] }) {
            -1
        } else {
            candidate
        }
    }
}
