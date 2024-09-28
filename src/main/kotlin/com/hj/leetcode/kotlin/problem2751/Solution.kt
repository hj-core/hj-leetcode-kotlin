package com.hj.leetcode.kotlin.problem2751

/**
 * LeetCode page: [2751. Robot Collisions](https://leetcode.com/problems/robot-collisions/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the number of robots;
     */
    fun survivedRobotsHealths(positions: IntArray, healths: IntArray, directions: String): List<Int> {
        val remainingRobots = robotsAtStart(positions, healths, directions)
        collideAll(remainingRobots)
        remainingRobots.sortBy { it.index }
        return remainingRobots.map { it.health }
    }

    private fun robotsAtStart(
        positions: IntArray,
        healths: IntArray,
        directions: String,
    ): MutableList<Robot> = positions.indices.mapTo(mutableListOf()) { index ->
        Robot(index, positions[index], healths[index], directions[index])
    }

    private data class Robot(
        val index: Int,
        val position: Int,
        val health: Int,
        val direction: Char,
    )

    private fun collideAll(robots: MutableList<Robot>) {
        robots.sortBy { it.position }
        // Use robots in-place as stack and perform all collisions
        var stackPtr = -1
        for (robot in robots) {
            var winner = robot
            while (stackPtr >= 0
                && robots[stackPtr].direction == 'R'
                && winner.direction == 'L'
            ) {
                val challenger = robots[stackPtr]
                stackPtr--
                winner = collide(challenger, winner)
            }
            if (winner.health > 0) {
                stackPtr++
                robots[stackPtr] = winner
            }
        }
        // Pop the robots above stackPtr
        repeat(robots.lastIndex - stackPtr) {
            robots.removeLast()
        }
    }

    private fun collide(left: Robot, right: Robot): Robot {
        require(left.direction == 'R' && right.direction == 'L')
        return when {
            left.health > right.health -> left.copy(health = left.health - 1)
            left.health < right.health -> right.copy(health = right.health - 1)
            else -> left.copy(health = 0)
        }
    }
}