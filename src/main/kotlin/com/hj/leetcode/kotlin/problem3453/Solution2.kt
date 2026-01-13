package com.hj.leetcode.kotlin.problem3453

/**
 * LeetCode page: [3453. Separate Squares I](https://leetcode.com/problems/separate-squares-i/);
 */
class Solution2 {
    // Complexity:
    // Time O(NLog(R/E)) and Space O(1), where N is the length of
    // squares, R is the initial range of y required to enclose all
    // squares, and E is the precision.
    fun separateSquares(squares: Array<IntArray>): Double {
        val epsilon = 1e-5
        val halfTotalArea = totalArea(squares) / 2

        // Binary search for the minimum y, which lies in the range of
        // (low-step, high+step).
        var (low, high) = yRange(squares)
        val step = epsilon / 2
        while (low < high) {
            val mid = (low + high) / 2
            if (areaAboveLine(squares, mid) <= halfTotalArea) {
                high = mid - step
            } else {
                low = mid + step
            }
        }

        return (low + high) / 2
    }

    private fun totalArea(squares: Array<IntArray>): Double =
        squares
            .sumOf { (_, _, width) -> width * width.toLong() }
            .toDouble()

    private fun yRange(squares: Array<IntArray>): Pair<Double, Double> {
        var low = squares[0][1]
        var high = squares[0][1] + squares[0][2]
        for ((_, y, width) in squares) {
            low = minOf(low, y)
            high = maxOf(high, y + width)
        }

        return low.toDouble() to high.toDouble()
    }

    private fun areaAboveLine(
        squares: Array<IntArray>,
        yLine: Double,
    ): Double =
        squares.fold(0.0) { acc, square ->
            val (_, y, width) = square
            val top = y + width
            if (yLine < top) {
                acc + minOf(width.toDouble(), top - yLine) * width
            } else {
                acc
            }
        }
}
