package com.hj.leetcode.kotlin.problem1337

/**
 * LeetCode page: [1337. The K Weakest Rows in a Matrix](https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(MN+MLogM) and Space O(M) where MM and N are the number of rows
     * and columns of mat;
     */
    fun kWeakestRows(mat: Array<IntArray>, k: Int): IntArray {
        val sortedRowStrength = sortedRowStrength(mat)
        return IntArray(k) { sortedRowStrength[it].index }
    }

    private fun sortedRowStrength(mat: Array<IntArray>): List<RowStrength> {
        val result = mat.mapIndexedTo(mutableListOf()) { index, row ->
            RowStrength(index, numSoldiers(row))
        }
        result.sortWith(compareBy({ it.numSoldiers }, { it.index }))
        return result
    }

    private data class RowStrength(val index: Int, val numSoldiers: Int)

    private fun numSoldiers(row: IntArray): Int {
        return row
            .indexOf(0)
            .let { if (it == -1) row.size else it }
    }
}