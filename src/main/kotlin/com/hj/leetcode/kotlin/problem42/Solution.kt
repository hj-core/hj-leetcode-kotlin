package com.hj.leetcode.kotlin.problem42

/**
 * LeetCode page: [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of height;
     */
    fun trap(height: IntArray): Int {
        var result = 0
        var left = 0
        var maxLeft = 0 // the smallest index of max(height[0..left])
        var right = height.lastIndex
        var maxRight = right // the largest index of max(height[right..<size])

        while (left <= right) {
            if (height[left] > height[maxLeft]) {
                maxLeft = left
            }
            if (height[right] > height[maxRight]) {
                maxRight = right
            }

            if (height[maxLeft] < height[maxRight]) {
                result += height[maxLeft] - height[left]
                left++
            } else {
                result += height[maxRight] - height[right]
                right--
            }
        }
        return result
    }
}