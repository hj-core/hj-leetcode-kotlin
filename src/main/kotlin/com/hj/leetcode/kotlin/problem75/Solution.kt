package com.hj.leetcode.kotlin.problem75

/**
 * LeetCode page: [75. Sort Colors](https://leetcode.com/problems/sort-colors/);
 */
class Solution {
    private val red = 0
    private val white = 1
    private val blue = 2

    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun sortColors(nums: IntArray): Unit {
        val numReds = pushTargetToStartOfRangeAndReturnNumTargets(
            targetColor = red,
            effectiveRange = nums.indices,
            colors = nums
        )

        pushTargetToStartOfRangeAndReturnNumTargets(
            targetColor = white,
            effectiveRange = numReds..nums.lastIndex,
            colors = nums
        )
    }

    private fun pushTargetToStartOfRangeAndReturnNumTargets(
        targetColor: Int,
        effectiveRange: IntRange,
        colors: IntArray
    ): Int {
       var pendingIndex = effectiveRange.first
       for (index in effectiveRange){
           val color = colors[index]
           if (color == targetColor) {
               colors.swap(index, pendingIndex)
               pendingIndex++
           }
       }
        return pendingIndex
    }

    private fun IntArray.swap(index: Int, withIndex: Int) {
        this[index] = this[withIndex].also { this[withIndex] = this[index] }
    }
}