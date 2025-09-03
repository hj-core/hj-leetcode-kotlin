package com.hj.leetcode.kotlin.problem3027

/**
 * LeetCode page: [3027. Find the Number of Ways to Place People II](https://leetcode.com/problems/find-the-number-of-ways-to-place-people-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of points.
    fun numberOfPairs(points: Array<IntArray>): Int {
        val sorted = points.sortedArrayWith(compareBy({ it[0] }, { -it[1] }))
        var result = 0
        for (i in 1..<sorted.size) {
            var minY = Int.MAX_VALUE
            for (j in i - 1 downTo 0) {
                if (sorted[j][1] in sorted[i][1]..<minY) {
                    minY = sorted[j][1]
                    result++
                }
            }
        }
        return result
    }
}
