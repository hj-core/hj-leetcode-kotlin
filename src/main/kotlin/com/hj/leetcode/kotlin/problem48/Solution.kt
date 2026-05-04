package com.hj.leetcode.kotlin.problem48

/**
 * LeetCode page: [48. Rotate Image](https://leetcode.com/problems/rotate-image/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(1) where N is the rows and columns of matrix.
    fun rotate(matrix: Array<IntArray>) {
        val n = matrix.size
        for (r in 0..<n / 2) {
            val mirrorR = n - 1 - r
            for (c in r..<mirrorR) {
                val mirrorC = n - 1 - c
                var tmp = matrix[r][c]
                matrix[c][mirrorR] = tmp.also { tmp = matrix[c][mirrorR] }
                matrix[mirrorR][mirrorC] = tmp.also { tmp = matrix[mirrorR][mirrorC] }
                matrix[mirrorC][r] = tmp.also { tmp = matrix[mirrorC][r] }
                matrix[r][c] = tmp
            }
        }
    }
}
