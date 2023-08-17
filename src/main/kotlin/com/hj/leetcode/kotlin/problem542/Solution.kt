package com.hj.leetcode.kotlin.problem542

/**
 * LeetCode page: [542. 01 Matrix](https://leetcode.com/problems/01-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the number of rows and columns in mat;
     */
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        val result = Array(mat.size) { row ->
            IntArray(mat[row].size) { Int.MAX_VALUE }
        }
        val queue = ArrayDeque<Cell>()

        // Base cases are all zero-cells
        searchZeroCells(mat) { zeroCell ->
            result[zeroCell.row][zeroCell.column] = 0
            queue.addLast(zeroCell)
        }

        // Update the distance of each one-cell through relaxation
        while (queue.isNotEmpty()) {
            relaxFirst(queue, mat, result)
        }
        return result
    }

    private data class Cell(val row: Int, val column: Int)

    private inline fun searchZeroCells(mat: Array<IntArray>, onZeroCell: (zeroCell: Cell) -> Unit) {
        for (row in mat.indices) {
            for (column in mat[row].indices) {
                if (mat[row][column] == 0) {
                    onZeroCell(Cell(row, column))
                }
            }
        }
    }

    private fun relaxFirst(
        queue: ArrayDeque<Cell>,
        mat: Array<IntArray>,
        minDistances: Array<IntArray> // minimum distance to zero of each cell
    ) {
        val cell = queue.removeFirst()

        for (neighbour in cell.neighbours()) {
            if (neighbour.isOutside(mat)) {
                continue
            }

            if (minDistances.getValue(cell) + 1 < minDistances.getValue(neighbour)) {
                minDistances.setValue(neighbour, minDistances.getValue(cell) + 1)
                queue.addLast(neighbour)
            }
        }
    }

    private fun Cell.neighbours(): List<Cell> = listOf(
        Cell(row - 1, column),
        Cell(row + 1, column),
        Cell(row, column - 1),
        Cell(row, column + 1)
    )

    private fun Cell.isOutside(mat: Array<IntArray>): Boolean {
        return row !in mat.indices || column !in mat[row].indices
    }

    private fun Array<IntArray>.getValue(cell: Cell): Int {
        return this[cell.row][cell.column]
    }

    private fun Array<IntArray>.setValue(cell: Cell, newValue: Int) {
        this[cell.row][cell.column] = newValue
    }
}

