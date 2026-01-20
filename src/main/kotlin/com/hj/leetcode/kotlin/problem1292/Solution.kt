package com.hj.leetcode.kotlin.problem1292

/**
 * LeetCode page: [1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold](https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(MN) where M and N are the number of rows
    // and columns of mat, respectively.
    fun maxSideLength(
        mat: Array<IntArray>,
        threshold: Int,
    ): Int {
        val m = mat.size
        val n = mat[0].size
        val suffixSum2d = suffixSum2d(mat)

        var nextSize = 1
        for (r in mat.indices.reversed()) {
            for (c in n - nextSize downTo 0) {
                if (subMatSum(
                        suffixSum2d,
                        r,
                        c,
                        nextSize,
                    ) <= threshold
                ) {
                    nextSize++
                    break
                }
            }
        }

        return nextSize - 1
    }

    // Returns a 2D array where each entry (r, c) is the sum of
    // mat[r..][c..].
    private fun suffixSum2d(mat: Array<IntArray>): Array<IntArray> {
        val suffixSum =
            Array(mat.size + 1) { IntArray(mat[0].size + 1) }
        for (r in mat.indices.reversed()) {
            for (c in mat[r].indices.reversed()) {
                suffixSum[r][c] =
                    mat[r][c] +
                    suffixSum[r + 1][c] +
                    suffixSum[r][c + 1] -
                    suffixSum[r + 1][c + 1]
            }
        }

        return suffixSum
    }

    // Returns the sum of mat[r..<r+size][c..<c+size].
    private fun subMatSum(
        suffixSum2d: Array<IntArray>,
        r: Int,
        c: Int,
        size: Int,
    ): Int =
        suffixSum2d[r][c] -
            suffixSum2d[r + size][c] -
            suffixSum2d[r][c + size] +
            suffixSum2d[r + size][c + size]
}
