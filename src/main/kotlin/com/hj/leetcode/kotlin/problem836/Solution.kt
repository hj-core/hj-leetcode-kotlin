package com.hj.leetcode.kotlin.problem836

/**
 * LeetCode page: [836. Rectangle Overlap](https://leetcode.com/problems/rectangle-overlap/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun isRectangleOverlap(
        rec1: IntArray,
        rec2: IntArray,
    ): Boolean =
        isIntervalOverlap(rec1[0], rec1[2], rec2[0], rec2[2]) &&
            isIntervalOverlap(rec1[1], rec1[3], rec2[1], rec2[3])

    private fun isIntervalOverlap(
        start1: Int,
        end1: Int,
        start2: Int,
        end2: Int,
    ): Boolean = maxOf(start1, start2) < minOf(end1, end2)
}
