package com.hj.leetcode.kotlin.problem1162

/**
 * LeetCode page: [1162. As Far from Land as Possible](https://leetcode.com/problems/as-far-from-land-as-possible/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N^2) where N is the size of grid;
     */
    fun maxDistance(grid: Array<IntArray>): Int {
        val visited = grid.clone()
        val allCurrentNeighbours = ArrayDeque<Cell>()
        var maxDistance = 0

        allCurrentNeighbours.addAllLands(visited)
        val isAllWatersOrAllLands =
            allCurrentNeighbours.size.let { it == 0 || it == grid.size * grid.size }
        if (isAllWatersOrAllLands) return -1

        while (allCurrentNeighbours.isNotEmpty()) {
            repeat(allCurrentNeighbours.size) {
                val cell = allCurrentNeighbours.removeFirst()
                val neighbours = cell.neighbours()
                for (neighbour in neighbours) {
                    val isWaterAndNotVisited =
                        visited.has(neighbour) && visited.valueAt(neighbour) == 0
                    if (isWaterAndNotVisited) {
                        allCurrentNeighbours.addLast(neighbour)
                        visited.setValueAt(neighbour, 1)
                    }
                }
            }
            if (allCurrentNeighbours.isNotEmpty()) maxDistance++
        }

        return maxDistance
    }

    private data class Cell(val row: Int, val column: Int)

    private fun ArrayDeque<Cell>.addAllLands(grid: Array<IntArray>) {
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                val isLand = grid[row][column] == 1
                if (isLand) {
                    addLast(Cell(row, column))
                }
            }
        }
    }

    private fun Cell.neighbours(): List<Cell> {
        return listOf(
            Cell(row - 1, column),
            Cell(row + 1, column),
            Cell(row, column - 1),
            Cell(row, column + 1)
        )
    }

    private fun Array<IntArray>.has(cell: Cell): Boolean {
        return cell.row in indices && cell.column in this[cell.row].indices
    }

    private fun Array<IntArray>.valueAt(cell: Cell): Int {
        return this[cell.row][cell.column]
    }

    private fun Array<IntArray>.setValueAt(cell: Cell, newValue: Int) {
        this[cell.row][cell.column] = newValue
    }
}