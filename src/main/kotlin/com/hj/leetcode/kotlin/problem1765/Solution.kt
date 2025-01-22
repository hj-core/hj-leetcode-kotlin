package com.hj.leetcode.kotlin.problem1765

/**
 * LeetCode page: [1765. Map of Highest Peak](https://leetcode.com/problems/map-of-highest-peak/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN)
     * where M and N are the number of rows and columns in isWater, respectively.
     */
    fun highestPeak(isWater: Array<IntArray>): Array<IntArray> {
        // Use BFS to determine the maximum possible height of each cell,
        // starting with the water cells.
        val cellNotVisited = -1
        val result = Array(isWater.size) { IntArray(isWater[it].size) { cellNotVisited } }
        var height = 0
        val currCells = ArrayDeque<Cell>() // Cells with the current height

        val cellIsWater = 1
        for (row in isWater.indices) {
            for (col in isWater[row].indices) {
                if (isWater[row][col] == cellIsWater) {
                    result[row][col] = 0
                    currCells.add(Cell(row, col))
                }
            }
        }

        while (currCells.isNotEmpty()) {
            height++
            repeat(currCells.size) {
                val cell = currCells.removeFirst()
                for (neighbour in cell.neighbours()) {
                    val (row, col) = neighbour
                    if (neighbour.withinBounds(result) && result[row][col] == cellNotVisited) {
                        result[row][col] = height
                        currCells.add(neighbour)
                    }
                }
            }
        }
        return result
    }

    private data class Cell(
        val row: Int,
        val col: Int,
    ) {
        fun neighbours(): List<Cell> =
            listOf(
                this.copy(row = row + 1),
                this.copy(row = row - 1),
                this.copy(col = col + 1),
                this.copy(col = col - 1),
            )

        fun withinBounds(grid: Array<IntArray>): Boolean = row in grid.indices && col in grid[row].indices
    }
}
