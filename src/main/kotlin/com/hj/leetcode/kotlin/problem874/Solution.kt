package com.hj.leetcode.kotlin.problem874

import kotlin.math.max

/**
 * LeetCode page: [874. Walking Robot Simulation](https://leetcode.com/problems/walking-robot-simulation/);
 */
class Solution {
    /* Complexity:
     * Time O(kN+M) and Space O(M) where N is the size of commands, M is the size of obstacles
     * and k is the maximum movements in a single command.
     */
    fun robotSim(
        commands: IntArray,
        obstacles: Array<IntArray>,
    ): Int {
        val obstacleSet = obstacles.asSequence().map { Position(it[0], it[1]) }.toSet()
        var result = 0
        var position = Position(0, 0)
        var direction = Direction.North

        for (command in commands) {
            when (command) {
                -2 -> direction = direction.turnedLeft()
                -1 -> direction = direction.turnedRight()
                in 1..9 -> position = position.moved(direction, command, obstacleSet)
                else -> throw IllegalArgumentException("Invalid command")
            }
            result = max(result, position.squaredDistance())
        }
        return result
    }

    private data class Position(
        val x: Int,
        val y: Int,
    ) {
        fun moved(
            direction: Direction,
            steps: Int,
            obstacles: Set<Position>,
        ): Position {
            var result = this
            for (step in 1..steps) {
                val tryMove = result.moved(direction)
                if (tryMove in obstacles) {
                    break
                }
                result = tryMove
            }
            return result
        }

        private fun moved(direction: Direction): Position = Position(x + direction.dx, y + direction.dy)

        fun squaredDistance(): Int = x * x + y * y
    }

    private enum class Direction(
        val dx: Int,
        val dy: Int,
    ) {
        North(0, 1),
        South(0, -1),
        East(1, 0),
        West(-1, 0),
        ;

        fun turnedLeft(): Direction =
            when (this) {
                North -> West
                West -> South
                South -> East
                East -> North
            }

        fun turnedRight(): Direction =
            when (this) {
                North -> East
                East -> South
                South -> West
                West -> North
            }
    }
}
