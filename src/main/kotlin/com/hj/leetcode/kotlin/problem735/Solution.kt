package com.hj.leetcode.kotlin.problem735

/**
 * LeetCode page: [735. Asteroid Collision](https://leetcode.com/problems/asteroid-collision/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of asteroids;
     */
    fun asteroidCollision(asteroids: IntArray): IntArray {
        val coexistAsteroids = ArrayDeque<Int>()
        for (asteroid in asteroids) {
            if (coexistAsteroids.isEmpty() || canCoexist(coexistAsteroids.last(), asteroid)) {
                coexistAsteroids.addLast(asteroid)
                continue
            }

            var remainingAsteroid: Int? = asteroid
            while (coexistAsteroids.isNotEmpty()
                && remainingAsteroid != null
                && cannotCoexist(coexistAsteroids.last(), remainingAsteroid)
            ) {
                val previousAsteroid = coexistAsteroids.removeLast()
                remainingAsteroid = collisionResult(previousAsteroid, remainingAsteroid)
            }
            remainingAsteroid?.let { coexistAsteroids.addLast(it) }
        }
        return coexistAsteroids.toIntArray()
    }

    private fun collisionResult(asteroid: Int, newAsteroid: Int): Int? = when {
        size(newAsteroid) < size(asteroid) -> asteroid
        size(newAsteroid) > size(asteroid) -> newAsteroid
        else -> null
    }

    private fun canCoexist(asteroid: Int, newAsteroid: Int): Boolean {
        return !cannotCoexist(asteroid, newAsteroid)
    }

    private fun cannotCoexist(asteroid: Int, newAsteroid: Int): Boolean {
        return asteroid > 0 && newAsteroid < 0
    }

    private fun size(asteroid: Int): Int = Math.abs(asteroid)
}