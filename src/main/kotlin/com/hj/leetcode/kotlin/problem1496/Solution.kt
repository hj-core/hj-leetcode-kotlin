package com.hj.leetcode.kotlin.problem1496

/**
 * LeetCode page: [1496. Path Crossing](https://leetcode.com/problems/path-crossing/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of path;
     */
    fun isPathCrossing(path: String): Boolean {
        val visited = hashSetOf<Point>()
        var currentPoint = Point(0, 0)
        visited.add(currentPoint)

        for (direction in path) {
            currentPoint = currentPoint.moved(direction)
            if (currentPoint in visited) {
                return true
            }
            visited.add(currentPoint)
        }
        return false
    }

    private data class Point(val x: Int, val y: Int) {
        fun moved(direction: Char): Point = when (direction) {
            'N' -> Point(x, y + 1)
            'S' -> Point(x, y - 1)
            'E' -> Point(x - 1, y)
            'W' -> Point(x + 1, y)
            else -> throw IllegalArgumentException()
        }
    }
}