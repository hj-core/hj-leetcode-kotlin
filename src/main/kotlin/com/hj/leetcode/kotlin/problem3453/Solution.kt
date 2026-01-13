package com.hj.leetcode.kotlin.problem3453

/**
 * LeetCode page: [3453. Separate Squares I](https://leetcode.com/problems/separate-squares-i/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of squares.
    fun separateSquares(squares: Array<IntArray>): Double {
        val totalArea = totalArea(squares)
        val widthChanges = sortedWidthChanges(squares)
        var minY = unpackYWidth(widthChanges[0]).first
        var width = 0L
        var accArea = 0L

        for (change in widthChanges) {
            val (y, dWidth) = unpackYWidth(change)
            if (y == minY) {
                width += dWidth
            } else {
                val dArea = width * (y - minY)
                if ((accArea + dArea) * 2 < totalArea) {
                    accArea += dArea
                    minY = y
                    width += dWidth
                } else {
                    break
                }
            }
        }

        val dArea = totalArea.toDouble() / 2 - accArea
        return minY + dArea / width
    }

    private fun totalArea(squares: Array<IntArray>): Long =
        squares.sumOf { (_, _, width) -> width * width.toLong() }

    private fun sortedWidthChanges(
        squares: Array<IntArray>,
    ): List<Long> =
        buildList(squares.size * 2) {
            for ((_, y, width) in squares) {
                add(packYWidth(y, width))
                add(packYWidth(y + width, -width))
            }
            sort()
        }

    private fun packYWidth(
        y: Int,
        width: Int,
    ): Long =
        if (width < 0) {
            (y.toLong() shl 32) or (1L shl 31) or (-width.toLong())
        } else {
            (y.toLong() shl 32) or width.toLong()
        }

    private fun unpackYWidth(packed: Long): Pair<Int, Int> {
        val y = (packed shr 32).toInt()
        val width =
            (packed and 0x7fffffff).toInt().let {
                val sign = (packed shr 31) and 1
                if (sign == 0L) it else -it
            }
        return y to width
    }
}
