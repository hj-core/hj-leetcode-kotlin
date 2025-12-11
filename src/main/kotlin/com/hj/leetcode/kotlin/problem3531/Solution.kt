package com.hj.leetcode.kotlin.problem3531

/**
 * LeetCode page: [3531. Count Covered Buildings](https://leetcode.com/problems/count-covered-buildings/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of buildings.
    fun countCoveredBuildings(
        n: Int,
        buildings: Array<IntArray>,
    ): Int {
        // xRange[y0]:= (minX, maxX) for points in y=y0
        val xRange = hashMapOf<Int, IntArray>()
        // yRange[x0]:= (minY, maxY) for points in x=x0
        val yRange = hashMapOf<Int, IntArray>()

        for ((x, y) in buildings) {
            if (y !in xRange) {
                xRange[y] = intArrayOf(x, x)
            } else {
                val range = checkNotNull(xRange[y])
                range[0] = minOf(range[0], x)
                range[1] = maxOf(range[1], x)
            }

            if (x !in yRange) {
                yRange[x] = intArrayOf(y, y)
            } else {
                val range = checkNotNull(yRange[x])
                range[0] = minOf(range[0], y)
                range[1] = maxOf(range[1], y)
            }
        }

        return buildings.count { (x, y) ->
            val (minX, maxX) = checkNotNull(xRange[y])
            if (x !in (minX + 1)..<maxX) {
                return@count false
            }
            val (minY, maxY) = checkNotNull(yRange[x])
            y in (minY + 1)..<maxY
        }
    }
}
