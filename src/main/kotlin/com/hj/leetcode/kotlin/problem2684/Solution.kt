package com.hj.leetcode.kotlin.problem2684

/**
 * LeetCode page: [2684. Maximum Number of Moves in a Grid](https://leetcode.com/problems/maximum-number-of-moves-in-a-grid/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(M) where M is the number of rows in grid
     * and N is the number of columns in grid.
     */
    fun maxMoves(grid: Array<IntArray>): Int {
        // BFS
        var reachableRows = grid.indices.toSet() // Reachable rows at first column
        for (col in 0..<grid[0].lastIndex) {
            val nextReachable = mutableSetOf<Int>()
            for (row in reachableRows) {
                for (rowShift in -1..1) {
                    val nextRow = row + rowShift
                    if (nextRow in grid.indices && grid[row][col] < grid[nextRow][col + 1]) {
                        nextReachable.add(nextRow)
                    }
                }
            }
            if (nextReachable.isEmpty()) {
                return col
            }
            reachableRows = nextReachable
        }
        return grid[0].lastIndex
    }
}
