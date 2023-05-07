package com.hj.leetcode.kotlin.problem1964

/**
 * LeetCode page: [1964. Find the Longest Valid Obstacle Course at Each Position](https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of obstacles;
     */
    fun longestObstacleCourseAtEachPosition(obstacles: IntArray): IntArray {
        // Initialize an IntArray to store the result
        val result = IntArray(obstacles.size)

        /* Initialize a mutable list with an initial dummyObstacle (a value less than the minimum possible
         * obstacle). This list stores the minimum obstacles of each course length, i.e.
         * list[index] ::= the minimum obstacles to have the longest course length that equals index.
         */
        val dummyObstacle = -1 // Should not be greater than the minimum possible obstacle
        val lengthMinObstacle = mutableListOf(dummyObstacle)

        /* For each obstacle in obstacles, we find the rightmost insertion index of it in the length list
         * which will also be its longest obstacle and update the result with it. If this insertion index
         * is the size of length list, we append the obstacle to the length list, otherwise we update the
         * existing value at this index with the obstacle.
         */
        for ((index, obstacle) in obstacles.withIndex()) {
            val longestCourse = lengthMinObstacle.rightmostInsertionIndex(obstacle)
            if (longestCourse == lengthMinObstacle.size) {
                lengthMinObstacle.add(obstacle)
            } else {
                lengthMinObstacle[longestCourse] = obstacle
            }

            result[index] = longestCourse
        }

        // return the result
        return result
    }

    /**
     * Return the rightmost insertion index of [value] such that the receiver list remains sorted.
     * The receiver list is expected to be sorted, otherwise the result is undefined.
     */
    private fun List<Int>.rightmostInsertionIndex(value: Int): Int {
        var left = 0
        var right = lastIndex

        while (left <= right) {
            val mid = (left + right) ushr 1
            val midValue = this[mid]
            if (midValue <= value) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
    }
}