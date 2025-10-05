package com.hj.leetcode.kotlin.problem417

/**
 * LeetCode page: [417. Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/);
 */
class Solution2 {
    // Complexity:
    // Time O(MN) and Space O(MN) where M and N are the number
    // of rows and columns in heights, respectively.
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        val m = heights.size
        val n = heights[0].size

        // isReachable[r][c]&1 is an indicator for the Pacific;
        // isReachable[r][c]&2 is an indicator for the Atlantic.
        val isReachable = Array(m) { IntArray(n) }
        val queue = mutableListOf<Int>() // elem = (r<<12) | c

        for (c in 0..<n) {
            isReachable[0][c] = isReachable[0][c] or 1
            queue.add(c)
        }

        for (c in 0..<n) {
            isReachable[m - 1][c] = isReachable[m - 1][c] or 2
            queue.add((m - 1) shl 12 or c)
        }

        for (r in 0..<m) {
            isReachable[r][0] = isReachable[r][0] or 1
            queue.add(r shl 12)

            isReachable[r][n - 1] = isReachable[r][n - 1] or 2
            queue.add((r shl 12) or (n - 1))
        }

        val moves = intArrayOf(0, 1, 0, -1, 0)
        while (queue.isNotEmpty()) {
            val cell = queue.removeLast()
            val r = cell shr 12
            val c = cell and 0xFFF

            for (dir in 0..<4) {
                val nr = r + moves[dir]
                val nc = c + moves[dir + 1]

                if (nr !in heights.indices || nc !in heights[nr].indices ||
                    heights[nr][nc] < heights[r][c] ||
                    isReachable[r][c] and isReachable[nr][nc] == isReachable[r][c]
                ) {
                    continue
                }

                isReachable[nr][nc] = isReachable[nr][nc] or isReachable[r][c]
                queue.add(nr shl 12 or nc)
            }
        }

        val result = mutableListOf<List<Int>>()
        for (r in 0..<m) {
            for (c in 0..<n) {
                if (isReachable[r][c] == 3) {
                    result.add(listOf(r, c))
                }
            }
        }
        return result
    }
}
