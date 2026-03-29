package com.hj.leetcode.kotlin.problem2946

/**
 * LeetCode page: [2946. Matrix Similarity After Cyclic Shifts](https://leetcode.com/problems/matrix-similarity-after-cyclic-shifts/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(1) where M and N are the number of rows and
    // columns in mat, respectively.
    fun areSimilar(
        mat: Array<IntArray>,
        k: Int,
    ): Boolean {
        val n = mat[0].size
        val effK = k % n
        if (effK == 0) {
            return true
        }

        for ((r, row) in mat.withIndex()) {
            val shift = if (r and 1 == 0) -effK else effK
            for (c in mat[r].indices) {
                if (row[c] != row[(c + shift).mod(n)]) {
                    return false
                }
            }
        }

        return true
    }
}
