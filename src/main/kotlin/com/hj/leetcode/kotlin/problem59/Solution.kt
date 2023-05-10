package com.hj.leetcode.kotlin.problem59

/**
 * LeetCode page: [59. Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(n^2) and Auxiliary Space O(1);
     */
    fun generateMatrix(n: Int): Array<IntArray> {
        val result = Array(n) { IntArray(n) }
        var currentValue = 1
        result.spiralTraversal { row, column ->
            result[row][column] = currentValue
            currentValue++
        }
        return result
    }

    private tailrec fun Array<IntArray>.spiralTraversal(
        fromLayer: Int = 0,
        atEachPosition: (row: Int, column: Int) -> Unit
    ) {
        require(fromLayer >= 0)

        // Check if the traversal is ended
        if (fromLayer > lastSpiralLayer()) {
            return
        }

        // Traversal the fromLayer
        spiralTraversalAt(fromLayer, atEachPosition)

        // Recursive call for next layer
        spiralTraversal(fromLayer + 1, atEachPosition)
    }

    private fun Array<IntArray>.lastSpiralLayer(): Int {
        val indexLastRow = this.lastIndex
        val indexLastColumn = this[0].lastIndex
        val minLastIndex = minOf(indexLastRow, indexLastColumn)
        return minLastIndex / 2
    }

    private fun Array<IntArray>.spiralTraversalAt(
        layer: Int,
        atEachPosition: (row: Int, column: Int) -> Unit
    ) {
        require(layer >= 0)

        val indexLastRow = this.lastIndex
        val indexLastColumn = this[0].lastIndex
        val top = layer
        val bottom = indexLastRow - layer
        val left = layer
        val right = indexLastColumn - layer

        check(top <= bottom)
        check(left <= right)

        // Traversal the top row
        for (column in left..right) {
            atEachPosition(top, column)
        }

        // Traversal the right column, except its first and last elements
        for (row in top + 1 until bottom) {
            atEachPosition(row, right)
        }

        // If bottom and top are different, traversal the bottom row in reversed order
        if (bottom != top) {
            for (column in right downTo left) {
                atEachPosition(bottom, column)
            }
        }

        /* If left and right are different, traversal the left column in reversed order,
         * except its first and last elements.
         */
        if (left != right) {
            for (row in bottom - 1 downTo top + 1) {
                atEachPosition(row, left)
            }
        }
    }
}