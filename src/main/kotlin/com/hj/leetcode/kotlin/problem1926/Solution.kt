package com.hj.leetcode.kotlin.problem1926

/**
 * LeetCode page: [1926. Nearest Exit from Entrance in Maze](https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/);
 */
class Solution {
    /* Complexity:
     * Time O(|maze|) and Space O(|maze|);
     */
    fun nearestExit(maze: Array<CharArray>, entrance: IntArray): Int {
        var steps = 0
        val visited = newInitialVisitedTable(maze)
        val currStepPositions = ArrayDeque<IntArray>()

        currStepPositions.addLast(entrance)
        visited[entrance[0]][entrance[1]] = true

        while (currStepPositions.isNotEmpty()) {
            steps++

            repeat(currStepPositions.size) {
                val position = currStepPositions.removeFirst()
                val neighbours = getNeighbourPositions(position)

                for (neighbour in neighbours) {
                    if (isValidAndNonVisited(neighbour, maze, visited)) {
                        if (isAtBorder(neighbour, maze)) return steps
                        currStepPositions.addLast(neighbour)
                        visited[neighbour[0]][neighbour[1]] = true
                    }
                }
            }
        }

        return -1
    }

    private fun newInitialVisitedTable(maze: Array<CharArray>): List<BooleanArray> {
        return List(maze.size) { row ->
            BooleanArray(maze[row].size) { column -> maze[row][column] == '+' }
        }
    }

    private fun getNeighbourPositions(position: IntArray): List<IntArray> {
        return listOf(
            intArrayOf(position[0] - 1, position[1]),
            intArrayOf(position[0] + 1, position[1]),
            intArrayOf(position[0], position[1] - 1),
            intArrayOf(position[0], position[1] + 1)
        )
    }

    private fun isValidAndNonVisited(
        position: IntArray,
        maze: Array<CharArray>,
        visited: List<BooleanArray>
    ): Boolean {
        return !isOutOfRange(position, maze) && !visited[position[0]][position[1]]
    }

    private fun isOutOfRange(position: IntArray, maze: Array<CharArray>): Boolean {
        return position[0] !in maze.indices || position[1] !in maze[position[0]].indices
    }

    private fun isAtBorder(position: IntArray, maze: Array<CharArray>): Boolean {
        if (isOutOfRange(position, maze)) return false
        return position[0] == 0 ||
                position[0] == maze.lastIndex ||
                position[1] == 0 ||
                position[1] == maze[position[0]].lastIndex
    }
}