package com.hj.leetcode.kotlin.problem498

/**
 * LeetCode page: [498. Diagonal Traverse](https://leetcode.com/problems/diagonal-traverse/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Auxiliary Space O(1) where M and N are
    // the number of rows and columns in grid, respectively.
    fun findDiagonalOrder(mat: Array<IntArray>): IntArray {
        val m = mat.size
        val n = mat[0].size
        val result = IntArray(m * n)
        var r = 0
        var c = 0
        var diagonal = 0
        val directions = arrayOf(intArrayOf(-1, 1), intArrayOf(1, -1))

        for (i in result.indices) {
            result[i] = mat[r][c]
            val direction = directions[diagonal and 1]
            r += direction[0]
            c += direction[1]

            if (r < 0 || c == n) {
                diagonal++
                c = minOf(diagonal, n - 1)
                r = diagonal - c
            } else if (r == m || c < 0) {
                diagonal++
                r = minOf(diagonal, m - 1)
                c = diagonal - r
            }
        }
        return result
    }
}
