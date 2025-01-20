package com.hj.leetcode.kotlin.problem2661

/**
 * LeetCode page: [2661. First Completely Painted Row or Column](https://leetcode.com/problems/first-completely-painted-row-or-column/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N)
     * where N is the size of arr and the total number of elements in mat.
     */
    fun firstCompleteIndex(
        arr: IntArray,
        mat: Array<IntArray>,
    ): Int {
        val cellsByVal = hashMapOf<Int, Pair<Int, Int>>()
        for (row in mat.indices) {
            for (col in mat[row].indices) {
                cellsByVal[mat[row][col]] = Pair(row, col)
            }
        }

        val rowNoColorCnt = IntArray(mat.size) { mat[it].size }
        val colNoColorCnt = IntArray(mat[0].size) { mat.size }

        for ((i, num) in arr.withIndex()) {
            val (row, col) = checkNotNull(cellsByVal[num])
            rowNoColorCnt[row]--
            colNoColorCnt[col]--
            if (rowNoColorCnt[row] == 0 || colNoColorCnt[col] == 0) {
                return i
            }
        }
        throw IllegalStateException("Unreachable")
    }
}
