package com.hj.leetcode.kotlin.problem766

import java.util.ArrayDeque

/**
 * LeetCode page: [766. Toeplitz Matrix](https://leetcode.com/problems/toeplitz-matrix/);
 */
class Solution2 {
    /* Complexity:
     * Time O(MN) and Space O(N) where M and N are the rows and columns of matrix;
     */
    fun isToeplitzMatrix(matrix: Array<IntArray>): Boolean {
        val reader = MatrixReader(matrix)
        val currRow = ArrayDeque<Int>()
        var nextRow = reader.readRow()

        nextRow?.let { currRow.addAll(it) }
        nextRow = reader.readRow()

        while (nextRow != null) {
            currRow.removeLast()
            currRow.addFirst(nextRow.next())

            val expectedNextRow = currRow.iterator()
            expectedNextRow.next()

            val isNotToeplitz = !nextRow.contentEquals(expectedNextRow)
            if (isNotToeplitz) return false

            nextRow = reader.readRow()
        }

        return true
    }

    private class MatrixReader(matrix: Array<IntArray>) {
        private val iterator = matrix.iterator()

        fun readRow() = if (iterator.hasNext()) iterator.next().iterator() else null
    }

    private fun <T> ArrayDeque<T>.addAll(iterator: Iterator<T>) {
        for (elem in iterator) {
            addLast(elem)
        }
    }

    private fun Iterator<Int>.contentEquals(other: Iterator<Int>): Boolean {
        for (value in other) {
            val notEqual = !hasNext() || next() != value
            if (notEqual) return false
        }

        return !hasNext()
    }
}