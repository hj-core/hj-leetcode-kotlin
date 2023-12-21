package com.hj.leetcode.kotlin.problem1232

/**
 * LeetCode page: [1232. Check If It Is a Straight Line](https://leetcode.com/problems/check-if-it-is-a-straight-line/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of coordinates;
     */
    fun checkStraightLine(coordinates: Array<IntArray>): Boolean {
        val point1 = coordinates[0]
        val point2 = coordinates[1]
        return coordinates.all { point -> isStraightLine(point1, point2, point) }
    }

    private fun isStraightLine(point1: IntArray, point2: IntArray, point3: IntArray): Boolean {
        val (x1, y1) = point1
        val (x2, y2) = point2
        val (x3, y3) = point3
        return (x2 - x1) * (y3 - y1) == (y2 - y1) * (x3 - x1)
    }
}