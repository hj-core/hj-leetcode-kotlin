package com.hj.leetcode.kotlin.problem417

/**
 * LeetCode page: [417. Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(MN) where M and N are the number
    // of rows and columns in heights, respectively.
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        val isPacific = findPacificCells(heights)
        val isAtlantic = findAtlanticCells(heights)

        val result = mutableListOf<List<Int>>()
        for (r in isPacific.indices) {
            for (c in isPacific[r].indices) {
                if (isPacific[r][c] && isAtlantic[r][c]) {
                    result.add(listOf(r, c))
                }
            }
        }
        return result
    }

    private fun findPacificCells(heights: Array<IntArray>): Array<BooleanArray> {
        val m = heights.size
        val n = heights[0].size
        val isReachable = Array(m) { BooleanArray(n) }

        for (c in 0..<n) {
            dfs(0, c, -1, heights, isReachable)
        }
        for (r in 0..<m) {
            dfs(r, 0, -1, heights, isReachable)
        }
        return isReachable
    }

    private fun dfs(
        r: Int,
        c: Int,
        parentHeight: Int,
        heights: Array<IntArray>,
        isReachable: Array<BooleanArray>,
    ) {
        if (r !in heights.indices || c !in heights[r].indices) {
            return
        }
        if (heights[r][c] < parentHeight || isReachable[r][c]) {
            return
        }

        isReachable[r][c] = true
        dfs(r, c - 1, heights[r][c], heights, isReachable)
        dfs(r, c + 1, heights[r][c], heights, isReachable)
        dfs(r - 1, c, heights[r][c], heights, isReachable)
        dfs(r + 1, c, heights[r][c], heights, isReachable)
    }

    private fun findAtlanticCells(heights: Array<IntArray>): Array<BooleanArray> {
        val m = heights.size
        val n = heights[0].size
        val isReachable = Array(m) { BooleanArray(n) }

        for (c in 0..<n) {
            dfs(m - 1, c, -1, heights, isReachable)
        }
        for (r in 0..<m) {
            dfs(r, n - 1, -1, heights, isReachable)
        }
        return isReachable
    }
}
