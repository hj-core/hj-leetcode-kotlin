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

        var top = -1 // Use robots as stack
        var i = 0
        while (i < n) {
            when {
                robots[i].isRight() || top < 0 || robots[top].isLeft() -> {
                    top++
                    robots[top] = robots[i]
                    i++
                }

                robots[top].health() < robots[i].health() -> {
                    top--
                    robots[i] = robots[i].decHealth()
                }

                robots[top].health() > robots[i].health() -> {
                    robots[top] = robots[top].decHealth()
                    i++
                }

                else -> {
                    top--
                    i++
                }
            }
        }

        val survived = top + 1
        val maxRobot = Robot.new(n, 0, 'L')
        for (i in survived..<n) {
            robots[i] = maxRobot
        }
        robots.sortBy { it.packed } // Sort by index

        return List(survived) { i -> robots[i].health() }
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
