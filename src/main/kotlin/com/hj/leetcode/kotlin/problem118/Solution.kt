package com.hj.leetcode.kotlin.problem118

/**
 * LeetCode page: [118. Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/);
 */
class Solution {
    /* Complexity:
     * Time O(numRows^2) and Space O(numRows^2);
     */
    fun generate(numRows: Int): List<List<Int>> {
        val result = mutableListOf(mutableListOf(1))
        for (i in 1..<numRows) {
            val row = mutableListOf(1)
            for (j in 1..<i) {
                row.add(result[i - 1][j - 1] + result[i - 1][j])
            }
            row.add(1)
            result.add(row)
        }
        return result
    }
}
