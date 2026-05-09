package com.hj.leetcode.kotlin.problem1914

/**
 * LeetCode page: [1914. Cyclically Rotating a Grid](https://leetcode.com/problems/cyclically-rotating-a-grid/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(1) where M and N are the number of rows and
    // columns of grid.
    fun rotateGrid(
        grid: Array<IntArray>,
        k: Int,
    ): Array<IntArray> {
        for (layer in 0..<totalLayers(grid)) {
            rotateLayer(grid, layer, k)
        }

        return grid
    }

    private fun totalLayers(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        return minOf(m, n) / 2
    }

    private fun rotateLayer(
        grid: Array<IntArray>,
        layer: Int,
        k: Int,
    ) {
        val size = layerSize(grid, layer)
        val k = k % size
        if (k == 0) {
            return
        }

        reverseLayer(grid, layer, 0..<k)
        reverseLayer(grid, layer, k..<size)
        reverseLayer(grid, layer, 0..<size)
    }

    private fun layerSize(
        grid: Array<IntArray>,
        layer: Int,
    ): Int {
        val m = grid.size
        val n = grid[0].size
        return 2 * (m + n - 2) - 8 * layer
    }

    private fun reverseLayer(
        grid: Array<IntArray>,
        layer: Int,
        indexRange: IntRange,
    ) {
        var left = indexRange.first
        var right = indexRange.last
        while (left < right) {
            swap(grid, layer, left, right)
            left++
            right--
        }
    }

    private fun swap(
        grid: Array<IntArray>,
        layer: Int,
        index1: Int,
        index2: Int,
    ) {
        val (r1, c1) = getRowColumn(grid, layer, index1)
        val (r2, c2) = getRowColumn(grid, layer, index2)
        grid[r1][c1] = grid[r2][c2].also { grid[r2][c2] = grid[r1][c1] }
    }

    private fun getRowColumn(
        grid: Array<IntArray>,
        layer: Int,
        index: Int,
    ): Pair<Int, Int> {
        val lm = grid.size - 2 * layer
        val ln = grid[0].size - 2 * layer
        val corners = intArrayOf(ln - 1, lm + ln - 2, ln * 2 + lm - 3, (lm + ln - 2) * 2)

        return when (index) {
            in 0..<corners[0] -> Pair(layer, layer + index)
            in corners[0]..<corners[1] -> Pair(layer + index - corners[0], layer + ln - 1)
            in corners[1]..<corners[2] -> Pair(layer + lm - 1, layer + corners[2] - index)
            in corners[2]..<corners[3] -> Pair(layer + corners[3] - index, layer)
            else -> throw IndexOutOfBoundsException()
        }
    }
}
