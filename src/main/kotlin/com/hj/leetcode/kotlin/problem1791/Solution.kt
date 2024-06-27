package com.hj.leetcode.kotlin.problem1791

/**
 * LeetCode page: [1791. Find Center of Star Graph](https://leetcode.com/problems/find-center-of-star-graph/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun findCenter(edges: Array<IntArray>): Int {
        return edges[0].first { it in edges[1] }
    }
}