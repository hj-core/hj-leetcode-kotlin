package com.hj.leetcode.kotlin.problem3963

/**
 * LeetCode page: [3963. Create Grid With Exactly One Path](https://leetcode.com/problems/create-grid-with-exactly-one-path/);
 */
class Solution {
    // Complexity:
    // Time O(m+n) and Auxiliary Space O(n).
    fun createGrid(
        m: Int,
        n: Int,
    ): Array<String> {
        val a = ".".repeat(n)
        val b = "#".repeat(n - 1) + "."
        val grid = Array(m) { b }
        grid[0] = a
        return grid
    }
}
