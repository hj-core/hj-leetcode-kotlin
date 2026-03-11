package com.hj.leetcode.kotlin.problem1582

/**
 * LeetCode page: [1582. Special Positions in a Binary Matrix](https://leetcode.com/problems/special-positions-in-a-binary-matrix/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(M+N) where M and N are the number of rows
    // and columns of mat, respectively.
    fun numSpecial(mat: Array<IntArray>): Int {
        val m = mat.size
        val n = mat[0].size
        val rowOnes = Array(m) { intArrayOf(0, 0) }
        val colOnes = IntArray(n)

        for ((r, row) in mat.withIndex()) {
            for ((c, num) in row.withIndex()) {
                if (num == 1) {
                    rowOnes[r][0]++
                    rowOnes[r][1] = c
                    colOnes[c]++
                }
            }
        }

        return rowOnes.count { (cnt, lastOne) ->
            cnt == 1 && colOnes[lastOne] == 1
        }
    }
}
