package com.hj.leetcode.kotlin.problem1301

/**
 * LeetCode page: [1301. Number of Paths with Max Score](https://leetcode.com/problems/number-of-paths-with-max-score/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of board.
    fun pathsWithMaxScore(board: List<String>): IntArray {
        val modulus = 1_000_000_007
        val n = board.size
        // dp[c]@r := {maximum score, path count} to reach cell (r, c).
        // A path count of -1 indicates that the cell is unreachable.
        val dp = Array(n + 1) { intArrayOf(0, -1) }

        // Base case: r = n - 1
        dp[n - 1][1] = 1
        for (c in n - 2 downTo 0) {
            if (board[n - 1][c] != 'X') {
                dp[c][0] = dp[c + 1][0] + (board[n - 1][c] - '0')
                dp[c][1] = dp[c + 1][1]
            }
        }

        // Remaining cases
        for (r in n - 2 downTo 0) {
            val prevNext = dp[n].clone() // dp[c+1]@r+1
            for (c in n - 1 downTo 0) {
                if (board[r][c] == 'X') {
                    dp[c].copyInto(prevNext)
                    dp[c][1] = -1
                    continue
                }

                // Borrow prevNext for storing the dp[c]@r
                if (prevNext[1] == -1) {
                    prevNext[0] = 0
                }
                for (src in arrayOf(dp[c], dp[c + 1])) {
                    if (src[1] == -1) {
                        continue
                    }
                    if (prevNext[0] < src[0]) {
                        prevNext[0] = src[0]
                        prevNext[1] = src[1]
                    } else if (prevNext[0] == src[0]) {
                        prevNext[1] = (prevNext[1].coerceAtLeast(0) + src[1]) % modulus
                    }
                }
                prevNext[0] += if (board[r][c] == 'E') 0 else board[r][c] - '0'
                dp[c][0] = prevNext[0].also { prevNext[0] = dp[c][0] }
                dp[c][1] = prevNext[1].also { prevNext[1] = dp[c][1] }
            }
        }

        return if (dp[0][1] == -1) intArrayOf(0, 0) else dp[0]
    }
}
