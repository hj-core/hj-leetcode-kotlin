package com.hj.leetcode.kotlin.problem934

/**
 * LeetCode page: [934. Shortest Bridge](https://leetcode.com/problems/shortest-bridge/);
 */
class SolutionDraft {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of cells in grid;
     */
    fun shortestBridge(grid: Array<IntArray>): Int {
        var seedLand = Cell(-1, -1)
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                if (isLand(grid[row][column])) {
                    seedLand = Cell(row, column)
                    break
                }
            }
        }
        check(seedLand.inGrid(grid))

        val visited = Array(grid.size) { BooleanArray(grid[it].size) }
        val landQueue = ArrayDeque<Cell>()
        val waterQueue = ArrayDeque<Cell>()

        visited[seedLand.row][seedLand.column] = true
        landQueue.add(seedLand)

        while (landQueue.isNotEmpty()) {
            val land = landQueue.removeFirst()
            val surroundingCells = land.surroundingCells(grid)
            for (cell in surroundingCells) {
                if (visited[cell.row][cell.column]) {
                    continue
                }

                visited[cell.row][cell.column] = true
                if (isLand(cell.value(grid))) {
                    landQueue.addLast(cell)
                }

                if (isWater(cell.value(grid))) {
                    waterQueue.addLast(cell)
                }
            }
        }

        var result = 1

        while (waterQueue.isNotEmpty()) {
            repeat(waterQueue.size) {
                val water = waterQueue.removeFirst()
                val surroundingCells = water.surroundingCells(grid)
                for (cell in surroundingCells) {
                    if (visited[cell.row][cell.column]) {
                        continue
                    }

                    visited[cell.row][cell.column] = true
                    if (isLand(cell.value(grid))) {
                        return result
                    }

                    if (isWater(cell.value(grid))) {
                        waterQueue.addLast(cell)
                    }
                }
            }
            result++
        }

        throw IllegalStateException("Can not connect the two islands.")
    }

    private data class Cell(val row: Int, val column: Int) {

        fun inGrid(grid: Array<IntArray>): Boolean {
            return row in grid.indices && column in grid[row].indices
        }

        fun surroundingCells(grid: Array<IntArray>): List<Cell> {
            return listOf(
                Cell(row - 1, column),
                Cell(row + 1, column),
                Cell(row, column + 1),
                Cell(row, column - 1)
            ).filter { it.inGrid(grid) }
        }

        fun value(grid: Array<IntArray>): Int {
            return grid[row][column]
        }
    }

    private fun isLand(value: Int): Boolean = value == 1

    private fun isWater(value: Int): Boolean = value == 0
}