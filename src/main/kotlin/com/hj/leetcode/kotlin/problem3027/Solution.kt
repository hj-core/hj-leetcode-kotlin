package com.hj.leetcode.kotlin.problem3027

/**
 * LeetCode page: [3027. Find the Number of Ways to Place People II](https://leetcode.com/problems/find-the-number-of-ways-to-place-people-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of points.
    fun numberOfPairs(points: Array<IntArray>): Int {
        val sortedPoints =
            points.sortedArrayWith(
                compareBy({ it[0] }, { -it[1] }),
            )

        var result = 0
        for (i in 1..<sortedPoints.size) {
            val y0 = sortedPoints[i][1]
            var minY = Int.MAX_VALUE

            for (j in i - 1 downTo 0) {
                val y1 = sortedPoints[j][1]
                if (y1 in y0..<minY) {
                    minY = y1
                    result++
                }
            }
        }
        return result
    }
}
