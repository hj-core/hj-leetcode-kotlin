package com.hj.leetcode.kotlin.problem1074

/**
 * LeetCode page: [1074. Number of Submatrices That Sum to Target](https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/);
 */
class Solution {
    /* Complexity:
     * Time O(M^2 * N) and Space O(MN) where M and N are the number of rows and
     * columns of matrix;
     */
    fun numSubmatrixSumTarget(matrix: Array<IntArray>, target: Int): Int {
        var result = 0
        val prefixSum = matrix.prefixSum()
        for (top in matrix.indices) {
            for (bottom in top..<matrix.size) {
                // Like condensing the rows between top and bottom into a single row
                val countRightSum = hashMapOf<Int, Int>().apply { this[0] = 1 }
                for (right in matrix[top].indices) {
                    val rightSum = (prefixSum[bottom][right] -
                            (prefixSum.getOrNull(top - 1)?.get(right) ?: 0))
                    val complement = rightSum - target
                    if (complement in countRightSum) {
                        result += checkNotNull(countRightSum[complement])
                    }
                    countRightSum[rightSum] = 1 + (countRightSum[rightSum] ?: 0)
                }
            }
        }
        return result
    }

    private fun Array<IntArray>.prefixSum(): Array<IntArray> {
        val result = Array(size) { IntArray(this[it].size) }

        result[0][0] = this[0][0]
        for (column in 1..<this[0].size) {
            result[0][column] = this[0][column] + result[0][column - 1]
        }
        for (row in 1..<this.size) {
            result[row][0] = this[row][0] + result[row - 1][0]
        }
        for (row in 1..<this.size) {
            for (column in 1..<this[row].size) {
                result[row][column] = (this[row][column]
                        + result[row - 1][column]
                        + result[row][column - 1]
                        - result[row - 1][column - 1])
            }
        }
        return result
    }
}