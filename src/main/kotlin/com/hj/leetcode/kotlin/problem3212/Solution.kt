package com.hj.leetcode.kotlin.problem3212

/**
 * LeetCode page: [3212. Count Submatrices With Equal Frequency of X and Y](https://leetcode.com/problems/count-submatrices-with-equal-frequency-of-x-and-y/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(N) where M and N are the number of rows
    // and columns in grid, respectively.
    fun numberOfSubmatrices(grid: Array<CharArray>): Int {
        var count = 0
        // prefixSum[c]@r := the count of 'X' and 'Y' of submatrix
        // from cell (0, 0) to (r, c).
        val prefixSum = Array(grid[0].size) { IntArray(2) }
        for (r in grid.indices) {
            // rowPrefixSum := the count of 'X' and 'Y' in row r up to
            // column c.
            val rowPrefixSum = IntArray(2)
            for ((c, char) in grid[r].withIndex()) {
                when (char) {
                    'X' -> rowPrefixSum[0]++
                    'Y' -> rowPrefixSum[1]++
                }
                prefixSum[c][0] += rowPrefixSum[0]
                prefixSum[c][1] += rowPrefixSum[1]

                if (prefixSum[c][0] > 0 &&
                    prefixSum[c][0] == prefixSum[c][1]
                ) {
                    count++
                }
            }
        }

        return count
    }
}
