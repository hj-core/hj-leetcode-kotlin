package com.hj.leetcode.kotlin.problem2285

/**
 * LeetCode page: [2285. Maximum Total Importance of Roads](https://leetcode.com/problems/maximum-total-importance-of-roads/);
 */
class Solution {
    /* Complexity:
     * Time O(nLog(n)+E) and Space O(n) where E is the size of roads;
     */
    fun maximumImportance(n: Int, roads: Array<IntArray>): Long {
        val sortedDegrees = nodeDegrees(n, roads).apply {
            sort()
        }
        return sortedDegrees.foldIndexed(0L) { index, acc, degree ->
            val value = index + 1L
            acc + degree * value
        }
    }

    private fun nodeDegrees(n: Int, roads: Array<IntArray>): IntArray {
        val result = IntArray(n)
        for ((u, v) in roads) {
            result[u]++
            result[v]++
        }
        return result
    }
}