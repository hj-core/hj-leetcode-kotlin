package com.hj.leetcode.kotlin.problem3342

import java.util.*

/**
 * LeetCode page: [3342. Find Minimum Time to Reach Last Room II](https://leetcode.com/problems/find-minimum-time-to-reach-last-room-ii/);
 */
class Solution {
    // Complexity:
    // Time O(NM*Log(NM)) and Space O(NM) where N and M are the number of
    // rows and columns of moveTime, respectively.
    fun minTimeToReach(moveTime: Array<IntArray>): Int {
        if (moveTime.size == 1 && moveTime[0].size == 1) {
            return 0
        }
        val moves = arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)
        val pq = PriorityQueue<Cell>(compareBy(Cell::time))
        val visited = Array(moveTime.size) { BooleanArray(moveTime[it].size) }

        pq.add(Cell(0, 0, 0))
        visited[0][0] = true
        while (true) {
            val curr = pq.poll()
            val moveCost = 1 + (1 and (curr.row xor curr.col))

            for ((dr, dc) in moves) {
                val neighRow = curr.row + dr
                val neighCol = curr.col + dc

                if (neighRow !in moveTime.indices || neighCol !in moveTime[neighRow].indices) {
                    continue
                }
                if (visited[neighRow][neighCol]) {
                    continue
                }

                val neighTime = moveCost + maxOf(curr.time, moveTime[neighRow][neighCol])
                if (neighRow == moveTime.lastIndex && neighCol == moveTime[curr.row].lastIndex) {
                    return neighTime
                }
                pq.offer(Cell(neighRow, neighCol, neighTime))
                visited[neighRow][neighCol] = true
            }
        }
    }

    private class Cell(
        val row: Int,
        val col: Int,
        val time: Int,
    )
}
