package com.hj.leetcode.kotlin.problem75

/**
 * LeetCode page: [75. Sort Colors](https://leetcode.com/problems/sort-colors/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun sortColors(nums: IntArray): Unit {
        val red = 0
        val white = 1

        val numReds = pushTargetsToStartAndReturnCount(
            colors = nums,
            start = 0,
            targetColor = red,
        )

        pushTargetsToStartAndReturnCount(
            colors = nums,
            start = numReds,
            targetColor = white
        )
    }

    private fun pushTargetsToStartAndReturnCount(
        colors: IntArray,
        start: Int,
        targetColor: Int,
    ): Int {
        var swapIndex = start
        for (index in start..<colors.size) {
            val color = colors[index]
            if (color == targetColor) {
                colors.swap(index, swapIndex)
                swapIndex++
            }
        }
        return swapIndex
    }

    private fun IntArray.swap(index: Int, withIndex: Int) {
        this[index] = this[withIndex].also { this[withIndex] = this[index] }
    }
}