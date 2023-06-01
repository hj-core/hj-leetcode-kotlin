package com.hj.leetcode.kotlin.problem1091

/**
 * LeetCode page: [1091. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the number of rows and columns in grid;
     */
    fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
        if (isNotClear(grid.first().first()) || isNotClear(grid.last().last())) {
            return -1
        }

        val bfsVisited = Array(grid.size) { row -> BooleanArray(grid[row].size) }
        val cellsOfLength = ArrayDeque<Cell>()

        var currentLength = 1
        Cell(0, 0).setVisited(bfsVisited)
        Cell(0, 0).addToLast(cellsOfLength)


        while (cellsOfLength.isNotEmpty()) {
            repeat(cellsOfLength.size) {
                val cell = cellsOfLength.removeFirst()
                if (cell.isBottomRight(grid)) {
                    return currentLength
                }

                for (adjacentCell in cell.adjacentCells(grid)) {
                    if (adjacentCell.isVisited(bfsVisited)) {
                        continue
                    }
                    adjacentCell.setVisited(bfsVisited)

                    if (adjacentCell.isClear(grid)) {
                        adjacentCell.addToLast(cellsOfLength)
                    }
                }
            }
            currentLength++
        }
        return -1
    }

    private fun isNotClear(value: Int): Boolean = value == 1

    private class Cell(val row: Int, val column: Int) {

        fun setVisited(visited: Array<BooleanArray>) {
            visited[row][column] = true
        }

        fun isVisited(visited: Array<BooleanArray>): Boolean {
            return visited[row][column]
        }

        fun addToLast(queue: ArrayDeque<Cell>) {
            queue.addLast(this)
        }

        fun adjacentCells(grid: Array<IntArray>): List<Cell> {
            return listOf(
                Cell(row - 1, column - 1),
                Cell(row - 1, column),
                Cell(row - 1, column + 1),
                Cell(row, column - 1),
                Cell(row, column + 1),
                Cell(row + 1, column - 1),
                Cell(row + 1, column),
                Cell(row + 1, column + 1)
            ).filter { it.inGrid(grid) }
        }

        private fun inGrid(grid: Array<IntArray>): Boolean {
            return row in grid.indices && column in grid[row].indices
        }

        fun value(grid: Array<IntArray>): Int {
            return grid[row][column]
        }

        fun isBottomRight(grid: Array<IntArray>): Boolean {
            return row == grid.lastIndex && column == grid[row].lastIndex
        }
    }

    private fun Cell.isClear(grid: Array<IntArray>): Boolean {
        return !isNotClear(value(grid))
    }
}