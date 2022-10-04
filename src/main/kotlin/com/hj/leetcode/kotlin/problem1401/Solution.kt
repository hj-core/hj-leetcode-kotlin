package com.hj.leetcode.kotlin.problem1401

/**
 * LeetCode page: [1401. Circle and Rectangle Overlapping](https://leetcode.com/problems/circle-and-rectangle-overlapping/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun checkOverlap(radius: Int, xCenter: Int, yCenter: Int, x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
        val closestX = when {
            xCenter < x1 -> x1
            xCenter > x2 -> x2
            else -> xCenter
        }

        val closestY = when {
            yCenter < y1 -> y1
            yCenter > y2 -> y2
            else -> yCenter
        }

        return square(closestX - xCenter) + square(closestY - yCenter) <= square(radius)
    }

    private fun square(i: Int) = i * i
}