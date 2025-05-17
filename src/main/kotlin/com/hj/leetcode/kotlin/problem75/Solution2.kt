package com.hj.leetcode.kotlin.problem75

import java.util.*

/**
 * LeetCode page: [75. Sort Colors](https://leetcode.com/problems/sort-colors/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of nums;
     */
    fun sortColors(nums: IntArray) {
        val colorFreq = countColors(nums)
        var start = 0
        for ((color, count) in colorFreq.withIndex()) {
            Arrays.fill(nums, start, start + count, color)
            start += count
        }
    }

    private fun countColors(colors: IntArray): IntArray {
        val result = IntArray(3)
        for (color in colors) {
            result[color]++
        }
        return result
    }
}
