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
        var result = 0
        val cache = hashMapOf<Int, Boolean>()
        for (column in candidateColumns(mat)) {
            if (cache[column] == true) {
                result += 1
                continue
            }

            cache[column] =
                mat.indexOfFirst { it[column] == 1 } == mat.indexOfLast { it[column] == 1 }
            if (cache[column] == true) {
                result += 1
            }
        }
        return result
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
}