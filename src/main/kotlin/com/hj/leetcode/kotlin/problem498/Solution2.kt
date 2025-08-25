package com.hj.leetcode.kotlin.problem498

/**
 * LeetCode page: [498. Diagonal Traverse](https://leetcode.com/problems/diagonal-traverse/);
 */
class Solution2 {
    // Complexity:
    // Time O(MN) and Auxiliary Space O(1) where M and N are
    // the number of rows and columns in grid, respectively.
    fun findDiagonalOrder(mat: Array<IntArray>): IntArray {
        val m = mat.size
        val n = mat[0].size
        val result = IntArray(m * n)
        var i = 0 // The index to place the next number
        var r = 0
        var c = 0
        var diagonal = 0
        val directions = arrayOf(intArrayOf(-1, 1), intArrayOf(1, -1))

        while (diagonal < m + n - 1) {
            val direction: IntArray
            val length: Int

            if (diagonal and 1 == 0) {
                direction = directions[0]
                length = minOf(r + 1, n - c)
            } else {
                direction = directions[1]
                length = minOf(m - r, c + 1)
            }

            repeat(length) {
                result[i] = mat[r][c]
                i++
                r += direction[0]
                c += direction[1]
            }

            diagonal++
            if (diagonal and 1 == 0) {
                r = minOf(diagonal, m - 1)
                c = diagonal - r
            } else {
                c = minOf(diagonal, n - 1)
                r = diagonal - c
            }
        }
        return result
    }
}
