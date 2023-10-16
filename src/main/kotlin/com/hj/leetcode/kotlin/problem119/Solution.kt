package com.hj.leetcode.kotlin.problem119

/**
 * LeetCode page: [119. Pascal's Triangle II](https://leetcode.com/problems/pascals-triangle-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(rowIndex) and Space O(rowIndex);
     */
    fun getRow(rowIndex: Int): List<Int> {
        val result = mutableListOf(1)
        for (i in 1..rowIndex) {
            val coeff = result.last().toLong() * (rowIndex - i + 1) / i
            result.add(coeff.toInt())
        }
        return result
    }
}