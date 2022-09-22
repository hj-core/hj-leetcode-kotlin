package com.hj.leetcode.kotlin.problem42

/**
 * LeetCode page: [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of height;
     */
    fun trap(height: IntArray): Int {
        var totalTrapped = 0

        var left = 0
        var right = height.lastIndex
        var maxHeightCanTrap = minOf(height[left], height[right])

        while (left < right) {
            val leftHeight = height[left]
            val rightHeight = height[right]
            val minHeight = minOf(leftHeight, rightHeight)

            val currTrapped = (maxHeightCanTrap - minHeight).coerceAtLeast(0)
            totalTrapped += currTrapped
            maxHeightCanTrap = maxOf(maxHeightCanTrap, minHeight)

            if (leftHeight <= rightHeight) left++ else right--
        }
        return totalTrapped
    }
}