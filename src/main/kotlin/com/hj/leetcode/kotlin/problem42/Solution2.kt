package com.hj.leetcode.kotlin.problem42

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of height;
     */
    fun trap(height: IntArray): Int {
        val rightBound = IntArray(height.size)
        for (i in height.lastIndex - 1  downTo 0) {
            rightBound[i] = max(rightBound[i + 1], height[i + 1])
        }

        var result = 0
        var leftBound = 0
        /* The number water trapped at index i is related to the height[i],
         * the max height before i and the max height after i.
         */
        for (i in height.indices) {
            result += max(0, min(leftBound, rightBound[i]) - height[i])
            leftBound = max(leftBound, height[i])
        }
        return result
    }
}