package com.hj.leetcode.kotlin.problem2257

private typealias Grid = Array<IntArray>

/**
 * LeetCode page: [2257. Count Unguarded Cells in the Grid](https://leetcode.com/problems/count-unguarded-cells-in-the-grid/);
 */
class Solution {
    // Complexity:
    // Time O(mn) and Space O(mn).
    fun countUnguarded(
        m: Int,
        n: Int,
        guards: Array<IntArray>,
        walls: Array<IntArray>,
    ): Int {
        // grid[r][c]:=
        // 0 if it is free,
        // 7 if there is a guard or wall,
        // bit 1 is set if it is watched from row direction,
        // bit 2 is set if it is watched from column direction.
        val grid = Array(m) { IntArray(n) }

        for ((r0, c0) in walls) {
            grid[r0][c0] = 7
        }

        for ((r0, c0) in guards) {
            grid[r0][c0] = 7

            for (r in r0 - 1 downTo 0) {
                if (grid[r][c0] and 1 != 0) {
                    break
                } else {
                    grid[r][c0] = grid[r][c0] xor 1
                }
            }

            for (r in r0 + 1..<m) {
                if (grid[r][c0] and 1 != 0) {
                    break
                } else {
                    grid[r][c0] = grid[r][c0] xor 1
                }
            }

            for (c in c0 - 1 downTo 0) {
                if (grid[r0][c] and 2 != 0) {
                    break
                } else {
                    grid[r0][c] = grid[r0][c] xor 2
                }
            }

            for (c in c0 + 1..<n) {
                if (grid[r0][c] and 2 != 0) {
                    break
                } else {
                    grid[r0][c] = grid[r0][c] xor 2
                }
            }
        }

        return grid.sumOf { it.count { v -> v == 0 } }
    }
}
