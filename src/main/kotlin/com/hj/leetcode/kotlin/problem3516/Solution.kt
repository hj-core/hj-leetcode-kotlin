package com.hj.leetcode.kotlin.problem3516

import kotlin.math.abs

/**
 * LeetCode page: [3516. Find Closest Person](https://leetcode.com/problems/find-closest-person/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun findClosest(
        x: Int,
        y: Int,
        z: Int,
    ): Int {
        val dx = abs(x - z)
        val dy = abs(y - z)
        return when {
            dx < dy -> 1
            dx > dy -> 2
            else -> 0
        }
    }
}
