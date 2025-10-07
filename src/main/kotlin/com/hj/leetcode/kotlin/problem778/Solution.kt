package com.hj.leetcode.kotlin.problem778

/**
 * LeetCode page: [778. Swim in Rising Water](https://leetcode.com/problems/swim-in-rising-water/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N^2) where N is the number of
    // rows and columns in grid.
    fun swimInWater(grid: Array<IntArray>): Int {
        val n = grid.size

        val cellMap = IntArray(n * n) // cellMap[grid[r][c]]:= (r<<12) | c
        for (r in 0..<n) {
            for (c in 0..<n) {
                cellMap[grid[r][c]] = r shl 12 or c
            }
        }

        val uf = UnionFind(n * n)
        var t = 0

        while (uf.find(grid[n - 1][n - 1]) != uf.find(grid[0][0])) {
            t++
            val r = cellMap[t] shr 12
            val c = cellMap[t] and 0xFFF

            if (c > 0 && grid[r][c - 1] <= t) {
                uf.union(grid[r][c], grid[r][c - 1])
            }
            if (c < n - 1 && grid[r][c + 1] <= t) {
                uf.union(grid[r][c], grid[r][c + 1])
            }
            if (r > 0 && grid[r - 1][c] <= t) {
                uf.union(grid[r][c], grid[r - 1][c])
            }
            if (r < n - 1 && grid[r + 1][c] <= t) {
                uf.union(grid[r][c], grid[r + 1][c])
            }
        }
        return t
    }

    private class UnionFind(
        n: Int,
    ) {
        private val parent = IntArray(n) { it }
        private val rank = IntArray(n)

        fun find(x: Int): Int {
            var p = x
            while (parent[p] != p) {
                p = parent[p]
            }

            var y = x
            while (parent[y] != y) {
                parent[y] = p.also { y = parent[y] }
            }
            return p
        }

        fun union(
            x: Int,
            y: Int,
        ) {
            val xp = find(x)
            val yp = find(y)

            if (xp == yp) {
                return
            }

            when {
                rank[xp] < rank[yp] -> parent[xp] = yp
                rank[xp] > rank[yp] -> parent[yp] = xp
                else -> parent[yp] = xp.also { rank[xp]++ }
            }
        }
    }
}
