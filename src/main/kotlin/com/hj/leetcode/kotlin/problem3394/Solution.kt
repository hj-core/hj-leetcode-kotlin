package com.hj.leetcode.kotlin.problem3394

/**
 * LeetCode page: [3394. Check if Grid can be Cut into Sections](https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections/);
 */
class Solution {
    // Complexity:
    // Time O(MLogM) and Space O(M) where M is the length of rectangles.
    fun checkValidCuts(
        n: Int,
        rectangles: Array<IntArray>,
    ): Boolean {
        val xIntervals = rectangles.mapTo(mutableListOf()) { intArrayOf(it[0], it[2]) }
        xIntervals.sortBy { (start, end) -> start }
        if (canCutToThree(xIntervals)) {
            return true
        }

        val yIntervals = xIntervals
        for ((i, rectangle) in rectangles.withIndex()) {
            yIntervals[i][0] = rectangle[1]
            yIntervals[i][1] = rectangle[3]
        }
        yIntervals.sortBy { (start, end) -> start }
        return canCutToThree(yIntervals)
    }

    private fun canCutToThree(sortedIntervals: List<IntArray>): Boolean {
        var cuts = 0
        var currEnd = sortedIntervals[0][1]

        for ((nextStart, nextEnd) in sortedIntervals) {
            if (currEnd <= nextStart) {
                cuts++
                if (cuts == 2) {
                    return true
                }
            }
            currEnd = maxOf(currEnd, nextEnd)
        }
        return false
    }
}
