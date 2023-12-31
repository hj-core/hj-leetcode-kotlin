package com.hj.leetcode.kotlin.problem1637

import kotlin.math.max

/**
 * LeetCode page: [1637. Widest Vertical Area Between Two Points Containing No Points](https://leetcode.com/problems/widest-vertical-area-between-two-points-containing-no-points/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of points;
     */
    fun maxWidthOfVerticalArea(points: Array<IntArray>): Int {
        val sortedByX = points.sortedBy { it[0] }
        var result = 0
        for (index in 1..<sortedByX.size) {
            val width = sortedByX[index][0] - sortedByX[index - 1][0]
            result = max(result, width)
        }
        return result
    }
}