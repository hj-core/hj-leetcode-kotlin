package com.hj.leetcode.kotlin.problem2849

import kotlin.math.abs
import kotlin.math.max

/**
 * LeetCode page: [2849. Determine if a Cell Is Reachable at a Given Time](https://leetcode.com/problems/determine-if-a-cell-is-reachable-at-a-given-time/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun isReachableAtTime(sx: Int, sy: Int, fx: Int, fy: Int, t: Int): Boolean {
        val minT = minT(sx, sy, fx, fy)
        return when {
            t < minT -> false
            t == minT + 1 -> minT > 0
            else -> true
        }
    }

    private fun minT(sx: Int, sy: Int, fx: Int, fy: Int): Int {
        return max(abs(fx - sx), abs(fy - sy))
    }
}