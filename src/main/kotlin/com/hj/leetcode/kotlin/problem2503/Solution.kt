package com.hj.leetcode.kotlin.problem2503

import java.util.TreeSet

/**
 * LeetCode page: [2503. Maximum Number of Points From Grid Queries](https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/);
 */
class Solution {
    fun maxPoints(
        grid: Array<IntArray>,
        queries: IntArray,
    ): IntArray =
        if (grid.size * grid[0].size < queries.size) {
            solverForManyQueries(grid, queries)
        } else {
            solverForFewQueries(grid, queries)
        }

    // Complexity:
    // Time O((N+Q)*LogN) and Space O(N+Q) where N is the flattened length of grid and Q is the length of queries.
    private fun solverForManyQueries(
        grid: Array<IntArray>,
        queries: IntArray,
    ): IntArray {
        val sortedMinQueries = sortedMinQueries(grid).toList()

        return IntArray(queries.size) { i ->
            partition(sortedMinQueries) { elem -> elem <= queries[i] }
        }
    }

    /**
     * `sortedMinQueries` returns the minimum required query to reach each cell in sorted order.
     */
    private fun sortedMinQueries(grid: Array<IntArray>): Sequence<Int> =
        sequence {
            val result = Array(grid.size) { IntArray(grid[it].size) { -1 } }
            val pq = TreeSet(compareBy({ result[it.row][it.col] }, Cell::row, Cell::col))
            val moves =
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 0),
                    intArrayOf(0, -1),
                    intArrayOf(-1, 0),
                )

            result[0][0] = grid[0][0] + 1
            pq.add(Cell(0, 0))

            while (pq.isNotEmpty()) {
                val (row, col) = pq.pollFirst()!!
                yield(result[row][col])

                for ((dr, dc) in moves) {
                    val nextRow = row + dr
                    val nextCol = col + dc

                    if (nextRow !in grid.indices || nextCol !in grid[nextRow].indices) {
                        continue
                    }
                    val queryToNext = maxOf(result[row][col], grid[nextRow][nextCol] + 1)

                    if (result[nextRow][nextCol] == -1) {
                        result[nextRow][nextCol] = queryToNext
                        pq.add(Cell(nextRow, nextCol))
                    } else if (result[nextRow][nextCol] > queryToNext) {
                        pq.remove(Cell(nextRow, nextCol))
                        result[nextRow][nextCol] = queryToNext
                        pq.add(Cell(nextRow, nextCol))
                    }
                }
            }
        }

    private data class Cell(
        val row: Int,
        val col: Int,
    )

    /**
     * `partition` returns the number of elements in `nums` that satisfy `isLeft`.
     *
     * Clients must ensure `nums` consists of elements that satisfy `isLeft` followed the elements that don't.
     */
    private fun partition(
        nums: List<Int>,
        isLeft: (elem: Int) -> Boolean,
    ): Int {
        // Binary search on the last index of nums that satisfies isLeft.
        // The result is in range [left-1, right].
        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = (left + right) ushr 1
            if (isLeft(nums[mid])) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
    }

    // Complexity:
    // Time O(NLogN+QLogQ) and Space O(N+Q) where N is the flattened length of grid and Q is the length of queries.
    private fun solverForFewQueries(
        grid: Array<IntArray>,
        queries: IntArray,
    ): IntArray {
        val result = IntArray(queries.size)
        val sortedQIndices = queries.indices.sortedBy { queries[it] }

        var i = 0 // index of sortedQIndices
        var componentSize = 0

        for (minQuery in sortedMinQueries(grid)) {
            while (i < sortedQIndices.size && queries[sortedQIndices[i]] < minQuery) {
                result[sortedQIndices[i]] = componentSize
                i++
            }
            componentSize++

            if (i == sortedQIndices.size) {
                break
            }
        }

        while (i < sortedQIndices.size) {
            result[sortedQIndices[i]] = componentSize
            i++
        }
        return result
    }
}
