package com.hj.leetcode.kotlin.problem3454

/**
 * LeetCode page: [3454. Separate Squares II](https://leetcode.com/problems/separate-squares-ii/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of squares.
    fun separateSquares(squares: Array<IntArray>): Double {
        val widthChange = sortedWidthChanges(squares)
        val tree = SegmentTree(sortedXs(squares))

        var minY = widthChange[0][0]
        var accArea = 0L
        // A history of (y, merged_width_right_under_y, area_under_y)
        val history = mutableListOf<LongArray>()

        for ((y, x, dWidth) in widthChange) {
            if (y != minY) {
                val mergedWidth = tree.mergedWidth().toLong()
                accArea += mergedWidth * (y - minY)
                history.add(
                    longArrayOf(y.toLong(), mergedWidth, accArea),
                )

                minY = y
            }

            tree.applyChange(x, dWidth)
        }

        val totalArea = history.last()[2]
        for ((y, mergedWidth, accArea) in history) {
            if (accArea * 2 >= totalArea) {
                return y.toDouble() -
                    (accArea - totalArea.toDouble() / 2) /
                    mergedWidth
            }
        }
        throw IllegalStateException("Unreachable since y must exist")
    }

    // Returns a list of (y0, x0, dWidth) sorted by y in non-descending
    // order. A negative dWidth indicates the removal of the interval
    // [x0, x0 - dWidth] at the horizontal line y = y0.
    private fun sortedWidthChanges(
        squares: Array<IntArray>,
    ): Array<IntArray> {
        val changes =
            Array(squares.size * 2) { i ->
                val (x, y, width) = squares[i shr 1]

                if (i and 1 == 0) {
                    intArrayOf(y, x, width)
                } else {
                    intArrayOf(y + width, x, -width)
                }
            }
        changes.sortBy { it[0] }

        return changes
    }

    // Returns a deduplicated list of all relevant xs in non-descending
    // order. Note that there are at most 2*squares.size of xs we cared
    // about, i.e., x and x+width from each square.
    private fun sortedXs(squares: Array<IntArray>): IntArray {
        val xs = hashSetOf<Int>()
        for ((x, _, width) in squares) {
            xs.add(x)
            xs.add(x + width)
        }

        return xs.toIntArray().apply { sort() }
    }
}

// A segment tree customized for this problem. Each tree node is a
// (range_addition, min_element_reported_from_child, min_element_count)
// tuple.
//
// Ref: https://cp-algorithms.com/data_structures/segment_tree.html
private class SegmentTree(
    private val sortedXs: IntArray,
) {
    private val n = sortedXs.size - 1

    // root is at index 1.
    private val nodes = Array(n * 4) { IntArray(3) }

    private val xToIndex = xToIndex(sortedXs)
    private val fullWidth = sortedXs[n] - sortedXs[0]

    // Returns a map of (x, index_of_x_in_sortedXs).
    private fun xToIndex(sortedXs: IntArray): Map<Int, Int> =
        sortedXs.withIndex().associate { (i, x) -> x to i }

    init {
        build(1, 0, n - 1)
    }

    private fun build(
        v: Int,
        tl: Int,
        tr: Int,
    ) {
        if (tl == tr) {
            nodes[v][2] = sortedXs[tl + 1] - sortedXs[tl]
        } else {
            val tm = (tl + tr) ushr 1
            build(v * 2, tl, tm)
            build(v * 2 + 1, tm + 1, tr)
            fix(v)
        }
    }

    // Fixes the nodes[v] to reflect the changes to its child noes.
    private fun fix(v: Int) {
        val lChild = nodes[v * 2]
        val rChild = nodes[v * 2 + 1]

        val lMin = lChild[0] + lChild[1]
        val rMin = rChild[0] + rChild[1]
        when {
            lMin < rMin -> {
                nodes[v][1] = lMin
                nodes[v][2] = lChild[2]
            }

            lMin > rMin -> {
                nodes[v][1] = rMin
                nodes[v][2] = rChild[2]
            }

            else -> {
                nodes[v][1] = lMin
                nodes[v][2] = lChild[2] + rChild[2]
            }
        }
    }

    // Lazily adds the addend over nodes[l..=r].
    private fun rangeAdd(
        v: Int,
        tl: Int,
        tr: Int,
        l: Int,
        r: Int,
        addend: Int,
    ) {
        if (l > r) {
            return
        }

        if (l == tl && r == tr) {
            nodes[v][0] += addend
        } else {
            val tm = (tl + tr) ushr 1

            rangeAdd(
                v * 2,
                tl,
                tm,
                l,
                minOf(r, tm),
                addend,
            )

            rangeAdd(
                v * 2 + 1,
                tm + 1,
                tr,
                maxOf(l, tm + 1),
                r,
                addend,
            )

            fix(v)
        }
    }

    fun applyChange(
        x: Int,
        dWidth: Int,
    ) {
        val l = checkNotNull(xToIndex[x])
        if (dWidth < 0) {
            val r = checkNotNull(xToIndex[x - dWidth]) - 1
            rangeAdd(1, 0, n - 1, l, r, -1)
        } else {
            val r = checkNotNull(xToIndex[x + dWidth]) - 1
            rangeAdd(1, 0, n - 1, l, r, 1)
        }
    }

    fun mergedWidth(): Int =
        if (nodes[1][0] + nodes[1][1] == 0) {
            fullWidth - nodes[1][2]
        } else {
            fullWidth
        }
}
