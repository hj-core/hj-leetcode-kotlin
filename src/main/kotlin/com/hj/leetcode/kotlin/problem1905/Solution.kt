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
        val visited =
            Array(grid2.size) { row ->
                BooleanArray(grid2[row].size) { column ->
                    grid2[row][column] == 0
                }
            }

        for (row in visited.indices) {
            for (column in visited[row].indices) {
                if (visited[row][column]) {
                    continue
                }

                val component = findConnectedComponent(Cell(row, column), visited, mutableListOf())
                if (component.isNotEmpty() && component.all { grid1[it.row][it.column] == 1 }) {
                    result += 1
                }
            }
        }
        return result
    }

    private fun findConnectedComponent(
        source: Cell,
        visited: Array<BooleanArray>,
        result: MutableList<Cell>,
    ): List<Cell> {
        if (source.row !in visited.indices || source.column !in visited[source.row].indices) {
            return emptyList()
        }
        if (visited[source.row][source.column]) {
            return emptyList()
        }
        result.add(source)
        visited[source.row][source.column] = true

        findConnectedComponent(source.copy(row = source.row + 1), visited, result)
        findConnectedComponent(source.copy(row = source.row - 1), visited, result)
        findConnectedComponent(source.copy(column = source.column + 1), visited, result)
        findConnectedComponent(source.copy(column = source.column - 1), visited, result)
        return result
    }

    private data class Cell(
        val row: Int,
        val column: Int,
    )
}
