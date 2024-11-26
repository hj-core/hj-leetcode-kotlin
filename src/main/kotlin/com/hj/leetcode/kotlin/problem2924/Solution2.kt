package com.hj.leetcode.kotlin.problem2924

/**
 * LeetCode page: [2924. Find Champion II](https://leetcode.com/problems/find-champion-ii/);
 */
class Solution2 {
    /* Complexity:
     * Time O(n+M) and Space O(n) where M is the length of edges.
     */
    fun findChampion(
        n: Int,
        edges: Array<IntArray>,
    ): Int {
        var sumCandidates = n * (n - 1) / 2
        var cntCandidates = n
        val hasZeroInDegree = BooleanArray(n) { true }

        for ((_, v) in edges) {
            if (hasZeroInDegree[v]) {
                sumCandidates -= v
                cntCandidates -= 1
                hasZeroInDegree[v] = false
            }
        }
        return if (cntCandidates == 1) sumCandidates else -1
    }
}
