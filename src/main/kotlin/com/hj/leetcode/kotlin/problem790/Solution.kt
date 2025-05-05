package com.hj.leetcode.kotlin.problem790

/**
 * LeetCode page: [790. Domino and Tromino Tiling](https://leetcode.com/problems/domino-and-tromino-tiling/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(1);
     */
    fun numTilings(n: Int): Int {
        val module = 1_000_000_007 // from the problem requirements;

        // We define three types of 2xn boards as follows:
        //   Type1 - No missing cells.
        //   Type2 - The top cell of the first column is missing.
        //   Type3 - The bottom cell of the first column is missing.
        // Then, we track the number of ways to cover each type of board with the
        // corresponding dp variables, which have initial values corresponding to
        // a 0x1 board.
        var dpType1 = 1L
        var prevDpType1 = 0L
        var dpType2 = 0L
        var dpType3 = 0L

        repeat(n) {
            val newDpType1 = (dpType1 + prevDpType1 + dpType2 + dpType3) % module
            val newDpType2 = (prevDpType1 + dpType3) % module
            val newDpType3 = (prevDpType1 + dpType2) % module

            prevDpType1 = dpType1
            dpType1 = newDpType1
            dpType2 = newDpType2
            dpType3 = newDpType3
        }
        return dpType1.toInt()
    }
}
