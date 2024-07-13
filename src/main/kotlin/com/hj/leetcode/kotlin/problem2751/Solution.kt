package com.hj.leetcode.kotlin.problem2751

/**
 * LeetCode page: [2751. Robot Collisions](https://leetcode.com/problems/robot-collisions/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the number of robots;
     */
    fun survivedRobotsHealths(positions: IntArray, healths: IntArray, directions: String): List<Int> {
        val sortedRobots = positions.indices.mapTo(mutableListOf()) { index ->
            Robot(index, positions[index], healths[index], directions[index])
        }.apply {
            sortBy { it.position }
        }

        val robotStack = mutableListOf<Robot>()
        for (robot in sortedRobots) {
            var winner = robot
            while (robotStack.isNotEmpty()
                && robotStack.last().direction == 'R'
                && winner.direction == 'L'
                && winner.health > 0
            ) {
                val opponent = robotStack.removeLast()
                winner = collide(opponent, winner)
            }
            if (winner.health > 0) {
                robotStack.add(winner)
            }
        }

        return robotStack
            .apply { sortBy { it.index } }
            .map { it.health }
    }

    private data class Robot(
        val index: Int,
        val position: Int,
        val health: Int,
        val direction: Char,
    )

    private fun collide(leftRobot: Robot, rightRobot: Robot): Robot {
        require(leftRobot.direction == 'R' && rightRobot.direction == 'L')

        return when {
            leftRobot.health < rightRobot.health -> {
                rightRobot.copy(health = rightRobot.health - 1)
            }

            leftRobot.health > rightRobot.health -> {
                leftRobot.copy(health = leftRobot.health - 1)
            }

            else -> leftRobot.copy(health = 0)
        }
    }
}