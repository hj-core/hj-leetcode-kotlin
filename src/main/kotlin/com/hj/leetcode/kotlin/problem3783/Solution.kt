package com.hj.leetcode.kotlin.problem3783

import kotlin.math.abs

/**
 * LeetCode page: [3783. Mirror Distance of an Integer](https://leetcode.com/problems/mirror-distance-of-an-integer/);
 */
class Solution {
    // Complexity:
    // Time O(Log n) and Space O(1).
    fun mirrorDistance(n: Int): Int = abs(n - reverse(n))

    private fun reverse(n: Int): Int {
        var reversed = 0
        var x = n
        while (x > 0) {
            reversed = reversed * 10 + x % 10
            x /= 10
        }
        return reversed
    }
}
