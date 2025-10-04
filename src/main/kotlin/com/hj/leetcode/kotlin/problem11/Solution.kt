package com.hj.leetcode.kotlin.problem11

/**
 * LeetCode page: [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of height.
    fun maxArea(height: IntArray): Int {
        var result = 0
        var iL = 0
        var iR = height.lastIndex

        while (iL < iR) {
            val w = iR - iL
            val h = minOf(height[iL], height[iR])
            result = maxOf(result, w * h)

            while (iL < iR && height[iL] <= h) iL++
            while (iL < iR && height[iR] <= h) iR--
        }
        return result
    }
}
