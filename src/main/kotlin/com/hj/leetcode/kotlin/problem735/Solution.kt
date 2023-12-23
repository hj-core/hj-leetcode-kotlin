package com.hj.leetcode.kotlin.problem735

import kotlin.math.abs

/**
 * LeetCode page: [735. Asteroid Collision](https://leetcode.com/problems/asteroid-collision/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of asteroids;
     */
    fun asteroidCollision(asteroids: IntArray): IntArray {
        var newcomerIndex = 0
        val oldAsteroids = ArrayDeque<Int>()

        while (newcomerIndex < asteroids.size) {
            val newcomer = asteroids[newcomerIndex]
            if (oldAsteroids.isEmpty() || newcomer > 0 || oldAsteroids.last() < 0) {
                oldAsteroids.addLast(newcomer)
                newcomerIndex++
                continue
            }

            val lastOld = oldAsteroids.last()
            val newcomerSize = abs(newcomer)
            when {
                newcomerSize < lastOld -> newcomerIndex++
                newcomerSize > lastOld -> oldAsteroids.removeLast()
                else -> {
                    oldAsteroids.removeLast()
                    newcomerIndex++
                }
            }
        }
        return oldAsteroids.toIntArray()
    }
}