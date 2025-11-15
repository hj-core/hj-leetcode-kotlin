package com.hj.leetcode.kotlin.problem2536

/**
 * LeetCode page: [2536. Increment Submatrices by One](https://leetcode.com/problems/increment-submatrices-by-one/);
 */
class Solution {
    // Complexity:
    // Time O(n^2+M) and Space O(n^2) where M is the length of
    // queries.
    fun rangeAddQueries(
        n: Int,
        queries: Array<IntArray>,
    ): Array<IntArray> {
        val result = Array(n) { IntArray(n) }

        // 2D sweep line
        for ((r1, c1, r2, c2) in queries) {
            result[r1][c1]++
            if (r2 + 1 < n) {
                result[r2 + 1][c1]--
                if (c2 + 1 < n) {
                    result[r1][c2 + 1]--
                    result[r2 + 1][c2 + 1]++
                }
            } else if (c2 + 1 < n) {
                result[r1][c2 + 1]--
            }
        }

        // Prefix sum to compute the final array
        for (i in 1..<n) {
            result[0][i] += result[0][i - 1]
        }
        for (i in 1..<n) {
            result[i][0] += result[i - 1][0]
            for (j in 1..<n) {
                result[i][j] +=
                    result[i - 1][j] + result[i][j - 1] -
                    result[i - 1][j - 1]
            }
        }
        return result
    }
}
