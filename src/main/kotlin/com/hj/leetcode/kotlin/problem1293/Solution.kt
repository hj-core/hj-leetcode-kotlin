package com.hj.leetcode.kotlin.problem1293

/**
 * LeetCode page: [1293. Shortest Path in a Grid with Obstacles Elimination](https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/);
 */
class Solution {
    /* Complexity:
     * Time O(MNK) and Space O(MN) where M and N are the number of rows and columns of grid and K equals k;
     */
    fun shortestPath(grid: Array<IntArray>, k: Int): Int {
        val totalRows = grid.size
        val totalColumns = grid[0].size

        val isSingleCell = totalRows == 1 && totalColumns == 1
        if (isSingleCell) return 0

        val directions = Direction.values()

        var pathLength = 0
        val maxAvailableElimination = List(totalRows) { IntArray(totalColumns) { -1 } }
        val stateQueue = ArrayDeque<State>()

        stateQueue.addLast(State(0, 0, k))

        while (stateQueue.isNotEmpty()) {
            repeat(stateQueue.size) {
                val state = stateQueue.removeFirst()

                with(state) {
                    val canEliminateMore = availableElimination > maxAvailableElimination[row][column]

                    if (canEliminateMore) {
                        maxAvailableElimination[row][column] = availableElimination

                        for (direction in directions) {
                            val nextState = state.moveOrNull(direction, grid) ?: continue
                            stateQueue.addLast(nextState)

                            val reachGoal = nextState.row == totalRows - 1 && nextState.column == totalColumns - 1
                            if (reachGoal) return pathLength + 1
                        }
                    }
                }
            }

            pathLength++
        }

        return -1
    }

    private enum class Direction(val rowChange: Int, val columnChange: Int) {
        North(-1, 0),
        South(1, 0),
        West(0, -1),
        East(0, 1)
    }

    private class State(val row: Int, val column: Int, val availableElimination: Int)

    private fun State.moveOrNull(direction: Direction, grid: Array<IntArray>): State? {
        val newRow = row + direction.rowChange
        val newColumn = column + direction.columnChange

        val isOutOfGrid = newRow !in grid.indices || newColumn !in grid[newRow].indices
        if (isOutOfGrid) return null

        val newAvailableElimination = availableElimination - grid[newRow][newColumn]
        val cannotReach = newAvailableElimination < 0
        if (cannotReach) return null

        return State(newRow, newColumn, newAvailableElimination)
    }
}