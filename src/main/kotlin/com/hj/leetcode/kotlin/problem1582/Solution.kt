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
        val candidateColumns = mutableListOf<Int>()
        for (row in mat) {
            val leftMostIndex = row.indexOfFirst { it == 1 }
            if (leftMostIndex == -1) {
                continue
            }

            val rightMostIndex = row.indexOfLast { it == 1 }
            if (leftMostIndex == rightMostIndex) {
                candidateColumns.add(leftMostIndex)
            }
        }

        var result = 0
        val cache = hashMapOf<Int, Boolean>()
        for (column in candidateColumns) {
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
}