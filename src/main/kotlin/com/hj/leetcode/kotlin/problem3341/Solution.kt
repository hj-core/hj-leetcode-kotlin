package com.hj.leetcode.kotlin.problem3341

import java.util.*

/**
 * LeetCode page: [3341. Find Minimum Time to Reach Last Room I](https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/);
 */
class Solution {
    // Complexity:
    // Time O(NM*Log(NM)) and Space O(NM) where N and M are the number of rows
    // and columns of moveTime, respectively.
    fun minTimeToReach(moveTime: Array<IntArray>): Int {
        val pq = PriorityQueue<Cell>(compareBy(Cell::minTime))
        val visited = Array(moveTime.size) { BooleanArray(moveTime[0].size) }
        val moves = arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

        pq.offer(Cell(0, 0, 0))
        while (pq.isNotEmpty()) {
            val curr = pq.poll()
            if (curr.row == moveTime.size - 1 && curr.col == moveTime[0].size - 1) {
                return curr.minTime
            }

            if (visited[curr.row][curr.col]) {
                continue
            }
            visited[curr.row][curr.col] = true

            for ((dr, dc) in moves) {
                val neighRow = curr.row + dr
                val neighCol = curr.col + dc
                if (neighRow !in moveTime.indices ||
                    neighCol !in moveTime[neighRow].indices ||
                    visited[neighRow][neighCol]
                ) {
                    continue
                }

                val neighTime = 1 + maxOf(curr.minTime, moveTime[neighRow][neighCol])
                pq.offer(Cell(neighRow, neighCol, neighTime))
            }
        }
        throw IllegalStateException("Code should not reach here")
    }

    private class Cell(
        val row: Int,
        val col: Int,
        val minTime: Int,
    )
}
