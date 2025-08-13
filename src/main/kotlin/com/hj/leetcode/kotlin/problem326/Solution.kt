package com.hj.leetcode.kotlin.problem326

/**
 * LeetCode page: [326. Power of Three](https://leetcode.com/problems/power-of-three/);
 */
class Solution {
    // Complexity:
    // Time O(Log n) and Space O(1).
    fun isPowerOfThree(n: Int): Boolean {
        if (n and 1 == 0) {
            return false
        }

        val n = n.toLong()
        var x = 1L
        while (x < n) {
            x += (x shl 1)
        }
        return x == n
    }
}
