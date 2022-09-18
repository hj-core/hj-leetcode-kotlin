package com.hj.leetcode.kotlin.problem42

/**
 * LeetCode page: [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of height;
     */
    fun trap(height: IntArray): Int {
        var trapped = 0
        var leftIndex = 0
        var rightIndex = height.lastIndex
        var maxHeightCanTrap = minOf(height[leftIndex], height[rightIndex])

        while (leftIndex < rightIndex) {
            val leftHeight = height[leftIndex]
            val rightHeight = height[rightIndex]
            val minHeight = minOf(leftHeight, rightHeight)
            trapped += (maxHeightCanTrap - minHeight).coerceAtLeast(0)
            maxHeightCanTrap = maxOf(maxHeightCanTrap, minHeight)
            if (leftHeight <= rightHeight) leftIndex++ else rightIndex--
        }
        return trapped
    }
}