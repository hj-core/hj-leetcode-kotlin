package com.hj.leetcode.kotlin.problem118

/**
 * LeetCode page: [118. Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/);
 */
class Solution {
    /* Complexity:
     * Time O(numRows^2) and Space O(numRows^2);
     */
    fun generate(numRows: Int): List<List<Int>> {
        val generator = generateSequence(listOf(1)) { previousRow ->
            val newRow = mutableListOf(1)
            previousRow.asSequence()
                .zipWithNext { a, b -> a + b }
                .forEach { newRow.add(it) }
            newRow.add(1)
            newRow
        }
        return generator.take(numRows).toList()
    }
}