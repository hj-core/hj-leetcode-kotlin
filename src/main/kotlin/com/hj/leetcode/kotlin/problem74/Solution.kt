package com.hj.leetcode.kotlin.problem74

/**
 * LeetCode page: [74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN + LogM) and Space O(1) where N and M are the number of rows and columns of matrix;
     */
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val targetTooSmall = target < matrix[0][0]
        val targetTooLarge = target > matrix.last().last()
        if (targetTooSmall || targetTooLarge) return false

        var row = binarySearch(target, 0, matrix.lastIndex) { index -> matrix[index][0] }
        val targetFound = matrix.getOrNull(row)?.get(0) == target
        if (targetFound) return true else row--

        val column = binarySearch(target, 0, matrix[row].lastIndex) { index -> matrix[row][index] }
        if (column == matrix[row].size) return false

        return matrix[row][column] == target
    }

    private fun binarySearch(
        target: Int,
        fromIndex: Int,
        toIndex: Int,
        readIndex: (index: Int) -> Int
    ): Int {
        var leftBound = fromIndex
        var rightBound = toIndex

        while (leftBound <= rightBound) {
            val midIndex = (leftBound + rightBound) ushr 1
            val midValue = readIndex(midIndex)
            when {
                midValue < target -> leftBound = midIndex + 1
                midValue > target -> rightBound = midIndex - 1
                else -> return midIndex
            }
        }
        return leftBound
    }
}