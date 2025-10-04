package com.hj.leetcode.kotlin.problem407

import java.util.*

private typealias Cell = IntArray // (r, c, h)

/**
 * LeetCode page: [407. Trapping Rain Water II](https://leetcode.com/problems/trapping-rain-water-ii/);
 */
class Solution {
    // Complexity:
    // Time O(MN Log(MN)) and Space O(MN) where M and N are the
    // number of rows and columns in heightMap, respectively.
    fun trapRainWater(heightMap: Array<IntArray>): Int {
        val m = heightMap.size
        val n = heightMap[0].size

        if (m < 3 || n < 3) {
            return 0
        }

        val pq = PriorityQueue<Cell>(compareBy { (_, _, h) -> h })
        val visited = Array(m) { BooleanArray(n) }

        walkEdges(heightMap) {
            val (r, c, _) = it
            pq.offer(it)
            visited[r][c] = true
        }

        var result = 0
        val moves = intArrayOf(0, 1, 0, -1, 0)

        while (pq.isNotEmpty()) {
            val (r, c, h) = pq.poll()
            result += h - heightMap[r][c]

            for (direction in 0..<4) {
                val newR = r + moves[direction]
                val newC = c + moves[direction + 1]
                if (newR !in 0..<m || newC !in 0..<n || visited[newR][newC]) {
                    continue
                }

                visited[newR][newC] = true
                val cell =
                    intArrayOf(
                        newR,
                        newC,
                        maxOf(h, heightMap[newR][newC]),
                    )
                pq.offer(cell)
            }
        }
        return result
    }

    private fun walkEdges(
        heightMap: Array<IntArray>,
        onEachCell: (Cell) -> Unit,
    ) {
        val m = heightMap.size
        val n = heightMap[0].size

        for (c in 0..<n) {
            onEachCell(intArrayOf(0, c, heightMap[0][c]))
        }

        for (r in 1..<m - 1) {
            onEachCell(intArrayOf(r, 0, heightMap[r][0]))
            onEachCell(intArrayOf(r, n - 1, heightMap[r][n - 1]))
        }

        for (c in 0..<n) {
            onEachCell(intArrayOf(m - 1, c, heightMap[m - 1][c]))
        }
    }
}
