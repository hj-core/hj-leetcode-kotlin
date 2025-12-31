package com.hj.leetcode.kotlin.problem1970

/**
 * LeetCode page: [1970. Last Day Where You Can Still Cross](https://leetcode.com/problems/last-day-where-you-can-still-cross/);
 */
class Solution {
    // Complexity:
    // Time O(row*col) and Space O(row*col).
    fun latestDayToCross(
        row: Int,
        col: Int,
        cells: Array<IntArray>,
    ): Int {
        // The unified nodes for column 0 and column col+1
        val posLeft = 0
        val posRight = row * col + 1

        val uf = UnionFind(posRight + 1)
        uf.markWater(posLeft)
        uf.markWater(posRight)

        for ((prevDay, cell) in cells.withIndex()) {
            val (r, c) = cell
            val pos = positionNumber(r, c, col)

            uf.markWater(pos)

            for (dr in -1..1) {
                val adjR = r + dr
                if (adjR !in 1..row) {
                    continue
                }

                for (dc in -1..1) {
                    val adjC = c + dc
                    when {
                        adjC < 1 -> {
                            uf.union(pos, posLeft)
                        }

                        adjC > col -> {
                            uf.union(pos, posRight)
                        }

                        else -> {
                            val adjPos =
                                positionNumber(adjR, adjC, col)
                            if (uf.isWater(adjPos)) {
                                uf.union(pos, adjPos)
                            }
                        }
                    }
                }
            }

            if (uf.isConnected(posLeft, posRight)) {
                return prevDay
            }
        }

        return cells.size - 1
    }

    private fun positionNumber(
        r: Int,
        c: Int,
        col: Int,
    ): Int = c + (r - 1) * col

    private class UnionFind(
        size: Int,
    ) {
        private val parent = IntArray(size) { it }
        private val rank = IntArray(size)

        fun markWater(x: Int) {
            if (rank[x] == 0) {
                rank[x] = 1
            }
        }

        fun isWater(x: Int): Boolean = rank[x] > 0

        fun find(x: Int): Int {
            if (parent[x] != x) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }

        fun union(
            x: Int,
            y: Int,
        ) {
            val rootX = find(x)
            val rootY = find(y)

            if (rootX == rootY) {
                return
            }

            val rankDiff = rank[rootX] - rank[rootY]
            when {
                rankDiff < 0 -> {
                    parent[rootX] = parent[rootY]
                }

                rankDiff > 0 -> {
                    parent[rootY] = parent[rootX]
                }

                else -> {
                    parent[rootY] = rootX
                    rank[rootX]++
                }
            }
        }

        fun isConnected(
            x: Int,
            y: Int,
        ): Boolean = find(x) == find(y)
    }
}
