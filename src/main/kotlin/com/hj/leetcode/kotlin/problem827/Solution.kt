package com.hj.leetcode.kotlin.problem827

/**
 * LeetCode page: [827. Making A Large Island](https://leetcode.com/problems/making-a-large-island/);
 */
class Solution {
    // move= Pair(dr, dc)
    private val moves = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

    // Complexity:
    // Time O(N^2) and Space O(N^2)
    // where N is the number of rows and columns in grid.
    fun largestIsland(grid: Array<IntArray>): Int {
        // islandIds[row][col] ::=
        // the island id associated with cell(row, col); a negative value indicates undefined.
        val islandIds = Array(grid.size) { IntArray(grid[it].size) { -1 } }

        // islandSizes[id] ::= the size of the island associated with this id
        val islandSizes = mutableListOf<Int>()

        exploreAllIslands(grid, islandIds, islandSizes)
        if (islandSizes.isEmpty()) {
            return 1
        }
        if (islandSizes.size == 1) {
            return minOf(islandSizes[0] + 1, grid.size * grid.size)
        }

        var result = 0 // It's safe to start with 0 because we have ruled out the all land case.
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (!isWater(grid[row][col])) {
                    continue
                }
                val ids = mutableSetOf<Int>()
                for ((dr, dc) in moves) {
                    val nextRow = row + dr
                    val nextCol = col + dc
                    if (nextRow in grid.indices && nextCol in grid[nextRow].indices) {
                        ids.add(islandIds[nextRow][nextCol])
                    }
                }
                ids.remove(-1)
                val aggregatedSize = 1 + ids.sumOf { islandSizes[it] }
                result = maxOf(result, aggregatedSize)
            }
        }
        return result
    }

    private fun isWater(value: Int): Boolean = value == 0

    // Explores the grid, associates each land with the island's id, and updates the island size.
    private fun exploreAllIslands(
        grid: Array<IntArray>,
        islandIds: Array<IntArray>,
        islandSizes: MutableList<Int>,
    ) {
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (isWater(grid[row][col]) || 0 <= islandIds[row][col]) {
                    continue
                }
                islandIds[row][col] = islandSizes.size
                islandSizes.add(1)
                exploreIsland(row, col, grid, islandIds, islandSizes)
            }
        }
    }

    private fun exploreIsland(
        row: Int,
        col: Int,
        grid: Array<IntArray>,
        islandIds: Array<IntArray>,
        islandSizes: MutableList<Int>,
    ) {
        val id = islandIds[row][col]
        require(0 <= id)

        for ((dr, dc) in moves) {
            val nextRow = row + dr
            val nextCol = col + dc

            if (nextRow in grid.indices &&
                nextCol in grid[nextRow].indices &&
                islandIds[nextRow][nextCol] < 0 &&
                !isWater(grid[nextRow][nextCol])
            ) {
                islandIds[nextRow][nextCol] = id
                islandSizes[id]++
                exploreIsland(nextRow, nextCol, grid, islandIds, islandSizes)
            }
        }
    }
}
