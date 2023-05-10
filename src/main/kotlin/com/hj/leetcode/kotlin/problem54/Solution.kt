package com.hj.leetcode.kotlin.problem54

/**
 * LeetCode page: [54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Auxiliary Space O(1) where N is the number of elements in matrix;
     */
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        matrix.spiralTraversal { element ->
            result.add(element)
        }
        return result
    }

    private tailrec fun Array<IntArray>.spiralTraversal(
        fromLayer: Int = 0,
        onEachElement: (element: Int) -> Unit
    ) {
        require(fromLayer >= 0)

        // Check if the traversal is ended
        if (fromLayer > lastSpiralLayer()) {
            return
        }

        // Traverse the fromLayer
        spiralTraversalAt(fromLayer, onEachElement)

        // Recursive call for the next layer
        spiralTraversal(fromLayer + 1, onEachElement)
    }

    private fun Array<IntArray>.lastSpiralLayer(): Int {
        val indexLastRow = this.lastIndex
        val indexLastColumn = this[0].lastIndex
        val minLastIndex = minOf(indexLastRow, indexLastColumn)
        return minLastIndex / 2
    }

    private fun Array<IntArray>.spiralTraversalAt(
        layer: Int,
        onEachElement: (element: Int) -> Unit
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

        // Traverse the top row
        for (column in left..right) {
            onEachElement(this[top][column])
        }

        // Traverse the right column, except its first and last elements
        for (row in top + 1 until bottom) {
            onEachElement(this[row][right])
        }

        // If bottom and top are different, traverse the bottom row in reversed order
        if (bottom != top) {
            for (column in right downTo left) {
                onEachElement(this[bottom][column])
            }
        }

        /* If left and right are different, traverse the left column in reversed order,
         * except its first and last elements.
         */
        if (left != right) {
            for (row in bottom - 1 downTo top + 1) {
                onEachElement(this[row][left])
            }
        }
    }
}