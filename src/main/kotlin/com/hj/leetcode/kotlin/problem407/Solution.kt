package com.hj.leetcode.kotlin.problem407

import java.util.*

/**
 * LeetCode page: [407. Trapping Rain Water II](https://leetcode.com/problems/trapping-rain-water-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(MN Log(MN)) and Space O(MN)
     * where M and N are the number of rows and columns in heightMap, respectively.
     */
    fun trapRainWater(heightMap: Array<IntArray>): Int {
        val totalRows = heightMap.size
        val totalCols = heightMap[0].size

        if (totalRows <= 2 || totalCols <= 2) {
            return 0
        }
        val newHeightMap = Array(totalRows) { heightMap[it].clone() }
        val visited = Array(totalRows) { BooleanArray(totalCols) }
        val boundaryPq = PriorityQueue<Cell>(compareBy { it.height(newHeightMap) })
        val quickList = mutableListOf<Cell>() // Boundary cells that will be processed before those in pq

        var result = 0
        addEdgeCells(totalRows, totalCols, visited, boundaryPq)

        while (boundaryPq.isNotEmpty()) {
            val curr = if (quickList.isNotEmpty()) quickList.removeLast() else boundaryPq.poll()
            val currHeight = curr.height(newHeightMap)

            for (next in curr.neighbours()) {
                if (!next.inGrid(newHeightMap) || next.isVisited(visited)) {
                    continue
                }
                next.markVisited(visited)

                val nextHeight = next.height(newHeightMap)
                if (nextHeight <= currHeight) {
                    result += currHeight - nextHeight
                    next.setHeight(newHeightMap, currHeight)
                    quickList.add(next)
                } else {
                    boundaryPq.add(next)
                }
            }
        }
        return result
    }

    private fun addEdgeCells(
        totalRows: Int,
        totalCols: Int,
        visited: Array<BooleanArray>,
        cells: PriorityQueue<Cell>,
    ) {
        // Add cells in the first and last rows
        for (col in 0..<totalCols) {
            val cellFirstRow = Cell(0, col)
            cells.add(cellFirstRow)
            cellFirstRow.markVisited(visited)

            val cellLastRow = Cell(totalRows - 1, col)
            cells.add(cellLastRow)
            cellLastRow.markVisited(visited)
        }
        // Add cells in the middle part of the first and last columns
        for (row in 1..<totalRows - 1) {
            val cellFirstCol = Cell(row, 0)
            cells.add(cellFirstCol)
            cellFirstCol.markVisited(visited)

            val cellLastCol = Cell(row, totalCols - 1)
            cells.add(cellLastCol)
            cellLastCol.markVisited(visited)
        }
    }

    private data class Cell(
        val row: Int,
        val col: Int,
    ) {
        fun markVisited(visited: Array<BooleanArray>) {
            visited[row][col] = true
        }

        fun isVisited(visited: Array<BooleanArray>): Boolean = visited[row][col]

        fun neighbours(): Array<Cell> =
            arrayOf(
                Cell(row + 1, col),
                Cell(row - 1, col),
                Cell(row, col + 1),
                Cell(row, col - 1),
            )

        fun inGrid(grid: Array<IntArray>): Boolean = row in grid.indices && col in grid[row].indices

        fun height(heightMap: Array<IntArray>): Int = heightMap[row][col]

        fun setHeight(
            heightMap: Array<IntArray>,
            newValue: Int,
        ) {
            heightMap[row][col] = newValue
        }
    }
}
