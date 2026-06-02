package com.hj.leetcode.kotlin.problem2126

/**
 * LeetCode page: [2126. Destroying Asteroids](https://leetcode.com/problems/destroying-asteroids/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of asteroids.
    fun asteroidsDestroyed(
        mass: Int,
        asteroids: IntArray,
    ): Boolean {
        var currMass = mass.toLong()
        for (m in asteroids.sortedArray()) {
            if (currMass >= m) {
                currMass += m
            } else {
                return false
            }
        }

        return true
    }
}
