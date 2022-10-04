package com.hj.leetcode.kotlin.problem1072

/**
 * LeetCode page: [1072. Flip Columns For Maximum Number of Equal Rows](https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the number of rows and columns of matrix;
     */
    fun maxEqualRowsAfterFlips(matrix: Array<IntArray>): Int {
        val countPerPattern = HashMap<List<Int>, Int>()

        for (binaryArray in matrix) {
            val pattern = binaryArray.toList()
            countPerPattern[pattern] = countPerPattern.getOrDefault(pattern, 0) + 1

            val complementaryPattern = List(pattern.size) { index -> 1 - pattern[index] }
            countPerPattern[complementaryPattern] = countPerPattern.getOrDefault(complementaryPattern, 0) + 1
        }
        return countPerPattern.values.max()!!
    }
}