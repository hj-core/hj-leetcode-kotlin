package com.hj.leetcode.kotlin.problem1572

/**
 * LeetCode page: [1572. Matrix Diagonal Sum](https://leetcode.com/problems/matrix-diagonal-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of rows(columns) of mat;
     */
    fun diagonalSum(mat: Array<IntArray>): Int {
        return sumPrimaryDiagonal(mat) + sumSecondaryDiagonal(mat) - sharedCenterValue(mat)
    }

    private fun sumPrimaryDiagonal(mat: Array<IntArray>): Int {
        var sum = 0
        for (index in mat.indices) {
            sum += mat[index][index]
        }
        return sum
    }

    private fun sumSecondaryDiagonal(mat: Array<IntArray>): Int {
        var sum = 0
        for (index in mat.indices) {
            sum += mat[mat.lastIndex - index][index]
        }
        return sum
    }

    private fun sharedCenterValue(mat: Array<IntArray>): Int {
        val noSharedElement = mat.size.isEven()
        if (noSharedElement) return 0

        val center = mat.size / 2
        return mat[center][center]
    }

    private fun Int.isEven(): Boolean = this and 1 == 0
}