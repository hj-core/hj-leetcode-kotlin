package com.hj.leetcode.kotlin.problem1072

/**
 * LeetCode page: [1072. Flip Columns For Maximum Number of Equal Rows](https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M is the number of rows in matrix
     * and N is the number of columns in matrix.
     */
    fun maxEqualRowsAfterFlips(matrix: Array<IntArray>): Int =
        matrix
            .groupingBy { describeStructure(it) }
            .eachCount()
            .values
            .max()

    private fun describeStructure(row: IntArray): List<Int> =
        buildList {
            val bitWidth = 32
            for (i in row.indices step bitWidth) {
                var component = 0
                for (j in i..<kotlin.math.min(i + bitWidth, row.size)) {
                    component = (component shl 1) + (row[j] xor row[0])
                }
                add(component)
            }
        }
}
