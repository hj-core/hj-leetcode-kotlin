package com.hj.leetcode.kotlin.problem934

/**
 * LeetCode page: [934. Shortest Bridge](https://leetcode.com/problems/shortest-bridge/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of cells in grid;
     */
    fun shortestBridge(grid: Array<IntArray>): Int {
        val visited = Array(grid.size) { BooleanArray(grid[it].size) }
        val currentLayer = waterSurroundingIsland(firstLand(grid), grid, visited)

        /* Search for the second island, layer by layer, and return the distance
         * when it is found.
         */
        var result = 1
        while (currentLayer.isNotEmpty()) {
            repeat(currentLayer.size) {
                val water = currentLayer.removeFirst()
                for (cell in water.adjacentCells(grid)) {
                    if (cell.isVisited(visited)) {
                        continue
                    }

                    cell.setVisited(visited)
                    when {
                        cell.isLand(grid) -> return result
                        cell.isWater(grid) -> currentLayer.addLast(cell)
                        else -> throw NoWhenBranchMatchedException()
                    }
                }
            }
            result++
        }
        throw IllegalStateException("Can not reach the second island.")
    }

    /**
     * Return the water that surrounds the island containing [seedLand].
     * This function will set [visited] of all lands of the island and the surrounding water.
     */
    private fun waterSurroundingIsland(
        seedLand: Cell,
        grid: Array<IntArray>,
        visited: Array<BooleanArray>
    ): ArrayDeque<Cell> {

        require(seedLand.isLand(grid))

        val result = ArrayDeque<Cell>()
        val lands = ArrayDeque<Cell>()
        lands.add(seedLand)
        seedLand.setVisited(visited)

        while (lands.isNotEmpty()) {
            val land = lands.removeFirst()
            for (cell in land.adjacentCells(grid)) {
                if (cell.isVisited(visited)) {
                    continue
                }

                cell.setVisited(visited)
                when {
                    cell.isLand(grid) -> lands.addLast(cell)
                    cell.isWater(grid) -> result.addLast(cell)
                    else -> throw NoWhenBranchMatchedException()
                }
            }
        }
        return result
    }

    private fun firstLand(grid: Array<IntArray>): Cell {
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                if (isLand(grid[row][column])) {
                    return Cell(row, column)
                }
            }
        }
        throw IllegalStateException("Can not find any land.")
    }

    private data class Cell(val row: Int, val column: Int) {

        fun adjacentCells(grid: Array<IntArray>): List<Cell> {
            return listOf(
                Cell(row - 1, column),
                Cell(row + 1, column),
                Cell(row, column + 1),
                Cell(row, column - 1)
            ).filter { it.inGrid(grid) }
        }

        fun inGrid(grid: Array<IntArray>): Boolean {
            return row in grid.indices && column in grid[row].indices
        }

        fun isVisited(visited: Array<BooleanArray>): Boolean {
            return visited[row][column]
        }

        fun setVisited(visited: Array<BooleanArray>) {
            visited[row][column] = true
        }

        fun value(grid: Array<IntArray>): Int {
            return grid[row][column]
        }
    }

    private fun Cell.isLand(grid: Array<IntArray>): Boolean = isLand(value(grid))

    private fun Cell.isWater(grid: Array<IntArray>): Boolean = isWater(value(grid))

    private fun isLand(value: Int): Boolean = value == 1

    private fun isWater(value: Int): Boolean = value == 0
}