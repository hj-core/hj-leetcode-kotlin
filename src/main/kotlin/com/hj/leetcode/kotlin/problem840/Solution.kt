package com.hj.leetcode.kotlin.problem840

/**
 * LeetCode page: [840. Magic Squares In Grid](https://leetcode.com/problems/magic-squares-in-grid/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(1) where M and N are the number of
    // rows and columns in grid, respectively.
    fun numMagicSquaresInside(grid: Array<IntArray>): Int =
        (1..<grid.size - 1).sumOf { r0 ->
            (1..<grid[r0].size - 1).count { c0 ->
                isMagicSquare(grid, r0, c0)
            }
        }

    // isMagicSquare returns whether the square center at (r0, c0)
    // is magic.
    private fun isMagicSquare(
        grid: Array<IntArray>,
        r0: Int,
        c0: Int,
    ): Boolean {
        // We use the following criteria:
        // - The central element must be 5.
        // - Elements adjacent to the center must be odd.
        // - The numbers 1–9 must appear exactly once.
        // - Each row must sum to 15.
        // - Each column must sum to 15.
        if (grid[r0][c0] != 5 || grid[r0][c0 - 1] and 1 == 0) {
            return false
        }

        var mask = 0L
        for (dr in 0..<3) {
            val r = r0 + dr - 1
            for (dc in 0..<3) {
                val c = c0 + dc - 1
                val v = grid[r][c].toLong()

                // rowSum + colSum + seen
                mask +=
                    (v shl (dr shl 3)) +
                    (v shl (24 + (dc shl 3))) +
                    (1L shl (48 + v).toInt())
            }
        }

        return mask == 0x3FE_0F0F0F_0F0F0F
    }
}
