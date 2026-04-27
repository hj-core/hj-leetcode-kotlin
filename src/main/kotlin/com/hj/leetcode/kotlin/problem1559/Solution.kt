package com.hj.leetcode.kotlin.problem1559

/**
 * LeetCode page: [1559. Detect Cycles in 2D Grid](https://leetcode.com/problems/detect-cycles-in-2d-grid/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(MN) where M and N are the number of rows and columns of grid.
    fun containsCycle(grid: Array<CharArray>): Boolean {
        val m = grid.size
        val n = grid[0].size
        val uf = UnionFind(m * n)

        for (c in 1..<n) {
            if (grid[0][c] == grid[0][c - 1] && !uf.union(c, c - 1)) {
                return true
            }
        }
        for (r in 1..<m) {
            if (grid[r][0] == grid[r - 1][0] && !uf.union(r * n, (r - 1) * n)) {
                return true
            }
            for (c in 1..<n) {
                if (grid[r][c] == grid[r - 1][c] && !uf.union(r * n + c, (r - 1) * n + c)) {
                    return true
                }
                if (grid[r][c] == grid[r][c - 1] && !uf.union(r * n + c, r * n + c - 1)) {
                    return true
                }
            }
        }

        return false
    }

    private class UnionFind(
        size: Int,
    ) {
        val parent = IntArray(size) { it }
        val rank = IntArray(size)

        fun find(x: Int): Int {
            if (parent[x] != x) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }

        fun union(
            x: Int,
            y: Int,
        ): Boolean {
            val rootX = find(x)
            val rootY = find(y)
            if (rootX == rootY) {
                return false
            }

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY
            } else {
                parent[rootY] = rootX
                rank[rootX]++
            }
            return true
        }
    }
}
