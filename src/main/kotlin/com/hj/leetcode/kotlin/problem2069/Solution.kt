package com.hj.leetcode.kotlin.problem2069

/**
 * LeetCode page: [2069. Walking Robot Simulation II](https://leetcode.com/problems/walking-robot-simulation-ii/);
 */
class Solution

/**
 * Your Robot object will be instantiated and called as such:
 * var obj = Robot(width, height)
 * obj.step(num)
 * var param_2 = obj.getPos()
 * var param_3 = obj.getDir()
 */
class Robot(
    val width: Int,
    val height: Int,
) {
    init {
        require(width >= 2 && height >= 2)
    }

    // The robot can only move along the perimeter; we can treat the perimeter
    // as an array and derive position and direction from the index.
    private val size = (width + height - 2) * 2
    private var isInitial = true
    private var index = 0
    private val segmentStart =
        intArrayOf(0, width, width + height - 1, size - height + 2, size)

    // Complexity:
    // Time O(1) and Space O(1).
    fun step(num: Int) {
        isInitial = false
        index = (index + num) % size
    }

    // Complexity:
    // Time O(1) and Space O(1).
    fun getPos(): IntArray =
        when (index) {
            in segmentStart[0]..<segmentStart[1] -> {
                intArrayOf(index, 0)
            }

            in segmentStart[1]..<segmentStart[2] -> {
                intArrayOf(width - 1, index - segmentStart[1] + 1)
            }

            in segmentStart[2]..<segmentStart[3] -> {
                intArrayOf(segmentStart[3] - index - 1, height - 1)
            }

            else -> {
                intArrayOf(0, size - index)
            }
        }

    // Complexity:
    // Time O(1) and Space O(1).
    fun getDir(): String {
        if (isInitial) {
            return "East"
        }

        return when (index) {
            in segmentStart[0] + 1..<segmentStart[1] -> {
                "East"
            }

            in segmentStart[1]..<segmentStart[2] -> {
                "North"
            }

            in segmentStart[2]..<segmentStart[3] -> {
                "West"
            }

            else -> {
                "South"
            }
        }
    }
}
