package com.hj.leetcode.kotlin.problem1605

/**
 * LeetCode page: [1605. Find Valid Matrix Given Row and Column Sums](https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M is the size of rowSum
     * and N is the size of colSum;
     */
    fun restoreMatrix(rowSum: IntArray, colSum: IntArray): Array<IntArray> {
        val wRowSum = rowSum.clone()
        val wColSum = colSum.clone()

        val result = Array(wRowSum.size) { IntArray(wColSum.size) }
        var m = wRowSum.lastIndex
        var n = wColSum.lastIndex

        while (m > -1 && n > -1) {
            if (wRowSum[m] <= wColSum[n]) {
                result[m][n] = wRowSum[m]
                wColSum[n] -= wRowSum[m]
                wRowSum[m] = 0
                m--
            } else {
                result[m][n] = wColSum[n]
                wRowSum[m] -= wColSum[n]
                wColSum[n] = 0
                n--
            }
        }
        return result
    }
}