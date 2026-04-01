package com.hj.leetcode.kotlin.problem2751

/**
 * LeetCode page: [2751. Robot Collisions](https://leetcode.com/problems/robot-collisions/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the number of robots.
    fun survivedRobotsHealths(
        positions: IntArray,
        healths: IntArray,
        directions: String,
    ): List<Int> {
        val n = positions.size
        val robots = Array(n) { i -> Robot.new(i, healths[i], directions[i]) }
        robots.sortBy { positions[it.index()] }

        val survived = mutableListOf<Robot>()
        var i = 0
        while (i < n) {
            when {
                robots[i].isRight() || survived.isEmpty() || survived.last().isLeft() -> {
                    survived.add(robots[i])
                    i++
                }

                survived.last().health() < robots[i].health() -> {
                    survived.removeLast()
                    robots[i] = robots[i].decHealth()
                }

                survived.last().health() > robots[i].health() -> {
                    survived[survived.lastIndex] = survived.last().decHealth()
                    i++
                }

                else -> {
                    survived.removeLast()
                    i++
                }
            }
        }

        survived.sortBy { it.packed } // Sort by index
        return survived.map(Robot::health)
    }

    @JvmInline
    private value class Robot(
        val packed: Long,
    ) {
        fun index(): Int = (this.packed shr INDEX_SHIFT).toInt()

        fun health(): Int = (this.packed and HEALTH_MASK).toInt()

        fun decHealth(): Robot = Robot(this.packed - 1L)

        fun isLeft(): Boolean = ((this.packed and DIR_MASK) shr DIR_SHIFT) == 0L

        fun isRight(): Boolean = !this.isLeft()

        companion object {
            private const val HEALTH_MASK: Long = 0x3fff_ffff
            private const val DIR_MASK: Long = 0xc000_0000
            private const val DIR_SHIFT: Int = 30
            private const val INDEX_SHIFT: Int = 32

            fun new(
                index: Int,
                health: Int,
                direction: Char,
            ): Robot {
                val dir = if (direction == 'L') 0L else 1L
                val packed =
                    (index.toLong() shl INDEX_SHIFT) or (dir shl DIR_SHIFT) or health.toLong()
                return Robot(packed)
            }
        }
    }
}
