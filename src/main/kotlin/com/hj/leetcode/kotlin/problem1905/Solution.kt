package com.hj.leetcode.kotlin.problem1905

/**
 * LeetCode page: [1905. Count Sub Islands](https://leetcode.com/problems/count-sub-islands/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M is the number of rows in grid1 and grid2
     * and N is the number columns in grid1 and grid2;
     */
    fun countSubIslands(
        grid1: Array<IntArray>,
        grid2: Array<IntArray>,
    ): Int {
        var result = 0
        val isWater =
            Array(grid2.size) { row ->
                BooleanArray(grid2[row].size) { column ->
                    grid2[row][column] == 0
                }
            }

        for (row in isWater.indices) {
            for (column in isWater[row].indices) {
                if (isWater[row][column]) {
                    continue
                }

                var isSubIsland = true
                findConnectedComponent(Cell(row, column), isWater) { land ->
                    isSubIsland = isSubIsland && (grid1[land.row][land.column] == 1)
                }
                if (isSubIsland) result++
            }
        }
        return result
    }

    private fun findConnectedComponent(
        source: Cell,
        isWater: Array<BooleanArray>,
        onEachLand: (land: Cell) -> Unit,
    ) {
        if (source.row !in isWater.indices || source.column !in isWater[source.row].indices) {
            return
        }
        if (isWater[source.row][source.column]) {
            return
        }
        onEachLand(source)
        isWater[source.row][source.column] = true

        findConnectedComponent(source.copy(row = source.row + 1), isWater, onEachLand)
        findConnectedComponent(source.copy(row = source.row - 1), isWater, onEachLand)
        findConnectedComponent(source.copy(column = source.column + 1), isWater, onEachLand)
        findConnectedComponent(source.copy(column = source.column - 1), isWater, onEachLand)
    }

    private data class Cell(
        val row: Int,
        val column: Int,
    )
}
