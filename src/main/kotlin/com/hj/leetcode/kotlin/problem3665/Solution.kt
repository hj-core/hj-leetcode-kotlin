package com.hj.leetcode.kotlin.problem3665

/**
 * LeetCode page: [3665. Twisted Mirror Path Count](https://leetcode.com/problems/twisted-mirror-path-count/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(N) where M and N are the number of rows and
    // columns of grid, respectively.
    fun uniquePaths(grid: Array<IntArray>): Int {
        val modulo = 1_000_000_007
        val m = grid.size
        val n = grid[0].size
        // topPaths[j]@i:= the number of ways to arrive cell(i, j) from the above
        val topPaths = IntArray(n)
        topPaths[0] = 1
        for (i in 0..<m) {
            var leftPaths = 0
            for (j in 0..<n) {
                if (grid[i][j] == 0) {
                    leftPaths = (leftPaths + topPaths[j]) % modulo
                    topPaths[j] = leftPaths
                } else {
                    leftPaths = topPaths[j].also { topPaths[j] = leftPaths }
                }
            }
        }
        return topPaths[n - 1]
    }
}
