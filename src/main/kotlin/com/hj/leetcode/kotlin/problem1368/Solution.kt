package com.hj.leetcode.kotlin.problem1368

/**
 * LeetCode page: [1368. Minimum Cost to Make at Least One Valid Path in a Grid](https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN)
     * where M and N are the number of rows and columns in grid, respectively.
     */
    fun minCost(grid: Array<IntArray>): Int {
        val start = Cell(0, 0)
        val goal = Cell(grid.size - 1, grid.last().size - 1)

        var result = 0
        // Cells reachable with a cost within the current min cost
        val visited = Array(grid.size) { BooleanArray(grid[it].size) }
        // Cells reachable with exactly the current min cost
        val reachableAtCost = ArrayDeque<Cell>()
        explore(start, grid, visited, reachableAtCost)

        while (!goal.isReachable(visited)) {
            result++
            // With this extra cost,
            // unreachable cells adjacent to the previously reachable cells now become reachable.
            repeat(reachableAtCost.size) {
                val cell = reachableAtCost.removeFirst()
                for (direction in 1..4) {
                    val moved = cell.moved(direction)
                    if (moved.inGrid(grid) && !moved.isReachable(visited)) {
                        reachableAtCost.add(moved)
                        moved.markReachable(visited)
                    }
                }
            }
            // Cells reachable from the above cells also become reachable
            repeat(reachableAtCost.size) {
                val source = reachableAtCost[it].moved(grid)
                explore(source, grid, visited, reachableAtCost)
            }
        }
        return result
    }

    // Explore the grid from the source, marking each cell in the path as visited
    // and adding it to the reachable.
    // The process terminates when it falls off the grid or encounters a visited cell.
    private fun explore(
        source: Cell,
        grid: Array<IntArray>,
        visited: Array<BooleanArray>,
        reachable: ArrayDeque<Cell>,
    ) {
        var cell = source
        while (cell.inGrid(grid) && !cell.isReachable(visited)) {
            reachable.add(cell)
            cell.markReachable(visited)
            cell = cell.moved(grid)
        }
    }

    private class Cell(
        val row: Int,
        val col: Int,
    ) {
        fun inGrid(grid: Array<IntArray>): Boolean = row in grid.indices && col in grid[row].indices

        fun isReachable(visited: Array<BooleanArray>): Boolean = visited[row][col]

        fun markReachable(visited: Array<BooleanArray>) {
            visited[row][col] = true
        }

        fun moved(grid: Array<IntArray>): Cell = moved(grid[row][col])

        fun moved(direction: Int): Cell =
            when (direction) {
                1 -> Cell(row, col + 1)
                2 -> Cell(row, col - 1)
                3 -> Cell(row + 1, col)
                4 -> Cell(row - 1, col)
                else -> throw IllegalArgumentException()
            }
    }
}
