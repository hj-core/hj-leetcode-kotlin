package com.hj.leetcode.kotlin.problem934

/**
 * LeetCode page: [934. Shortest Bridge](https://leetcode.com/problems/shortest-bridge/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N^2) where N is the size of grid;
     */
    fun shortestBridge(grid: Array<IntArray>): Int {
        val visited = List(grid.size) { BooleanArray(grid.size) }
        var surrounding = findCellsAroundIslandAndUpdateVisited(visited, grid)

        var shortest = 0
        while (surrounding.isNotEmpty()) {
            val reachSecondIsland = surrounding.any { grid[it.row][it.column] == 1 }
            if (reachSecondIsland) return shortest

            surrounding = findUnvisitedAroundCellsAndUpdateVisited(surrounding, visited, grid)
            shortest++
        }
        throw IllegalStateException("Cannot reach second island.")
    }

    private fun findCellsAroundIslandAndUpdateVisited(
        visited: List<BooleanArray>,
        grid: Array<IntArray>
    ): List<Cell> {
        val seedLand = findSeedLand(grid)
        val surrounding = mutableListOf<Cell>()
        addCellsAroundIslandAndUpdateVisited(seedLand, visited, grid, surrounding)
        return surrounding
    }

    private fun findSeedLand(grid: Array<IntArray>): Cell {
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                val isLand = grid[row][column] == 1
                if (isLand) return Cell(row, column)
            }
        }
        throw IllegalStateException("No land found.")
    }

    private data class Cell(val row: Int, val column: Int) {
        val north get() = Cell(row - 1, column)
        val south get() = Cell(row + 1, column)
        val west get() = Cell(row, column - 1)
        val east get() = Cell(row, column + 1)
    }

    private fun addCellsAroundIslandAndUpdateVisited(
        seedLand: Cell,
        visited: List<BooleanArray>,
        grid: Array<IntArray>,
        container: MutableList<Cell>
    ) {
        with(seedLand) {
            val shouldSkip = this.isInvalid(grid) || visited[row][column]
            if (shouldSkip) return

            visited[row][column] = true

            val isSurrounding = grid[row][column] == 0
            if (isSurrounding) {
                container.add(this)
            } else {
                addCellsAroundIslandAndUpdateVisited(north, visited, grid, container)
                addCellsAroundIslandAndUpdateVisited(south, visited, grid, container)
                addCellsAroundIslandAndUpdateVisited(west, visited, grid, container)
                addCellsAroundIslandAndUpdateVisited(east, visited, grid, container)
            }
        }
    }

    private fun Cell.isInvalid(grid: Array<IntArray>) = row !in grid.indices || column !in grid[row].indices

    private fun findUnvisitedAroundCellsAndUpdateVisited(
        cells: List<Cell>,
        visited: List<BooleanArray>,
        grid: Array<IntArray>
    ): List<Cell> {
        val newSurrounding = mutableListOf<Cell>()
        for (cell in cells) {
            addCellIfUnvisitedAndUpdateVisited(cell.north, visited, grid, newSurrounding)
            addCellIfUnvisitedAndUpdateVisited(cell.south, visited, grid, newSurrounding)
            addCellIfUnvisitedAndUpdateVisited(cell.west, visited, grid, newSurrounding)
            addCellIfUnvisitedAndUpdateVisited(cell.east, visited, grid, newSurrounding)
        }
        return newSurrounding
    }

    private fun addCellIfUnvisitedAndUpdateVisited(
        cell: Cell,
        visited: List<BooleanArray>,
        grid: Array<IntArray>,
        container: MutableList<Cell>
    ) {
        with(cell) {
            val shouldSkip = cell.isInvalid(grid) || visited[row][column]
            if (shouldSkip) return

            container.add(this)
            visited[row][column] = true
        }
    }
}