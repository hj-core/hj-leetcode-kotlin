package com.hj.leetcode.kotlin.problem1937

import kotlin.math.max

/**
 * LeetCode page: [1937. Maximum Number of Points with Cost](https://leetcode.com/problems/maximum-number-of-points-with-cost/);
 */
class Solution {
    /* Complexity:
     * Time O(M*N) and Space O(N) where M is the number of rows in points
     * and N is the number of columns in points;
     */
    fun maxPoints(points: Array<IntArray>): Long {
        /* dp[c]@r ::= the maximum points when reaches cell(r,c), including
         * the points for the cell;
         */
        val dp = LongArray(points[0].size) { points[0][it].toLong() } // dp[c]@r=0
        for (r in 1..<points.size) {
            val prevDp = dp.clone()
            // Case that the best previous cell is right above or to the left
            var leftBest = prevDp[0]
            for (c in dp.indices) {
                leftBest = max(leftBest - 1, prevDp[c])
                dp[c] = max(dp[c], points[r][c] + leftBest)
            }
            // Case that the best previous cell is right above or to the right
            var rightBest = prevDp.last()
            for (c in dp.indices.reversed()) {
                rightBest = max(rightBest - 1, prevDp[c])
                dp[c] = max(dp[c], points[r][c] + rightBest)
            }
        }
        return dp.max()
    }
}
