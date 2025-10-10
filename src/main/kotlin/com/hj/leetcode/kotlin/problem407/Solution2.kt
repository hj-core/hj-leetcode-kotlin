package com.hj.leetcode.kotlin.problem407

/**
 * LeetCode page: [407. Trapping Rain Water II](https://leetcode.com/problems/trapping-rain-water-ii/);
 */
class Solution2 {
    // Complexity:
    // Time O((MN)^2) and Space O(MN) where M and N are the number
    // of rows and columns in heightMap, respectively.
    fun trapRainWater(heightMap: Array<IntArray>): Int {
        val m = heightMap.size
        val n = heightMap[0].size

        if (m < 3 || n < 3) {
            return 0
        }

        val newHeightMap = Array(heightMap.size) { heightMap[it].clone() }
        val innerCells = mutableListOf<Cell>()
        val isBoundary = Array(heightMap.size) { BooleanArray(n) }

        // Set edge cells to true in isBoundary, and collect inner cells
        for (row in newHeightMap.indices) {
            for (col in newHeightMap[row].indices) {
                val cell = Cell(row, col)
                if (cell.isInner(newHeightMap)) {
                    innerCells.add(cell)
                } else {
                    cell.markBoundary(isBoundary)
                }
            }
        }

        var result = 0
        innerCells.sortBy { it.height(newHeightMap) }

        for (cell in innerCells) {
            if (cell.isBoundary(isBoundary)) {
                continue
            }
            if (cell.height(newHeightMap) != cell.height(heightMap)) {
                // The cell has been elevated to the height of a later cell,
                // will be visited again when processing the later cell.
                continue
            }
            val pCells = dfsPlatformCells(cell, newHeightMap, isBoundary, hashSetOf())
            val pHeight = pCells.first().height(newHeightMap)
            val borderHeight = borderHeight(pCells, newHeightMap)
            if (borderHeight <= pHeight) {
                for (pCell in pCells) {
                    pCell.markBoundary(isBoundary)
                }
            } else {
                for (pCell in pCells) {
                    pCell.setHeight(newHeightMap, borderHeight)
                }
                result += (borderHeight - pHeight) * pCells.size
            }
        }
        return result
    }

    private fun dfsPlatformCells(
        start: Cell,
        heightMap: Array<IntArray>,
        isBoundary: Array<BooleanArray>,
        visited: HashSet<Cell>,
    ): Set<Cell> {
        if (start in visited) {
            return visited
        }
        visited.add(start)
        for (next in start.neighbours()) {
            if (!next.isBoundary(isBoundary) && next.height(heightMap) == start.height(heightMap)) {
                dfsPlatformCells(next, heightMap, isBoundary, visited)
            }
        }
        return visited
    }

    private fun borderHeight(
        platformCells: Set<Cell>,
        heightMap: Array<IntArray>,
    ): Int {
        var result = Int.MAX_VALUE
        for (cell in platformCells) {
            for (next in cell.neighbours()) {
                if (next !in platformCells) {
                    result = minOf(result, next.height(heightMap))
                }
            }
        }
        return result
    }

    private data class Cell(
        val row: Int,
        val col: Int,
    ) {
        fun isInner(grid: Array<IntArray>): Boolean = row in 1..<grid.lastIndex && col in 1..<grid[row].lastIndex

        fun markBoundary(isBoundary: Array<BooleanArray>) {
            isBoundary[row][col] = true
        }

        fun isBoundary(isBoundary: Array<BooleanArray>): Boolean = isBoundary[row][col]

        fun height(heightMap: Array<IntArray>): Int = heightMap[row][col]

        fun setHeight(
            heightMap: Array<IntArray>,
            newValue: Int,
        ) {
            heightMap[row][col] = newValue
        }

        fun neighbours(): Array<Cell> =
            arrayOf(
                Cell(row + 1, col),
                Cell(row - 1, col),
                Cell(row, col + 1),
                Cell(row, col - 1),
            )
    }
}
