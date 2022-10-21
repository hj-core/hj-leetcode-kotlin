package com.hj.leetcode.kotlin.problem54

/**
 * LeetCode page: [54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the rows and columns of matrix;
     */
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        return getElementsSortedBySpiralOrder(matrix)
    }

    private tailrec fun getElementsSortedBySpiralOrder(
        matrix: Array<IntArray>,
        currShift: Int = 0,
        accElements: MutableList<Int> = mutableListOf()
    ): List<Int> {
        val bottomRow = matrix.lastIndex - currShift
        val rightColumn = matrix[0].lastIndex - currShift

        val shouldComplete = currShift > bottomRow || currShift > rightColumn
        if (shouldComplete) return accElements

        when (currShift) {
            bottomRow -> addElementsSingleRowCase(currShift, matrix, accElements)
            rightColumn -> addElementsSingleColumnCase(currShift, matrix, accElements)
            else -> addElementsInCurrentSpiralLayer(currShift, matrix, accElements)
        }

        return getElementsSortedBySpiralOrder(matrix, currShift + 1, accElements)
    }

    private fun addElementsSingleRowCase(
        currShift: Int,
        matrix: Array<IntArray>,
        container: MutableList<Int>
    ) {
        val rightColumn = matrix[0].lastIndex - currShift
        for (column in currShift..rightColumn) {
            container.add(matrix[currShift][column])
        }
    }

    private fun addElementsSingleColumnCase(
        currShift: Int,
        matrix: Array<IntArray>,
        container: MutableList<Int>
    ) {
        val bottomRow = matrix.lastIndex - currShift
        for (row in currShift..bottomRow) {
            container.add(matrix[row][currShift])
        }
    }

    private fun addElementsInCurrentSpiralLayer(
        currShift: Int,
        matrix: Array<IntArray>,
        container: MutableList<Int>
    ) {
        val bottomIndex = matrix.lastIndex - currShift
        val rightIndex = matrix[0].lastIndex - currShift

        for (column in currShift..rightIndex) {
            container.add(matrix[currShift][column])
        }

        for (row in currShift + 1 until bottomIndex) {
            container.add(matrix[row][rightIndex])
        }

        for (column in rightIndex downTo currShift) {
            container.add(matrix[bottomIndex][column])
        }

        for (row in bottomIndex - 1 downTo currShift + 1) {
            container.add(matrix[row][currShift])
        }
    }
}