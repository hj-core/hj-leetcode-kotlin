package com.hj.leetcode.kotlin.problem3025

/**
 * LeetCode page: [3025. Find the Number of Ways to Place People I](https://leetcode.com/problems/find-the-number-of-ways-to-place-people-i/);
 */
class Solution {
    private val zNoPoints = 0x7fff_ffff_ffff_ffff

    // Complexity:
    // Time O(NM) and Space O(M) where N is the length of points
    // and M is the maximum allowed x and y (i.e., 50).
    fun numberOfPairs(points: Array<IntArray>): Int {
        val xBound = 50
        val grid = LongArray(xBound + 1)
        for ((x, y) in points) {
            grid[x] = grid[x] or (1L shl y)
        }

        var result = 0
        for ((x0, y0) in points) {
            // A mask to clear the bits with y < y0
            val mask = ((1L shl y0) - 1) xor zNoPoints

            var minZ = calcZ(grid[x0] xor (1L shl y0), mask)
            if (minZ != zNoPoints) {
                result++
            }

            for (x1 in (x0 - 1) downTo 0) {
                val z = calcZ(grid[x1], mask)
                if (z < minZ) {
                    minZ = z
                    result++
                }
            }
        }
        return result
    }

    // Returns a value which is an indirect representation to
    // the LSB of (ys&mask), or Z_NO_POINTS if (ys&mask) is 0.
    //
    // # Example
    // ys   = 0x10c1_0000
    // mask = 0x0fff_0000
    // z    = 0x0001_1111
    private fun calcZ(
        ys: Long,
        mask: Long,
    ): Long {
        val a = ys and mask
        if (a == 0L) {
            return zNoPoints
        }
        return a xor (a - 1)
    }
}
