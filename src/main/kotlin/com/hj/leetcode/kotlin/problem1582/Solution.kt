package com.hj.leetcode.kotlin.problem1582

/**
 * LeetCode page: [1582. Special Positions in a Binary Matrix](https://leetcode.com/problems/special-positions-in-a-binary-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(N) where M and N are the number of rows
     * and columns of mat;
     */
    fun numSpecial(mat: Array<IntArray>): Int {
        val memoization = hashMapOf<Int, Boolean>()
        return candidateColumns(mat).count { hasSingleOne(mat, it, memoization) }
    }

    private fun candidateColumns(mat: Array<IntArray>): MutableList<Int> {
        val result = mutableListOf<Int>()
        for (row in mat) {
            val firstOneIndex = row.indexOfFirst { it == 1 }
            if (firstOneIndex == -1) {
                continue
            }

            val lastOneIndex = row.indexOfLast { it == 1 }
            if (firstOneIndex == lastOneIndex) {
                result.add(firstOneIndex)
            }
        }
        return result
    }

    private fun hasSingleOne(
        mat: Array<IntArray>,
        column: Int,
        memoization: MutableMap<Int, Boolean>,
    ): Boolean {

        if (column in memoization) {
            return checkNotNull(memoization[column])
        }

        val firstOneRow = mat.indexOfFirst { it[column] == 1 }
        if (firstOneRow == -1) {
            memoization[column] = false
            return false
        }

        val lastOneRow = mat.indexOfLast { it[column] == 1 }
        val result = firstOneRow == lastOneRow
        memoization[column] = result
        return result
    }
}