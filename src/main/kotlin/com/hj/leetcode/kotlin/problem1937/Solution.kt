package com.hj.leetcode.kotlin.problem1937

import kotlin.math.abs
import kotlin.math.max

/**
 * LeetCode page: [1937. Maximum Number of Points with Cost](https://leetcode.com/problems/maximum-number-of-points-with-cost/);
 */
class Solution {
    /* Complexity:
     * Time O(M*N^2) and Space O(N) where M is the number of rows in points
     * and N is the number of columns in points;
     */
    fun maxPoints(points: Array<IntArray>): Long {
        val dp = LongArray(points[0].size) { points[0][it].toLong() }
        for (r in 1..<points.size) {
            val prevDp = dp.clone()
            for (c in dp.indices) {
                for (c2 in prevDp.indices) {
                    dp[c] = max(dp[c], points[r][c] + prevDp[c2] - abs(c - c2))
                }
            }
        }
        return dp.max()
    }
}
