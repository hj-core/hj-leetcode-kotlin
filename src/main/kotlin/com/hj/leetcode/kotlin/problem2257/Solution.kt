package com.hj.leetcode.kotlin.problem2257

private typealias Grid = Array<IntArray>

/**
 * LeetCode page: [2257. Count Unguarded Cells in the Grid](https://leetcode.com/problems/count-unguarded-cells-in-the-grid/);
 */
class Solution {
    private val free = 0
    private val wall = 1
    private val guard = 2
    private val guardedHorizontally = 3
    private val guardedVertically = 4

    /* Complexity:
     * Time O(mn) and Space O(mn).
     */
    fun countUnguarded(
        m: Int,
        n: Int,
        guards: Array<IntArray>,
        walls: Array<IntArray>,
    ): Int =
        newGrid(m, n).run {
            installWalls(walls)
            installGuards(guards)
            markGuardedCells(guards)
            countFreeCells()
        }

    private fun newGrid(
        m: Int,
        n: Int,
    ): Grid = Array(m) { IntArray(n) { free } }

    private fun Grid.installWalls(walls: Array<IntArray>) {
        for ((r, c) in walls) {
            this[r][c] = wall
        }
    }

    private fun Grid.installGuards(guards: Array<IntArray>) {
        for ((r, c) in guards) {
            this[r][c] = guard
        }
    }

    private fun Grid.markGuardedCells(guards: Array<IntArray>) {
        for (guard in guards) {
            markHorizontallyGuarded(guard)
        }
        for (guard in guards) {
            markVerticallyGuarded(guard)
        }
    }

    private fun Grid.markHorizontallyGuarded(guard: IntArray) {
        val (r, c) = guard
        for (r2 in r - 1 downTo 0) {
            if (!tryGuardedHorizontally(r2, c)) break
        }
        for (r2 in r + 1..<this.size) {
            if (!tryGuardedHorizontally(r2, c)) break
        }
    }

    private fun Grid.tryGuardedHorizontally(
        row: Int,
        col: Int,
    ): Boolean {
        if (this[row][col] == free || this[row][col] == guardedVertically) {
            this[row][col] = guardedHorizontally
            return true
        }
        return false
    }

    private fun Grid.markVerticallyGuarded(guard: IntArray) {
        val (r, c) = guard
        for (c2 in c - 1 downTo 0) {
            if (!tryGuardedVertically(r, c2)) break
        }
        for (c2 in c + 1..<this[r].size) {
            if (!tryGuardedVertically(r, c2)) break
        }
    }

    private fun Grid.tryGuardedVertically(
        row: Int,
        col: Int,
    ): Boolean {
        if (this[row][col] == free || this[row][col] == guardedHorizontally) {
            this[row][col] = guardedVertically
            return true
        }
        return false
    }

    private fun Grid.countFreeCells(): Int = sumOf { row -> row.count { it == free } }
}
