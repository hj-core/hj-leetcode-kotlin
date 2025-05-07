package com.hj.leetcode.kotlin.problem3341

import java.util.*

/**
 * LeetCode page: [3341. Find Minimum Time to Reach Last Room I](https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/);
 */
class Solution {
    val maxN = 50
    val maxM = 50
    val maxMoveTime = 1_000_000_000

    // Complexity:
    // Time O(NM*Log(NM)) and Space O(NM) where N and M are the number of rows
    // and columns of moveTime, respectively.
    fun minTimeToReach(moveTime: Array<IntArray>): Int {
        val infMoveTime = maxMoveTime + maxN + maxM
        val n = moveTime.size
        val m = moveTime[0].size

        val minTime = Array(n) { IntArray(m) { infMoveTime } }
        val pq = PriorityQueue<Cell>(compareBy(Cell::minTime))
        minTime[0][0] = 0
        pq.offer(Cell(0, 0, 0))

        while (pq.isNotEmpty()) {
            val curr = pq.poll()
            if (curr.row == n - 1 && curr.col == m - 1) {
                break
            }
            if (curr.minTime > minTime[curr.row][curr.col]) {
                continue
            }

            for (neighbour in getNeighbours(curr, n, m, minTime)) {
                val time = 1 + maxOf(curr.minTime, moveTime[neighbour.row][neighbour.col])
                if (time < neighbour.minTime) {
                    minTime[neighbour.row][neighbour.col] = time
                    neighbour.minTime = time
                    pq.add(neighbour)
                }
            }
        }
        return minTime[n - 1][m - 1]
    }

    private class Cell(
        val row: Int,
        val col: Int,
        var minTime: Int,
    )

    private fun getNeighbours(
        curr: Cell,
        n: Int,
        m: Int,
        minTime: Array<IntArray>,
    ): List<Cell> =
        buildList {
            if (curr.row > 0) {
                add(Cell(curr.row - 1, curr.col, minTime[curr.row - 1][curr.col]))
            }
            if (curr.row < n - 1) {
                add(Cell(curr.row + 1, curr.col, minTime[curr.row + 1][curr.col]))
            }
            if (curr.col > 0) {
                add(Cell(curr.row, curr.col - 1, minTime[curr.row][curr.col - 1]))
            }
            if (curr.col < m - 1) {
                add(Cell(curr.row, curr.col + 1, minTime[curr.row][curr.col + 1]))
            }
        }
}
