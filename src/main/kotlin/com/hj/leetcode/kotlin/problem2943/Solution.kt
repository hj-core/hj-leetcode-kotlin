package com.hj.leetcode.kotlin.problem2943

/**
 * LeetCode page: [2943. Maximize Area of Square Hole in Grid](https://leetcode.com/problems/maximize-area-of-square-hole-in-grid/);
 */
class Solution {
    // Complexity:
    // Time O(HLogH+VLogV) and Space O(H+V), where H and V are the
    // length of hBars and vBars, respectively.
    fun maximizeSquareHoleArea(
        n: Int,
        m: Int,
        hBars: IntArray,
        vBars: IntArray,
    ): Int {
        val hMaxSpacing = maxSpacing(hBars)
        val vMaxSpacing = maxSpacing(vBars)
        val width = minOf(hMaxSpacing, vMaxSpacing)
        return width * width
    }

    // Returns the maximum 1D spacing if all bars are removed.
    private fun maxSpacing(bars: IntArray): Int {
        if (bars.isEmpty()) {
            return 1
        }

        val sortedBars = bars.sortedArray()
        var prevBar = sortedBars[0]
        var spacing = 0
        var maxSpacing = 2

        for (bar in sortedBars) {
            if (bar == prevBar + 1) {
                spacing++
                maxSpacing = maxOf(maxSpacing, spacing)
            } else {
                spacing = 2
            }
            prevBar = bar
        }

        return maxSpacing
    }
}
