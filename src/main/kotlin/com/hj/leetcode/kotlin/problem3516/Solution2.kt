package com.hj.leetcode.kotlin.problem3516

import kotlin.math.abs

/**
 * LeetCode page: [3516. Find Closest Person](https://leetcode.com/problems/find-closest-person/);
 */
class Solution2 {
    // Complexity:
    // Time O(1) and Space O(1).
    fun findClosest(
        x: Int,
        y: Int,
        z: Int,
    ): Int {
        // (x-z)^2 - (y-z)^2
        val squareDiff = (x - y) * (x + y - 2 * z)
        return when {
            squareDiff < 0 -> 1
            squareDiff > 0 -> 2
            else -> 0
        }
    }
}
