package com.hj.leetcode.kotlin.problem2812

import kotlin.math.min

/**
 * LeetCode page: [2812. Find the Safest Path in a Grid](https://leetcode.com/problems/find-the-safest-path-in-a-grid/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2 LogN) and Space O(N^2) where N is the size of grid;
     */
    fun maximumSafenessFactor(grid: List<List<Int>>): Int {
        if (grid[0][0] == 1 || grid.last().last() == 1) {
            return 0
        }
        return binarySearchMaxFactor(grid)
    }

    private fun binarySearchMaxFactor(grid: List<List<Int>>): Int {
        val start = Cell(0, 0)
        val minThiefDistances = minThiefDistanceOfCells(grid)
        var low = 0
        var high = min(minThiefDistances[0][0], minThiefDistances.last().last())

        while (low <= high) {
            val mid = low + (high - low) / 2
            if (existPath(start, grid, minThiefDistances, mid, hashSetOf())) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }
        return high
    }

    private fun minThiefDistanceOfCells(grid: List<List<Int>>): List<List<Int>> {
        val result = List(grid.size) { MutableList(grid.size) { -1 } }
        val bfsQueue = ArrayDeque<Cell>()

        addThiefCells(bfsQueue, grid)
        setThiefCellDistances(result, bfsQueue)

        var distance = 1
        while (bfsQueue.isNotEmpty()) {
            repeat(bfsQueue.size) {
                val cell = bfsQueue.removeFirst()
                for (neighbour in cell.neighbours()) {
                    val (nextRow, nextColumn) = neighbour

                    if (nextRow in grid.indices
                        && nextColumn in grid.indices
                        && result[nextRow][nextColumn] == -1 // Not visited
                    ) {
                        result[nextRow][nextColumn] = distance
                        bfsQueue.addLast(neighbour)
                    }
                }
            }
            distance++
        }
        return result
    }

    private data class Cell(val row: Int, val column: Int) {

        fun neighbours(): List<Cell> = listOf(
            Cell(row - 1, column),
            Cell(row + 1, column),
            Cell(row, column - 1),
            Cell(row, column + 1),
        )
    }

    private fun addThiefCells(toQueue: ArrayDeque<Cell>, grid: List<List<Int>>) {
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                if (grid[row][column] == 1) {
                    toQueue.addLast(Cell(row, column))
                }
            }
        }
    }

    private fun setThiefCellDistances(
        ofMinThiefDistances: List<MutableList<Int>>,
        thiefCells: Collection<Cell>,
    ) {
        for ((row, col) in thiefCells) {
            ofMinThiefDistances[row][col] = 0
        }
    }

    private fun existPath(
        start: Cell,
        grid: List<List<Int>>,
        minThiefDistances: List<List<Int>>,
        targetSafeFactor: Int,
        visited: MutableSet<Cell>,
    ): Boolean {
        val (row, column) = start
        if (row !in grid.indices
            || column !in grid[row].indices
            || start in visited
            || minThiefDistances[row][column] < targetSafeFactor
        ) {
            return false
        }

        visited.add(start)
        if (row == grid.lastIndex && column == grid.last().lastIndex) {
            return true
        }
        return start.neighbours().any {
            existPath(it, grid, minThiefDistances, targetSafeFactor, visited)
        }
    }
}